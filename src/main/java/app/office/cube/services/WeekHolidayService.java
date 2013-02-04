/**
 * 
 */
package app.office.cube.services;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.WeekHoliday;

/**
 * @author cyril
 * 
 */
@Service("weekHolidayService")
@Repository
public class WeekHolidayService implements IOfficeService<WeekHoliday> {
	private SessionFactory sf;
	private static final String COUNT = "select count(wh) from WeekHoliday wh";

	@Autowired
	public WeekHolidayService(
			@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<WeekHoliday> find(DetachedCriteria crit) {
		return new HashSet<WeekHoliday>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<WeekHoliday> findAll() {
		return new HashSet<WeekHoliday>(sf.getCurrentSession()
				.getNamedQuery("weekholidays").list());
	}

	@Transactional
	public WeekHoliday create(WeekHoliday o) {
		long id = (Long) sf.getCurrentSession().save(o);
		return (WeekHoliday) sf.getCurrentSession().get(WeekHoliday.class, id);
	}

	@Transactional
	public WeekHoliday update(WeekHoliday o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (WeekHoliday) sf.getCurrentSession().get(WeekHoliday.class,
				o.getMyKey());
	}

	@Transactional(readOnly = true)
	public WeekHoliday get(DetachedCriteria crit) {
		return (WeekHoliday) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	public WeekHoliday get(Long id) {
		return (WeekHoliday) sf.getCurrentSession().get(WeekHoliday.class, id);
	}

	@Transactional
	public WeekHoliday delete(WeekHoliday w) {
		sf.getCurrentSession().delete(w);
		return w;
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count = (Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}
}
