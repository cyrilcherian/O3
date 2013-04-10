/**
 * 
 */
package app.office.cube.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.office.cube.convertors.UserConvertor;
import app.office.cube.core.services.IOfficeService;
import app.office.cube.formbean.NotificationFormBean;
import app.office.cube.formbean.TeamFormBean;
import app.office.cube.helpers.RoleType;
import app.office.cube.persistence.Notification;
import app.office.cube.persistence.Team;
import app.office.cube.persistence.User;
import app.office.cube.persistence.UserLeavePolicy;

/**
 * @author cyril
 * 
 */
@Controller
public class NotificationController {
	private IOfficeService<Notification> service;
	private IOfficeService<User> uService;
	private IOfficeService<Team> tService;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public NotificationController(
			@Qualifier(value = "notificationService") IOfficeService<Notification> service,
			@Qualifier(value = "usersService") IOfficeService<User> uService,
			@Qualifier(value = "teamService") IOfficeService<Team> tService) {
		this.service = service;
		this.uService = uService;
		this.tService = tService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	}

	@RequestMapping(value = { "/notification/create" }, method = RequestMethod.POST)
	public void create(@RequestParam(value = "summary") String summary,
			@RequestParam(value = "message") String message,
			@RequestParam(value = "members") Long[] members,
			HttpServletResponse response) {
		List<Long> mList = Arrays.asList(members);
		Set<User> users = new HashSet<User>();
		if (mList.contains("-1")) { // /means All
			users.addAll(uService.findAll());
		} else {
			for (Long m : mList) {
				User u = uService.get(m);
				if (u == null) {
					Team t = tService.get(m);
					Set<Team> teams = new HashSet<Team>();
					teams.add(t);
					getTeams(teams, t);
					users.addAll(getUsers(teams));
				} else {
					users.add(u);
				}
			}
		}
		for (User user : users) {
			Notification n = new Notification();
			n.setUser(user);
			n.setDescription(message);
			n.setSummary(summary);
			service.create(n);
		}
	}
	@RequestMapping(value = "/notification/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		NotificationFormBean nfb = new NotificationFormBean();
		model.addAttribute("notificationBean", nfb);
		model.addAttribute("notifiers", getMembers());
		return "notification/new";
	}

	private String getMembers() {
		Map<String, List<Map<String, String>>> members = new HashMap<String, List<Map<String, String>>>();
		List<Map<String, String>> notifiers = new ArrayList<Map<String, String>>();
		HashMap<String, String> m = new HashMap<String, String>();
		for (User u : uService.findAll()) {
			m.put("name", u.getUserName());
			m.put("id", u.getMyKey() + "");
			notifiers.add(m);
			m.clear();
		}
		for (Team t : tService.findAll()) {
			m.put("name", t.getName());
			m.put("id", t.getMyKey() + "");
			notifiers.add(m);
			m.clear();
		}
		m.put("name", "All");
		m.put("id", "-1");
		notifiers.add(m);
		members.put("data", notifiers);
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(members);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	private Set<User> getUsers(Set<Team> teams) {
		LinkedHashSet<User> set = new LinkedHashSet<User>();
		for (Team tm : teams) {
			set.addAll(tm.getUsers());
		}

		return set;
	}

	private void getTeams(Set<Team> teams, Team team) {
		for (Team tm : team.getTeams()) {
			if (teams.add(tm)) {
				getTeams(teams, tm);
			}
		}
	}

}
