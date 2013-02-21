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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import app.office.cube.convertors.UserConvertor;
import app.office.cube.core.services.IOfficeService;
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
		binder.registerCustomEditor(User.class, "user", new UserConvertor(
				uService));
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

	@RequestMapping(value = { "/notification/getmembers" }, method = RequestMethod.GET)
	public String getMembers() {
		Map<String, List<Map<Long, String>>> members = new HashMap<String, List<Map<Long, String>>>();
		List<Map<Long, String>> us = new ArrayList<Map<Long, String>>();
		for (User u : uService.findAll()) {
			HashMap<Long, String> m = new HashMap<Long, String>();
			m.put(u.getMyKey(), u.getUserName());
			us.add(m);
		}
		members.put("users", us);
		List<Map<Long, String>> ts = new ArrayList<Map<Long, String>>();
		for (Team t : tService.findAll()) {
			HashMap<Long, String> m = new HashMap<Long, String>();
			m.put(t.getMyKey(), t.getName());
			ts.add(m);
		}
		members.put("teams", ts);
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
