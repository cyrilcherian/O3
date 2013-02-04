/**
 * 
 */
package app.office.cube.services;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.office.cube.core.services.IRoleService;
import app.office.cube.persistence.Role;

/**
 * @author cyril
 * 
 */
@Service("rolesService")
@Repository
public class RoleService implements IRoleService<Role> {
	private SessionFactory sf;

	@Autowired
	public RoleService(@Qualifier(value = "mySessionFactory") SessionFactory sf) {
		this.sf = sf;
	}
	@Transactional
	public void delete(Role r) {
		sf.getCurrentSession().delete(r);
	}

}
