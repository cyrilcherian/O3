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
import app.office.cube.persistence.OfficeHoliday;

/**
 * @author cyril
 * 
 */
@Controller
public class OfficeHolidayController {
	private IOfficeService<OfficeHoliday> service;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	public OfficeHolidayController(@Qualifier(value="officeHolidayService") IOfficeService<OfficeHoliday> service) {
		this.service = service;
	}
	
	@RequestMapping(value="/officeholiday/admin/new", method=RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute("officeHoliday", new OfficeHoliday());
		return "officeholiday/create";
	}
	
	@RequestMapping(value="/officeholiday/admin/create", method=RequestMethod.POST)
	public String create(OfficeHoliday oh, BindingResult result) {
		if (result.hasErrors()) {
			return "officeholiday/create";
		}
		service.create(oh);
		return "redirect:/officeholiday";
	}
	

	@RequestMapping(value="/officeholiday/admin/edit/{id}", method=RequestMethod.GET)
	public String getCreateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(OfficeHoliday.class);
		dc.add(Restrictions.idEq(id));
		OfficeHoliday oh = service.get(dc);
		model.addAttribute("officeHoliday", oh);
		return "officeholiday/edit";
	}
	@RequestMapping(value="/officeholiday/admin/update", method=RequestMethod.POST)
	public String update(OfficeHoliday oh, BindingResult result) {
		service.update(oh);
		return "redirect:/officeholiday";
	}

	@RequestMapping(value="/officeholiday", method = RequestMethod.GET)
	public String list(Model model) {
		Set<OfficeHoliday> holidays = service.findAll();
		String jsonData = "";
		try{
			jsonData=mapper.writeValueAsString(holidays);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("officeHolidayJsonData",jsonData);
		return "officeholiday/list";
	}

}
