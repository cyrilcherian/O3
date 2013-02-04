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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
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

import app.office.cube.convertors.RolesTypeConvertor;
import app.office.cube.core.services.IOfficeService;
import app.office.cube.core.services.IRoleService;
import app.office.cube.helpers.RoleType;
import app.office.cube.helpers.Utils;
import app.office.cube.persistence.Role;
import app.office.cube.persistence.User;

/**
 * @author cyril
 * 
 */
@Controller
public class UserController {
	private IOfficeService<User> userService;
	private IRoleService<Role> roleService;
	private ObjectMapper mapper = new ObjectMapper();
	private Utils utils = new Utils();

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Role.class, new RolesTypeConvertor());
	}

	@Autowired
	public UserController(
			@Qualifier(value = "usersService") IOfficeService<User> service,
			@Qualifier(value = "rolesService") IRoleService<Role> roleService) {
		this.userService = service;
		this.roleService = roleService;
	}

	@RequestMapping(value = { "/user/{id}/validateCID" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateCID(@PathVariable Long id, @RequestParam String corporateID) {
		return validateCorporateID(id, corporateID);
	}

	@RequestMapping(value = { "/user/validateCID" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateCID(@RequestParam String corporateID) {
		return validateCorporateID(null, corporateID);
	}

	private boolean validateCorporateID(Long id, String corporateID) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("corporateID", corporateID));
		if (id != null) {
			dc.add(Restrictions.ne("myKey", id));
		}
		Set<User> ulist = userService.find(dc);
		if (ulist.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = { "/user/{id}/validateUN" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateUN(@PathVariable Long id, @RequestParam String userName) {
		return validateUsername(id, userName);
	}

	@RequestMapping(value = { "/user/validateUN" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateUN(@RequestParam String userName) {
		return validateUsername(null, userName);
	}

	private boolean validateUsername(Long id, String userName) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("userName", userName));
		if (id != null) {
			dc.add(Restrictions.ne("myKey", id));
		}
		Set<User> ulist = userService.find(dc);
		if (ulist.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/user/admin/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("roleTypes", RoleType.values());
		return "user/create";
	}

	@RequestMapping(value = "/user/admin/create", method = RequestMethod.POST)
	public String create(User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/create";
		}
		String myPassword = user.getPassword();
		if (myPassword != null && !"".equals(myPassword.trim())) {
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			user.setPassword(enc.encodePassword(myPassword, null));
		}
		user = userService.create(user);
		return "redirect:/user/active";
	}

	@RequestMapping(value = "/user/admin/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.idEq(id));
		User user = userService.get(dc);
		model.addAttribute("user", user);
		model.addAttribute("roleTypes", utils.sync(user.getRoles()));
		return "user/edit";
	}

	@RequestMapping(value = "/user/admin/activate/{id}", method = RequestMethod.GET)
	public String activate(@PathVariable Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.idEq(id));
		User user = userService.get(dc);
		user.setActive(true);
		userService.update(user);
		return "redirect:/user/active";
	}

	@RequestMapping(value = "/user/admin/deactivate/{id}", method = RequestMethod.GET)
	public String deactivate(@PathVariable Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.idEq(id));
		User user = userService.get(dc);
		user.setActive(false);
		userService.update(user);
		return "redirect:/user/active";
	}
	@PreAuthorize("hasRole('ROLE_SUPERVISOR')")
	@RequestMapping(value = "/user/edit", method = RequestMethod.GET)
	public String getUpdateForm(HttpServletRequest httpServletRequest,
			Model model) {
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		dc.add(Restrictions.eq("userName", userName));
		User user = userService.get(dc);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	public String update(User user, BindingResult result) {
		if (result.hasErrors()) {
			return "user/edit";
		}
		User initialUsrList = userService.get(user.getMyKey());
		user.setPassword(initialUsrList.getPassword());
		if (user.getRoles() != null) {
			utils.syncDBRoles(initialUsrList.getRoles(), user.getRoles());
		} else {
			user.setRoles(initialUsrList.getRoles());
		}
		for (Role role : initialUsrList.getRoles()) {
			if (utils.getExistingRole(user.getRoles(), role.getRoleName()) == null) {
				roleService.delete(role);
			}
		}
		userService.update(user);
		return "redirect:/user/active";
	}

	@RequestMapping(value = {"/user/update/password"}, method = RequestMethod.POST)
	public String resetPassword(User user, BindingResult result) {
		User us = userService.get(user.getMyKey());
		String myPassword = user.getPassword();
		if (myPassword != null && !"".equals(myPassword.trim())) {
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			us.setPassword(enc.encodePassword(myPassword, null));
		}
		userService.update(us);
		return "redirect:/user/active";
	}

	@RequestMapping(value = "/user/edit/password", method = RequestMethod.GET)
	public String getEditPasswordForm(HttpServletRequest httpServletRequest, Model model, @RequestParam(required=false)  Long id) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if (id == null){
			String userName = httpServletRequest.getUserPrincipal().getName();
			dc.add(Restrictions.eq("userName", userName));
		} else{
			dc.add(Restrictions.eq("myKey", id));
		}
		User user = userService.get(dc);
		model.addAttribute("user", user);
		return "user/resetpassword";
	}

	@RequestMapping(value = "/user/{active}", method = RequestMethod.GET)
	public String list(Model model, @PathVariable String active) {
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		if ("inactive".equals(active)) {
			dc.add(Restrictions.eq("active", false));
		} else {
			dc.add(Restrictions.eq("active", true));
		}
		Set<User> userList = userService.find(dc);
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("userJsonData", jsonData);
		return "user/list";
	}
	
}
