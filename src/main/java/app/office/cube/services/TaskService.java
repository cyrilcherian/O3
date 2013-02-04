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
import app.office.cube.persistence.Task;

/**
 * @author cyril
 * 
 */
@Service("tasksService")
@Repository
public class TaskService implements IOfficeService<Task> {
	private SessionFactory sf;
	private static final String COUNT = "select count(t) from Task t";
	@Autowired
	public TaskService(@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<Task> find(DetachedCriteria crit) {
		return new HashSet<Task>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<Task> findAll() {
		return new HashSet<Task>(sf.getCurrentSession()
				.getNamedQuery("listTasks").list());
	}

	@Transactional
	public Task create(Task u) {
		long id = (Long) sf.getCurrentSession().save(u);
		return (Task) sf.getCurrentSession().get(Task.class, id);
	}

	@Transactional
	public Task update(Task o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (Task) sf.getCurrentSession().get(Task.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public Task get(DetachedCriteria crit) {
		return (Task) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Task get(Long id) {
		return (Task) sf.getCurrentSession().get(Task.class, id);
	}

	@Transactional
	public Task delete(Task t) {
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
