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
import app.office.cube.persistence.UserLeavePolicy;

/**
 * @author cyril
 * 
 */
@Service("userLeavePolicyService")
@Repository
public class UserLeavePolicyService implements IOfficeService<UserLeavePolicy> {
	private SessionFactory sf;
	private static final String COUNT = "select count(ulp) from UserLeavePolicy ulp";
	@Autowired
	public UserLeavePolicyService(
			@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<UserLeavePolicy> find(DetachedCriteria crit) {
		return new HashSet<UserLeavePolicy>(crit.getExecutableCriteria(sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<UserLeavePolicy> findAll() {
		return new HashSet<UserLeavePolicy>(sf.getCurrentSession().getNamedQuery("listUserLeavepolicy")
				.list());
	}

	@Transactional
	public UserLeavePolicy create(UserLeavePolicy o) {
		long id = (Long) sf.getCurrentSession().save(o);
		return (UserLeavePolicy) sf.getCurrentSession().get(
				UserLeavePolicy.class, id);
	}

	@Transactional
	public UserLeavePolicy update(UserLeavePolicy o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (UserLeavePolicy) sf.getCurrentSession().get(
				UserLeavePolicy.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public UserLeavePolicy get(DetachedCriteria crit) {
		return (UserLeavePolicy) crit.getExecutableCriteria(
				sf.getCurrentSession()).uniqueResult();
	}

	@Transactional(readOnly = true)
	public UserLeavePolicy get(Long id) {
		return (UserLeavePolicy) sf.getCurrentSession().get(
				UserLeavePolicy.class, id);
	}

	@Transactional
	public UserLeavePolicy delete(UserLeavePolicy ulp) {
		sf.getCurrentSession().delete(ulp);
		return ulp;
	}
	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}
}
