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
import app.office.cube.persistence.User;

/**
 * @author cyril
 * 
 */
@Service("usersService")
@Repository
public class UserService implements IOfficeService<User> {
	private SessionFactory sf;
	private static final String COUNT = "select count(u) from User u";
	@Autowired
	public UserService(@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}

	@Transactional(readOnly = true)
	public Set<User> find(DetachedCriteria crit) {
		return new HashSet<User>(crit.getExecutableCriteria(sf.getCurrentSession()).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Set<User> findAll() {
		return new HashSet<User>(sf.getCurrentSession().getNamedQuery("listUsers").list());
	}

	@Transactional
	public User create(User u) {
		long id = (Long) sf.getCurrentSession().save(u);
		return (User) sf.getCurrentSession().get(User.class, id);
	}

	@Transactional
	public User update(User o) {
		sf.getCurrentSession().saveOrUpdate(o);
		return (User) sf.getCurrentSession().get(User.class, o.getMyKey());
	}

	@Transactional(readOnly = true)
	public User get(DetachedCriteria crit) {
		return (User) crit.getExecutableCriteria(sf.getCurrentSession())
				.uniqueResult();
	}

	@Transactional(readOnly = true)
	public User get(Long id) {
		return (User) sf.getCurrentSession().get(User.class, id);
	}

	@Transactional
	public User delete(User u) {
		sf.getCurrentSession().delete(u);
		return u;
	}

	@Transactional(readOnly = true)
	public long getCount() {
		Long count =
				(Long) sf.getCurrentSession().createQuery(COUNT)
				.uniqueResult();

		return count;
	}

}
