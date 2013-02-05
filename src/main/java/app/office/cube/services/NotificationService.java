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
import app.office.cube.persistence.Notification;

/**
 * @author cyril
 * 
 */
@Service("notificationService")
@Repository
public class NotificationService implements IOfficeService<Notification> {
	private SessionFactory sf;
	private static final String COUNT = "select count(n) from Notification n";
	@Autowired
	public NotificationService(@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<Notification> find(DetachedCriteria crit) {
		return new HashSet<Notification>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<Notification> findAll() {
		return new HashSet<Notification>(sf.getCurrentSession()
				.getNamedQuery("listNotifications").list());
	}

	@Transactional
	public Notification create(Notification o) {
		long id = (Long) sf.getCurrentSession().save(o);
		return (Notification) sf.getCurrentSession().get(Notification.class, id);
	}

	@Transactional
	public Notification update(Notification o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (Notification) sf.getCurrentSession().get(Notification.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public Notification get(DetachedCriteria crit) {
		return (Notification) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Notification get(Long id) {
		return (Notification) sf.getCurrentSession().get(Notification.class, id);
	}

	@Transactional
	public Notification delete(Notification t) {
		sf.getCurrentSession().delete(t);
		return t;
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}
}
