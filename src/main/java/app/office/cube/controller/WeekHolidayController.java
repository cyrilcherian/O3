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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.office.cube.convertors.DaysTypeConvertor;
import app.office.cube.convertors.FrequencyTypeConvertor;
import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.Days;
import app.office.cube.persistence.Frequency;
import app.office.cube.persistence.WeekHoliday;

/**
 * @author cyril
 * 
 */
@Controller
public class WeekHolidayController {
	private IOfficeService<WeekHoliday> whService;
	private ObjectMapper mapper = new ObjectMapper();

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Days.class, new DaysTypeConvertor());
		binder.registerCustomEditor(Frequency.class,
				new FrequencyTypeConvertor());
	}

	@Autowired
	public WeekHolidayController(
			@Qualifier(value = "weekHolidayService") IOfficeService<WeekHoliday> whService) {
		this.whService = whService;
	}

	@RequestMapping(value = "/weekholiday/admin/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute("weekHoliday", new WeekHoliday());
		return "weekholiday/create";
	}

	@RequestMapping(value = "/weekholiday/admin/create", method = RequestMethod.POST)
	public String create(WeekHoliday wh, BindingResult result) {
		if (result.hasErrors()) {
			return "weekholiday/create";
		}
		whService.create(wh);
		return "redirect:/weekholiday";
	}

	@RequestMapping(value = "/weekholiday/admin/edit/{id}", method = RequestMethod.GET)
	public String getCreateForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(WeekHoliday.class);
		dc.add(Restrictions.idEq(id));
		WeekHoliday wh = whService.get(dc);
		model.addAttribute("weekHoliday", wh);
		return "weekholiday/edit";
	}

	@RequestMapping(value = "/weekholiday/admin/update", method = RequestMethod.POST)
	public String update(WeekHoliday wh, BindingResult result) {
		if (result.hasErrors()) {
			return "weekholiday/edit/" + wh.getMyKey();
		}
		whService.update(wh);
		return "redirect:/weekholiday";
	}

	@RequestMapping(value = "/weekholiday", method = RequestMethod.GET)
	public String list(Model model) {
		Set<WeekHoliday> weekHolidays = whService.findAll();
		String weekjsonData = "";
		try {
			weekjsonData = mapper.writeValueAsString(weekHolidays);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("weekHolidayJsonData", weekjsonData);
		return "weekholiday/list";
	}

}
