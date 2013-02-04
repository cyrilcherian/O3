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
import app.office.cube.persistence.Team;

/**
 * @author cyril
 * 
 */
@Service("teamService")
@Repository
public class TeamService implements IOfficeService<Team> {
	private SessionFactory sf;
	private static final String COUNT = "select count(t) from Team t";
	@Autowired
	public TeamService(@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<Team> find(DetachedCriteria crit) {
		return new HashSet<Team>(crit.getExecutableCriteria(
				sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<Team> findAll() {
		return new HashSet<Team>(sf.getCurrentSession()
				.getNamedQuery("listTeams").list());
	}

	@Transactional
	public Team create(Team o) {
		long id = (Long) sf.getCurrentSession().save(o);
		return (Team) sf.getCurrentSession().get(Team.class, id);
	}

	@Transactional
	public Team update(Team o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (Team) sf.getCurrentSession().get(Team.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public Team get(DetachedCriteria crit) {
		return (Team) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public Team get(Long id) {
		return (Team) sf.getCurrentSession().get(Team.class, id);
	}

	@Transactional
	public Team delete(Team t) {
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
