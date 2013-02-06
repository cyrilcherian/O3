/**
 * 
 */
package app.office.cube.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.LeaveStatus;
import app.office.cube.persistence.Notification;
import app.office.cube.persistence.Project;
import app.office.cube.persistence.User;
import app.office.cube.persistence.UserLeave;

/**
 * @author Cyril
 * 
 */
@Controller
public class DashboardController {
	private IOfficeService<User> userService;
	private IOfficeService<Project> projectService;
	private IOfficeService<UserLeave> ulService;
	private IOfficeService<Notification> notificationService;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public DashboardController(
			@Qualifier(value = "usersService") IOfficeService<User> userService,
			@Qualifier(value = "userLeaveService") IOfficeService<UserLeave> ulService,
			@Qualifier(value = "projectsService") IOfficeService<Project> pService,
			@Qualifier(value = "notificationService") IOfficeService<Notification> notificationService){
		this.userService = userService;
		this.ulService = ulService;
		this.projectService = pService;
		this.notificationService = notificationService;
		
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list() {
		return "dashboard";
	}
	
	@RequestMapping(value = "/listJoinee", method = RequestMethod.GET)
	public @ResponseBody String listNewJoinee() {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -10);
		dc.add(Restrictions.gt("createdDate", cal.getTime()));
		Set<User> userList = userService.find(dc);
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String a = "aaData";
		return "{" + '"' + a +'"' + ":" + jsonData + "}";
	}
	
	@RequestMapping(value = "/listLeaveApproval", method = RequestMethod.GET)
	public @ResponseBody String  listLeaveApproval() {
		DetachedCriteria dc = DetachedCriteria.forClass(UserLeave.class);
		dc.createAlias("userLeavePolicy", "ulp");
		dc.add(Restrictions.eq("status", LeaveStatus.WAITING_APPROVAL));
		Set<UserLeave> userLeaves = ulService.find(dc);
		String jsonData="";
		try{
			jsonData=mapper.writeValueAsString(userLeaves);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String a = "aaData";
		return "{" + '"' + a +'"' + ":" + jsonData + "}";
	}

	@RequestMapping(value = "/listNewProject", method = RequestMethod.GET)
	public @ResponseBody String listNewProject() {
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -10);
		dc.add(Restrictions.gt("createdDate", cal.getTime()));
		Set<Project> projectList = projectService.find(dc);
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(projectList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String a = "aaData";
		return "{" + '"' + a +'"' + ":" + jsonData + "}";
	}
	
	@RequestMapping(value = "/listNotifications", method = RequestMethod.GET)
	public @ResponseBody String listNotifications() {
		DetachedCriteria dc = DetachedCriteria.forClass(Notification.class);
		Set<Notification> notifications = notificationService.find(dc);
		String jsonData = "";
		try{
			jsonData=mapper.writeValueAsString(notifications);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String a = "aaData";
		return "{" + '"' + a +'"' + ":" + jsonData + "}";
	}
}
