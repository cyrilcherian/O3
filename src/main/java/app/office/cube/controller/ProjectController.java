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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.Project;

/**
 * @author cyril
 * 
 */
@Controller
public class ProjectController {
	private IOfficeService<Project> service;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public ProjectController(
			@Qualifier(value = "projectsService") IOfficeService<Project> service) {
		this.service = service;
	}

	@RequestMapping(value = { "/project/validatePN" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateUN(@RequestParam String name) {
		return validateProjectName(name);
	}

	private boolean validateProjectName(String name) {
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		dc.add(Restrictions.eq("name", name));
		Set<Project> ulist = service.find(dc);
		if (ulist.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/project/admin/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		Project project = new Project();
		model.addAttribute("project", project);
		return "project/create";
	}

	@RequestMapping(value = "/project/admin/create", method = RequestMethod.POST)
	public String create(Project project, BindingResult result) {
		if (result.hasErrors()) {
			return "project/create";
		}
		project = service.create(project);
		return "redirect:/project";
	}

	@RequestMapping(value = "/project/admin/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(Project.class);
		dc.add(Restrictions.idEq(id));
		Project project = service.get(dc);
		model.addAttribute("project", project);
		return "project/edit";
	}
	
	@RequestMapping(value = "/project/admin/update", method = RequestMethod.POST)
	public String update(Project project, BindingResult result) {
		if (result.hasErrors()) {
			return "project/edit";
		}
	
		service.update(project);
		return "redirect:/project";
	}
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String list(Model model) {
		Set<Project> userList = service.findAll();
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("projectJsonData", jsonData);
		return "project/list";
	}

}
