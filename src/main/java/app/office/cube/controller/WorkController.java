/**
 * 
 */
package app.office.cube.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.office.cube.convertors.ProjectConvertor;
import app.office.cube.convertors.TaskConvertor;
import app.office.cube.convertors.UserConvertor;
import app.office.cube.core.services.IExcelService;
import app.office.cube.core.services.IOfficeService;
import app.office.cube.formbean.Event;
import app.office.cube.formbean.ReportFormBean;
import app.office.cube.helpers.Utils;
import app.office.cube.persistence.OfficeHoliday;
import app.office.cube.persistence.Project;
import app.office.cube.persistence.Task;
import app.office.cube.persistence.User;
import app.office.cube.persistence.WeekHoliday;
import app.office.cube.persistence.Work;

/**
 * @author cyril
 * 
 */
@Controller
public class WorkController {
	private IOfficeService<User> userService;
	private IOfficeService<Project> projectService;
	private IOfficeService<Task> taskService;
	private IOfficeService<WeekHoliday> whService;
	private IExcelService<Work> workService;
	private IOfficeService<OfficeHoliday> ohService;
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	public WorkController(
			@Qualifier(value = "usersService") IOfficeService<User> service,
			@Qualifier(value = "projectsService") IOfficeService<Project> projectService,
			@Qualifier(value = "tasksService") IOfficeService<Task> taskService,
			@Qualifier(value = "officeHolidayService") IOfficeService<OfficeHoliday> ohService,
			@Qualifier(value = "weekHolidayService") IOfficeService<WeekHoliday> whService,
			@Qualifier(value = "worksService") IExcelService<Work> workService) {
		this.userService = service;
		this.projectService = projectService;
		this.taskService = taskService;
		this.workService = workService;
		this.ohService = ohService;
		this.whService = whService;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Task.class, "task", new TaskConvertor(
				taskService));
		binder.registerCustomEditor(User.class, "user", new UserConvertor(
				userService));
		binder.registerCustomEditor(Project.class, "project",
				new ProjectConvertor(projectService));
	}

	@RequestMapping(value = { "/work/show" }, method = RequestMethod.GET)
	public @ResponseBody
	String show(@RequestParam String start, @RequestParam String end,
			HttpServletRequest httpServletRequest) {
		Date startDate = Utils.getDateForUnixTime(start);
		Date endDate = Utils.getDateForUnixTime(end);
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc = DetachedCriteria.forClass(Work.class);
		dc.createAlias("user", "u");
		dc.add(Restrictions.ge("date", startDate));
		dc.add(Restrictions.le("date", endDate));
		dc.add(Restrictions.eq("u.userName", userName));
		Set<Work> workList = workService.find(dc);
		Set<WeekHoliday> whList = whService.findAll();
		DetachedCriteria dc1 = DetachedCriteria.forClass(OfficeHoliday.class);
		dc1.add(Restrictions.ge("date", startDate));
		dc1.add(Restrictions.le("date", endDate));
		Set<OfficeHoliday> ohList = ohService.find(dc1);

		String jsonData = "";
		try {
			List<Event> events = Utils.getEvents(workList);
			events.addAll(Utils.getWeekEvents(ohList, whList, startDate,
					endDate));
			jsonData = mapper.writeValueAsString(events);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonData;
	}

	@RequestMapping(value = { "/work/xls/{start}" }, method = RequestMethod.GET)
	public void generateReport(@PathVariable Long start,
			HttpServletRequest httpServletRequest, HttpServletResponse response) {
		Date startDate = Utils.getMonthStartDate(new Date(start));

		Date endDate = Utils.getMonthEndDate(startDate);
		String fileName = "Work.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		response.setContentType("application/vnd.ms-excel");
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc0 = DetachedCriteria.forClass(User.class);
		dc0.add(Restrictions.eq("userName", userName));
		Long u = userService.get(dc0).getMyKey();
		Set<Long> users = new HashSet<Long>();
		users.add(u);
		Workbook wb = workService.generateReport(users, startDate, endDate);
		Utils.write(response, wb);
	}

	@RequestMapping(value = "/work", method = RequestMethod.GET)
	public String work(Model model) {
		return "work/show";
	}

	@RequestMapping(value = "/work/create", method = RequestMethod.POST)
	public @ResponseBody
	Event create(Work work, HttpServletRequest httpServletRequest) {
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc0 = DetachedCriteria.forClass(User.class);
		dc0.add(Restrictions.eq("userName", userName));
		work.setUser(userService.get(dc0));
		work = workService.create(work);
		return Utils.getEvent(work);
	}

	@RequestMapping(value = "/work/update", method = RequestMethod.POST)
	public @ResponseBody
	Event update(Work work, BindingResult result,
			HttpServletRequest httpServletRequest) {
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc0 = DetachedCriteria.forClass(User.class);
		dc0.add(Restrictions.eq("userName", userName));
		work.setUser(userService.get(dc0));
		work = workService.update(work);
		return Utils.getEvent(work);
	}

	@RequestMapping(value = "/work/delete", method = RequestMethod.POST)
	public @ResponseBody
	Event delete(Work work, BindingResult result,
			HttpServletRequest httpServletRequest) {
		String userName = httpServletRequest.getUserPrincipal().getName();
		DetachedCriteria dc0 = DetachedCriteria.forClass(User.class);
		dc0.add(Restrictions.eq("userName", userName));
		work.setUser(userService.get(dc0));
		work = workService.delete(work);
		return Utils.getEvent(work);
	}

	@RequestMapping(value = "/work/new", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		model.addAttribute("work", new Work());
		model.addAttribute("tasks", taskService.findAll());
		model.addAttribute("projects", projectService.findAll());
		return "work/partial/create";
	}

	@RequestMapping(value = "/work/edit/{id}", method = RequestMethod.GET)
	public String getEditForm(@PathVariable Long id, Model model) {
		DetachedCriteria dc = DetachedCriteria.forClass(Work.class);
		dc.add(Restrictions.idEq(id));
		Work work = workService.get(dc);

		model.addAttribute("work", work);
		model.addAttribute("tasks", taskService.findAll());
		model.addAttribute("projects", projectService.findAll());
		return "work/partial/edit";
	}

	@RequestMapping(value = { "/work/report/create" }, method = RequestMethod.GET)
	public void generateReport(ReportFormBean rfb, HttpServletResponse response) {
		String fileName = "Work.xls";
		response.setHeader("Content-Disposition", "inline; filename="
				+ fileName);
		response.setContentType("application/vnd.ms-excel");
		Workbook wb = workService.generateReport(rfb.getUserSet(), rfb.getFrom(),
				rfb.getTo());
		Utils.write(response, wb);
	}

	@RequestMapping(value = "/work/admin/report/new", method = RequestMethod.GET)
	public String reportNew(Model model) {
		model.addAttribute("reportFormBean", new ReportFormBean());
		return "work/report";
	}

}