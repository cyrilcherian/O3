/**
 * 
 */
package app.office.cube.controller;

import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.LeavePolicy;
import app.office.cube.persistence.User;
import app.office.cube.persistence.UserLeavePolicy;

/**
 * @author cyril
 * 
 */
@Controller
public class LeavePolicyController {
	private IOfficeService<LeavePolicy> service;
	private IOfficeService<User> userService;
	private IOfficeService<UserLeavePolicy> ulpService;
	private ObjectMapper mapper = new ObjectMapper();
	@Autowired
	public LeavePolicyController(@Qualifier(value="leavePolicyService") IOfficeService<LeavePolicy> service,
			@Qualifier(value="usersService") IOfficeService<User> userService,
			@Qualifier(value="userLeavePolicyService") IOfficeService<UserLeavePolicy> ulpService) {
		this.service = service;
		this.userService = userService;
		this.ulpService = ulpService;
	}
	
	@RequestMapping(value="/leavepolicy/admin/new", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute("leavePolicy", new LeavePolicy());
		return "leavepolicy/create";
	}
	@RequestMapping(value="/leavepolicy/admin/edit/{id}", method=RequestMethod.GET)
	public String getCreateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(LeavePolicy.class);
		dc.add(Restrictions.idEq(id));
		LeavePolicy lp = service.get(dc);
		model.addAttribute("leavePolicy", lp);
		return "leavepolicy/edit";
	}
	@RequestMapping(value="/leavepolicy/admin/update", method=RequestMethod.POST)
	public String update(LeavePolicy leavePolicy, BindingResult result) {
		if (result.hasErrors()) {
			return "leavepolicy/edit";
		}
		service.update(leavePolicy);
		return "redirect:/leavepolicy";
	}
	
	@RequestMapping(value="/leavepolicy/admin/create", method=RequestMethod.POST)
	public String create(LeavePolicy leavePolicy, BindingResult result) {
		if (result.hasErrors()) {
			return "leavepolicy/create";
		}
		leavePolicy = service.create(leavePolicy);
		Set<User> userList = userService.findAll();
		for (User user: userList) {
			UserLeavePolicy ulp = new UserLeavePolicy();
			ulp.setUser(user);
			ulp.setLeavePolicy(leavePolicy);
			ulpService.create(ulp);
		}
		return "redirect:/leavepolicy";
	}

	@RequestMapping(value="/leavepolicy", method = RequestMethod.GET)
	public String list(Model model) {
		Set<LeavePolicy> holidays = service.findAll();
		String jsonData = "";
		try{
			jsonData=mapper.writeValueAsString(holidays);
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("leavePolicyJsonData",jsonData);
		return "leavepolicy/list";
	}
}
