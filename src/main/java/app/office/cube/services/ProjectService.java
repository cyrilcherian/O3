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
import app.office.cube.persistence.Project;

/**
 * @author cyril
 * 
 */
@Service("projectsService")
@Repository
public class ProjectService implements IOfficeService<Project> {
	private SessionFactory sf;
	private static final String COUNT = "select count(p) from Project p";
	@Autowired
	public ProjectService(
			@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<Project> find(DetachedCriteria crit) {
		return new HashSet<Project>(crit.getExecutableCriteria(sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<Project> findAll() {
		return new HashSet<Project>(sf.getCurrentSession().getNamedQuery("listProjects").list());
	}

	@Transactional
	public Project create(Project u) {
		long id = (Long) sf.getCurrentSession().save(u);
		return (Project) sf.getCurrentSession().get(Project.class, id);
	}

	@Transactional
	public Project update(Project o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (Project) sf.getCurrentSession()
				.get(Project.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public Project get(DetachedCriteria crit) {
		return (Project) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Project get(Long id) {
		return (Project) sf.getCurrentSession().get(Project.class, id);
	}

	@Transactional
	public Project delete(Project p) {
		sf.getCurrentSession().delete(p);
		return p;
	}
	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}

}
