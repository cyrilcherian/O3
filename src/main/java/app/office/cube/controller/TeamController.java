/**
 * 
 */
package app.office.cube.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.office.cube.convertors.TeamTypeConvertor;
import app.office.cube.convertors.UserTypeConvertor;
import app.office.cube.core.services.IOfficeService;
import app.office.cube.formbean.TeamFormBean;
import app.office.cube.persistence.Team;
import app.office.cube.persistence.User;

/**
 * @author cyril
 * 
 */
@Controller
public class TeamController {
	private IOfficeService<Team> service;
	private IOfficeService<User> userService;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public TeamController(
			@Qualifier(value = "usersService") IOfficeService<User> userService,
			@Qualifier(value = "teamService") IOfficeService<Team> service) {
		this.service = service;
		this.userService = userService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "users", new UserTypeConvertor(
				userService));
		binder.registerCustomEditor(Set.class, "teams", new TeamTypeConvertor(
				service));

	}

	@RequestMapping(value = { "/team/validateT" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateTeamName(@RequestParam String name) {
		return validateTN(null, name);
	}

	@RequestMapping(value = { "/team/{id}/validateT" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateTeamName(@PathVariable Long id, @RequestParam String name) {
		return validateTN(id, name);
	}

	private boolean validateTN(Long id, String name) {
		DetachedCriteria dc = DetachedCriteria.forClass(Team.class);
		dc.add(Restrictions.eq("name", name));
		if (id != null) {
			dc.add(Restrictions.ne("myKey", id));
		}

		Set<Team> ulist = service.find(dc);
		if (ulist.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/team/admin/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		TeamFormBean teamBean = new TeamFormBean();
		model.addAttribute("teamBean", teamBean);
		return "team/create";
	}

	@RequestMapping(value = "/team/admin/edit/{id}", method = RequestMethod.GET)
	public String getCreateForm(@PathVariable Long id, Model model) {
		getShowData(id, model);
		return "team/edit";
	}
	@RequestMapping(value = "/team/show/{id}", method = RequestMethod.GET)
	public String getShowForm(@PathVariable Long id, Model model) {
		getShowData(id, model);
		return "team/show";
	}
	private void getShowData(@PathVariable Long id, Model model){
		DetachedCriteria dc = DetachedCriteria.forClass(Team.class);
		dc.add(Restrictions.idEq(id));
		Team team = service.get(dc);
		String usersData = "";
		String teamsData = "";
		try {
			usersData = mapper.writeValueAsString(team.getUsers());
			teamsData = mapper.writeValueAsString(team.getTeams());
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("team", team);
		model.addAttribute("users", usersData);
		model.addAttribute("teams", teamsData);
	}
	@RequestMapping(value = "/team/admin/update", method = RequestMethod.POST)
	public String update(Team team, BindingResult result) {
		if (result.hasErrors()) {
			return "team/edit";
		}
		service.update(team);
		return "redirect:/team";
	}

	@RequestMapping(value = "/team/admin/create", method = RequestMethod.POST)
	public String create(TeamFormBean tBean, BindingResult result) {
		if (result.hasErrors()) {
			return "team/new";
		}
		Team team = new Team();
		team.setName(tBean.getName());
		team.setDescription(tBean.getDescription());
		LinkedHashSet<User> users = new LinkedHashSet<User>();
		LinkedHashSet<Team> teams = new LinkedHashSet<Team>();

		for (Long key : tBean.getUserSet()) {
			User user = userService.get(key);
			if (user != null) {
				users.add(user);
			}
		}
		for (Long key : tBean.getTeamSet()) {
			Team t = service.get(key);
			if (t != null) {
				teams.add(t);
			}
		}
		team.setUsers(users);
		team.setTeams(teams);
		service.update(team);
		return "redirect:/team";
	}

	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public String list(Model model) {
		getTeamList(model);
		return "team/list";
	}

	@RequestMapping(value = "/team/user", method = RequestMethod.GET)
	public String listUser(Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("active", true));
		Set<User> userList = new LinkedHashSet<User>(userService.find(dc));

		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userJsonData", jsonData);
		return "team/partial/userlist";
	}

	@RequestMapping(value = "/team/partial", method = RequestMethod.GET)
	public String teamPartialList(Model model) {
		getTeamList(model);
		return "team/partial/teamlist";
	}

	private void getTeamList(Model model) {
		Set<Team> teams = service.findAll();
		for (Team t : teams) {
			t.setUsers(null);
			t.setTeams(null);
		}
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(teams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("teamJsonData", jsonData);
	}
}
