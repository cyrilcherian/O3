/**
 * 
 */
package app.office.cube.helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDate;

import app.office.cube.formbean.Event;
import app.office.cube.persistence.OfficeHoliday;
import app.office.cube.persistence.Role;
import app.office.cube.persistence.WeekHoliday;
import app.office.cube.persistence.Work;

/**
 * @author Cyril
 * 
 */
public class Utils {

	public RoleType[] sync(Set<Role> r) {
		RoleType[] rts = RoleType.values();
		for (RoleType rt : rts) {
			if (getExistingRole(r, rt.getName()) != null) {
				rt.setChecked(true);
			} else {
				rt.setChecked(false);
			}
		}
		return rts;
	}

	public void syncDBRoles(Set<Role> dbRoles, Set<Role> roles) {
		for (Role dbRole : dbRoles) {
			Role role = getExistingRole(roles, dbRole.getRoleName());
			if (role != null) {
				role.setMyKey(dbRole.getMyKey());
			}
		}
	}

	public Role getExistingRole(Set<Role> roles, String roleName) {
		for (Role role : roles) {
			if (role.getRoleName().equalsIgnoreCase(roleName))
				return role;
		}
		return null;
	}

	public static Event getEvent(Work work) {
		Event event = new Event();
		String title = "Description: " + work.getDescription();
		title += "\n Hours:" + work.getHours();
		title += "\n Project:" + work.getProject().getName();
		title += "\n Task:" + work.getTask().getName();
		event.setTitle(title);
		event.setId(work.getMyKey() + "");
		event.setStart(String.format("%1$tY-%1$tm-%1$td", work.getDate()));
		return event;
	}
	
	public static Event getHolidayEvent(String title, Date date, String color) {
		Event event = new Event();
		event.setTitle(title);
		event.setId("holiday" + date.toString());
		event.setColor(color);
		event.setStart(String.format("%1$tY-%1$tm-%1$td", date));
		return event;
	}

	public static List<Event> getEvents(Collection<Work> workList) {
		ArrayList<Event> events = new ArrayList<Event>();
		for (Work work : workList) {
			events.add(getEvent(work));
		}
		return events;
	}
	public static List<Event> getWeekEvents(Collection<OfficeHoliday> ohList, Collection<WeekHoliday> whList, Date start, Date end) {
		ArrayList<Event> events = new ArrayList<Event>();
		LocalDate date = LocalDate.fromDateFields(start);
		LocalDate eDate = LocalDate.fromDateFields(end);
		for (; date.isBefore(eDate); date=date.plusDays(1)){
			for (WeekHoliday wh : whList) {
				if (CalendarHelper.isOffDay(wh, date.toDateMidnight().toDate())){
					events.add(getHolidayEvent("Weekend", date.toDateMidnight().toDate(), Event.LIGHT_CORAL));
					break;
				}
			}
			for (OfficeHoliday oh : ohList) {
				if (oh.getDate().equals(date.toDateMidnight().toDate())){
					events.add(getHolidayEvent(oh.getDescription(), date.toDateMidnight().toDate(), Event.OLIVE));
					break;
				}
			}
		}
			
		return events;
	}

	public static Date getDateForUnixTime(String unixDate) {
		return new Date(Long.parseLong(unixDate) * 1000);
	}

	public static Date getMonthEndDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, maxDay);
		return calendar.getTime();
	}

	public static Date getMonthStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}

	public static void write(HttpServletResponse response, Workbook wb) {

		try {
			// Retrieve the output stream
			ServletOutputStream outputStream = response.getOutputStream();
			// Write to the output stream
			wb.write(outputStream);
			// Flush the stream
			outputStream.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
