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
import app.office.cube.formbean.UserLeavePolicyFormBean;
import app.office.cube.persistence.LeavePolicy;
import app.office.cube.persistence.User;
import app.office.cube.persistence.UserLeavePolicy;

/**
 * @author cyril
 * 
 */
@Controller
public class UserLeavePolicyController {
	private IOfficeService<UserLeavePolicy> service;
	private IOfficeService<LeavePolicy> lpService;
	private IOfficeService<User> userService;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	public UserLeavePolicyController(@Qualifier(value="usersService") IOfficeService<User> userService,
			@Qualifier(value="userLeavePolicyService") IOfficeService<UserLeavePolicy> service,
			@Qualifier(value="leavePolicyService") IOfficeService<LeavePolicy> lpService){
		this.service = service;
		this.lpService = lpService;
		this.userService= userService;
	}
	
	@RequestMapping(value="/userleavepolicy/admin/new", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		UserLeavePolicyFormBean ulpBean = new UserLeavePolicyFormBean();
		model.addAttribute("userLeavePolicy", ulpBean);
		Set<LeavePolicy> lps = lpService.findAll();
		model.addAttribute("leavePolicies", lps);
		Set<User> users = userService.findAll();
		String jsonData = "";
		try{
			jsonData=mapper.writeValueAsString(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userJsonData",jsonData);

		return "userleavepolicy/create";
	}
	@RequestMapping(value="/userleavepolicy/admin/edit/{id}", method=RequestMethod.GET)
	public String getCreateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserLeavePolicy.class);
		dc.add(Restrictions.idEq(id));
		UserLeavePolicy ulp = service.get(dc);
		model.addAttribute("userLeavePolicy", ulp);
		return "userleavepolicy/edit";
	}
	@RequestMapping(value="/userleavepolicy/admin/update", method=RequestMethod.POST)
	public String update(UserLeavePolicy userLeavePolicy, BindingResult result) {
		if (result.hasErrors()) {
			return "userleavepolicy/edit";
		}
		UserLeavePolicy ulp = service.get(userLeavePolicy.getMyKey());
		ulp.setDaysLeaveAllowed(userLeavePolicy.getDaysLeaveAllowed());
		service.update(ulp);
		return "redirect:/userleavepolicy";
	}
	@RequestMapping(value="/userleavepolicy/admin/create", method=RequestMethod.POST)
	public String create(UserLeavePolicyFormBean ulpbean, BindingResult result) {
		LeavePolicy lp = lpService.get(ulpbean.getLeavePolicy());
		for(Long key: ulpbean.getUsers()){
			User user = userService.get(key);
			if (user != null && lp != null){
				DetachedCriteria dc = DetachedCriteria.forClass(UserLeavePolicy.class);
				dc.add(Restrictions.eq("user", user));
				dc.add(Restrictions.eq("leavePolicy", lp));
				UserLeavePolicy ulp = service.get(dc);
				if (ulp == null) {
					ulp = new UserLeavePolicy();
					ulp.setLeavePolicy(lp);
					ulp.setUser(user);
				}
				ulp.setDaysLeaveAllowed(ulp.getDaysLeaveAllowed() + ulpbean.getDaysLeaveAllowed());
				service.update(ulp);
			}
		}
		return "redirect:/userleavepolicy";
	}
	
	@RequestMapping(value="/userleavepolicy", method = RequestMethod.GET)
	public String list(Model model) {
		Set<UserLeavePolicy> policies = service.findAll();
		String jsonData="";
		try{
			jsonData=mapper.writeValueAsString(policies);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userLeavePolicyJsonData",jsonData);
		return "userleavepolicy/list";
	}
	
}
