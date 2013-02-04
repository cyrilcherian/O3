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
import app.office.cube.persistence.Task;

/**
 * @author cyril
 * 
 */
@Controller
public class TaskController {
	private IOfficeService<Task> service;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public TaskController(
			@Qualifier(value = "tasksService") IOfficeService<Task> service) {
		this.service = service;
	}

	@RequestMapping(value = { "/task/validateTN" }, method = RequestMethod.GET)
	public @ResponseBody
	boolean validateUN(@RequestParam String name) {
		return validateTaskName(name);
	}

	private boolean validateTaskName(String name) {
		DetachedCriteria dc = DetachedCriteria.forClass(Task.class);
		dc.add(Restrictions.eq("name", name));
		Set<Task> ulist = service.find(dc);
		if (ulist.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/task/admin/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		return "task/create";
	}

	@RequestMapping(value = "/task/admin/create", method = RequestMethod.POST)
	public String create(Task task, BindingResult result) {
		if (result.hasErrors()) {
			return "task/create";
		}
		task = service.create(task);
		return "redirect:/task";
	}

	@RequestMapping(value = "/task/admin/edit/{id}", method = RequestMethod.GET)
	public String getUpdateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(Task.class);
		dc.add(Restrictions.idEq(id));
		Task task = service.get(dc);
		model.addAttribute("task", task);
		return "task/edit";
	}

	@RequestMapping(value = "/task/admin/update", method = RequestMethod.POST)
	public String update(Task task, BindingResult result) {
		if (result.hasErrors()) {
			return "task/edit";
		}

		service.update(task);
		return "redirect:/task";
	}

	@RequestMapping(value = "/task", method = RequestMethod.GET)
	public String list(Model model) {
		Set<Task> taskList = service.findAll();
		String jsonData = "";
		try {
			jsonData = mapper.writeValueAsString(taskList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("taskJsonData", jsonData);
		return "task/list";
	}

}
