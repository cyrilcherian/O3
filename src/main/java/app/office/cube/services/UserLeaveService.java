/**
 * 
 */
package app.office.cube.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.core.services.IUserLeaveService;
import app.office.cube.helpers.CalendarHelper;
import app.office.cube.persistence.LeaveStatus;
import app.office.cube.persistence.OfficeHoliday;
import app.office.cube.persistence.UserLeave;
import app.office.cube.persistence.WeekHoliday;

/**
 * @author cyril
 * 
 */
@Service("userLeaveService")
@Repository
public class UserLeaveService implements IUserLeaveService<UserLeave> {
	private static final String COUNT = "select count(ul) from UserLeave ul";
	private SessionFactory sf;
	private IOfficeService<WeekHoliday> whService;
	private IOfficeService<OfficeHoliday> ohService;

	@Autowired
	public UserLeaveService(
			@Qualifier(value = "mySessionFactory") SessionFactory sf,
			@Qualifier(value = "weekHolidayService") IOfficeService<WeekHoliday> whService,
			@Qualifier(value = "officeHolidayService") IOfficeService<OfficeHoliday> ohService) {
		this.sf = sf;
		this.whService = whService;
		this.ohService = ohService;
	}

	@Transactional(readOnly = true)
	public Set<UserLeave> find(DetachedCriteria crit) {
		return new HashSet<UserLeave>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<UserLeave> findAll() {
		return new HashSet<UserLeave>(sf.getCurrentSession()
				.getNamedQuery("listUserLeaves").list());
	}

	@Transactional
	public UserLeave create(UserLeave o) {
		o.setStatus(LeaveStatus.WAITING_APPROVAL);
		float daysLeaveAllowed = o.getUserLeavePolicy().getDaysLeaveAllowed();
		o.getUserLeavePolicy().setDaysLeaveAllowed(
				daysLeaveAllowed - o.getDays());
		long id = (Long) sf.getCurrentSession().save(o);
		sf.getCurrentSession().update(o.getUserLeavePolicy());
		return (UserLeave) sf.getCurrentSession().get(UserLeave.class, id);
	}

	@Transactional
	public UserLeave update(UserLeave o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (UserLeave) sf.getCurrentSession().get(UserLeave.class,
				o.getMyKey());
	}

	@Transactional
	public UserLeave delete(UserLeave ul) {
		sf.getCurrentSession().delete(ul);
		return ul;
	}

	@Transactional(readOnly = true)
	public UserLeave get(DetachedCriteria crit) {
		return (UserLeave) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public UserLeave get(Long id) {
		return (UserLeave) sf.getCurrentSession().get(UserLeave.class, id);
	}

	@Transactional(readOnly = true)
	public List<Date> getWeekHolidays(Date from, Date to) {
		List<Date> weekHolidays = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		if (to == null || cal.after(to)) {
			return weekHolidays;
		}
		Set<WeekHoliday> whList = whService.findAll();
		for (; cal.getTime().getTime() <= to.getTime(); cal.add(Calendar.DATE,
				1)) {
			for (WeekHoliday wh : whList) {
				if (CalendarHelper.isOffDay(wh, cal.getTime())) {
					weekHolidays.add(cal.getTime());
					break;
				}
			}
		}
		return weekHolidays;
	}

	@Transactional(readOnly = true)
	public boolean isWeekHoliday(Date on) {
		if (on == null)
			return false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(on);
		Set<WeekHoliday> whList = whService.findAll();
		for (WeekHoliday wh : whList) {
			if (CalendarHelper.isOffDay(wh, cal.getTime())) {
				return true;
			}
		}
		return false;
	}

	public boolean isOfficeHoliday(Date on) {
		if (on == null)
			return false;
		Calendar cal = Calendar.getInstance();
		cal.setTime(on);
		DetachedCriteria dc = DetachedCriteria.forClass(OfficeHoliday.class);
		dc.add(Restrictions.eq("date", on));
		Set<OfficeHoliday> ohList = ohService.find(dc);
		if (!ohList.isEmpty()) {
			return true;
		}
		return false;
	}

	@Transactional(readOnly = true)
	public List<Date> getOfficeHolidays(Date from, Date to) {
		List<Date> officeHolidays = new ArrayList<Date>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(from);
		if (to == null || cal.after(to)) {
			return officeHolidays;
		}
		DetachedCriteria dc = DetachedCriteria.forClass(OfficeHoliday.class);
		dc.add(Restrictions.ge("date", from));
		Set<OfficeHoliday> ohList = ohService.find(dc);

		for (; cal.getTime().getTime() <= to.getTime(); cal.add(Calendar.DATE,
				1)) {
			for (OfficeHoliday oh : ohList) {
				if (oh.getDate().equals(cal.getTime())) {
					officeHolidays.add(cal.getTime());
					break;
				}
			}
		}
		return officeHolidays;
	}

	@Transactional(readOnly = true)
	public int getDays(Date from, Date to) {
		if (to == null || from.after(to)) {
			return 0;
		}
		Long diff = to.getTime() - from.getTime();
		Long days = diff / (1000 * 24 * 60 * 60) + 1L;
		return days.intValue();
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}

}
