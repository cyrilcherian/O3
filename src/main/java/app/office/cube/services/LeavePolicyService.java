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
import app.office.cube.persistence.LeavePolicy;

/**
 * @author cyril
 * 
 */
@Service("leavePolicyService")
@Repository
public class LeavePolicyService implements IOfficeService<LeavePolicy> {
	private SessionFactory sf;
	private static final String COUNT = "select count(lp) from LeavePolicy";
	@Autowired
	public LeavePolicyService(
			@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<LeavePolicy> find(DetachedCriteria crit) {
		return new HashSet<LeavePolicy>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<LeavePolicy> findAll() {
		return new HashSet<LeavePolicy>(sf.getCurrentSession()
				.getNamedQuery("listOfficePolicy").list());
	}

	@Transactional
	public LeavePolicy create(LeavePolicy o) {
		long id = (Long) sf.getCurrentSession().save(o);
		return (LeavePolicy) sf.getCurrentSession().get(LeavePolicy.class, id);
	}

	@Transactional
	public LeavePolicy update(LeavePolicy o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (LeavePolicy) sf.getCurrentSession().get(LeavePolicy.class,
				o.getMyKey());
	}

	@Transactional(readOnly = true)
	public LeavePolicy get(DetachedCriteria crit) {
		return (LeavePolicy) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public LeavePolicy get(Long id) {
		return (LeavePolicy) sf.getCurrentSession().get(LeavePolicy.class, id);
	}

	@Transactional
	public LeavePolicy delete(LeavePolicy lp) {
		sf.getCurrentSession().delete(lp);
		return lp;
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}

}
