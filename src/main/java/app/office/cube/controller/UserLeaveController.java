/**
 * 
 */
package app.office.cube.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.core.services.IUserLeaveService;
import app.office.cube.persistence.LeaveStatus;
import app.office.cube.persistence.Notification;
import app.office.cube.persistence.User;
import app.office.cube.persistence.UserLeave;
import app.office.cube.persistence.UserLeavePolicy;

/**
 * @author Cyril
 *
 */
@Controller
public class UserLeaveController {
	
	private IOfficeService<UserLeavePolicy> ulpService;
	private IUserLeaveService<UserLeave> ulService;
	private IOfficeService<User> uService;
	private IOfficeService<Notification> nService;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	public UserLeaveController(@Qualifier(value="userLeavePolicyService") IOfficeService<UserLeavePolicy> service,
			@Qualifier(value="userLeaveService") IUserLeaveService<UserLeave> ulService,
			@Qualifier(value="usersService") IOfficeService<User> uService,
			@Qualifier(value="notificationService") IOfficeService<Notification> nService
			){
		this.ulpService = service;
		this.ulService = ulService;
		this.uService = uService;
		this.nService = nService;
	}

	@RequestMapping(value="/userleave/applylist", method = RequestMethod.GET)
	public String applyList( Model model,  HttpServletRequest httpServletRequest) {
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc = DetachedCriteria.forClass(UserLeavePolicy.class);
		dc.createAlias("user", "u");
		dc.createAlias("leavePolicy", "lp");
		dc.add(Restrictions.eq("u.userName", userName));
		dc.add(Restrictions.gt("daysLeaveAllowed", 0F));
		Set<UserLeavePolicy> policies = ulpService.find(dc);
		String jsonData="";
		try{
			jsonData=mapper.writeValueAsString(policies);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userLeavePolicyJsonData",jsonData);
		return "userleave/applylist";
	}
	@RequestMapping(value="/userleave", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest httpServletRequest) {
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc0 = DetachedCriteria.forClass(User.class);
		dc0.add(Restrictions.eq("userName", userName));
		User user = uService.get(dc0);
		
		DetachedCriteria dc = DetachedCriteria.forClass(UserLeave.class);
		dc.createAlias("userLeavePolicy", "ulp");
		dc.add(Restrictions.eq("ulp.user", user));
		Set<UserLeave> userLeaves = ulService.find(dc);

		String jsonData="";
		try{
			jsonData=mapper.writeValueAsString(userLeaves);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userLeaveJsonData",jsonData);
		return "userleave/list";
	}
	
	@RequestMapping(value="/userleave/admin/approvelist", method = RequestMethod.GET)
	public String approveList( Model model,  HttpServletRequest httpServletRequest) {
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
		return "userleave/approvelist";
	}
	@RequestMapping(value="/userleave/admin/approve/{id}", method=RequestMethod.POST)
	public String approveLeave(@PathVariable Long id, Model model) {
		UserLeave ul = ulService.get(id);
		ul.setStatus(LeaveStatus.APPROVED);
		Notification n = new Notification();
		UserLeavePolicy ulp = ulpService.get(ul.getUserLeavePolicy().getMyKey());
		n.setUser(ulp.getUser());
		n.setDescription("Leave is approved");
		n.setSummary("Leave Approved");
		ulService.update(ul);
		nService.create(n);
		return "redirect:/userleave/admin/approvelist";
	}
	@RequestMapping(value="/userleave/admin/disapprove/{id}", method=RequestMethod.POST)
	public String disapproveLeave(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserLeave.class);
		dc.createAlias("userLeavePolicy", "ulp");
		dc.add(Restrictions.idEq(id));
		UserLeave ul = ulService.get(dc);
		ul.setStatus(LeaveStatus.DISAPPROVED);
		Notification n = new Notification();
		UserLeavePolicy ulp = ulpService.get(ul.getUserLeavePolicy().getMyKey());
		n.setUser(ulp.getUser());
		n.setDescription("Leave is disapproved");
		n.setSummary("Leave Disapproved");
		ulService.update(ul);
		nService.create(n);
		float daysLeaveAllowed = ul.getUserLeavePolicy().getDaysLeaveAllowed();
		ul.getUserLeavePolicy().setDaysLeaveAllowed(daysLeaveAllowed + ul.getDays());
		ulpService.update(ul.getUserLeavePolicy());
		return "redirect:/userleave/admin/approvelist";
	}
	@RequestMapping(value="/userleave/delete/{id}", method=RequestMethod.POST)
	public String delete(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(UserLeave.class);
		dc.createAlias("userLeavePolicy", "ulp");
		dc.add(Restrictions.idEq(id));
		UserLeave ul = ulService.get(dc);
		if (ul.getStatus() == LeaveStatus.WAITING_APPROVAL) {
			float daysLeaveAllowed = ul.getUserLeavePolicy().getDaysLeaveAllowed();
			ul.getUserLeavePolicy().setDaysLeaveAllowed(daysLeaveAllowed + ul.getDays());
			ulpService.update(ul.getUserLeavePolicy());
		}
		ulService.delete(ul);
		return "redirect:/userleave";
	}

}
