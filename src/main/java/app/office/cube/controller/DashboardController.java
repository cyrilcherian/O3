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

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.LeaveStatus;
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
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public DashboardController(
			@Qualifier(value = "usersService") IOfficeService<User> userService,
			@Qualifier(value = "userLeaveService") IOfficeService<UserLeave> ulService,
			@Qualifier(value = "projectsService") IOfficeService<Project> pService) {
		this.userService = userService;
		this.ulService = ulService;
		this.projectService = pService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {
		listNewJoinee(model);
		listNewProject(model);
		listLeaveApprovalList(model);
		return "dashboard";
	}

	private void listNewJoinee(Model model) {
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
		model.addAttribute("userJsonData", jsonData);
	}
	private void listLeaveApprovalList(Model model) {
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
		model.addAttribute("userLeaveJsonData",jsonData);
	}

	private void listNewProject(Model model) {
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
		model.addAttribute("projectJsonData", jsonData);
	}
}
