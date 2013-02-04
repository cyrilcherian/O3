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
import app.office.cube.persistence.OfficeHoliday;

/**
 * @author cyril
 * 
 */
@Service("officeHolidayService")
@Repository
public class OfficeHolidayService implements IOfficeService<OfficeHoliday> {
	private SessionFactory sf;
	private static final String COUNT = "select count(oh) from OfficeHoliday oh";

	@Autowired
	public OfficeHolidayService(
			@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<OfficeHoliday> find(DetachedCriteria crit) {
		return new HashSet<OfficeHoliday>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<OfficeHoliday> findAll() {
		return new HashSet<OfficeHoliday>(sf.getCurrentSession()
				.getNamedQuery("listOfficeHoliday").list());
	}

	@Transactional
	public OfficeHoliday create(OfficeHoliday o) {
		long id = (Long) sf.getCurrentSession().save(o);
		return (OfficeHoliday) sf.getCurrentSession().get(OfficeHoliday.class,
				id);
	}

	@Transactional
	public OfficeHoliday update(OfficeHoliday o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (OfficeHoliday) sf.getCurrentSession().get(OfficeHoliday.class,
				o.getMyKey());
	}

	@Transactional(readOnly = true)
	public OfficeHoliday get(DetachedCriteria crit) {
		return (OfficeHoliday) crit.getExecutableCriteria(
				sf.getCurrentSession()).uniqueResult();
	}

	public OfficeHoliday get(Long id) {
		return (OfficeHoliday) sf.getCurrentSession().get(OfficeHoliday.class,
				id);
	}

	@Transactional
	public OfficeHoliday delete(OfficeHoliday oh) {
		sf.getCurrentSession().delete(oh);
		return oh;
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count = (Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}

}
