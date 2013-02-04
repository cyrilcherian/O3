package app.office.cube.listeners;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.helpers.RoleType;
import app.office.cube.persistence.Role;
import app.office.cube.persistence.User;

public class MyApplicationListener implements
		ApplicationListener<ContextRefreshedEvent> {
	
	public MyApplicationListener() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		@SuppressWarnings("unchecked")
		IOfficeService<User> ws = ((IOfficeService<User>) event
				.getApplicationContext().getBean("usersService"));
		
		if (ws.getCount() == 0) {
			User u = new User();
			u.setFirstName("Admin");
			u.setLastName("Admin");
			u.setUserName("admin");
			u.setCorporateID("AD1");
			u.setEmail("admin@nowhere.com");
			Md5PasswordEncoder enc = new Md5PasswordEncoder();
			u.setPassword(enc.encodePassword("adm1n", null));
			Set<Role> roles = new HashSet<Role>();
			Role r = new Role();
			r.setUser(u);
			r.setRoleName(RoleType.ROLE_USER.getName());
			roles.add(r);
			r = new Role();
			r.setUser(u);
			r.setRoleName(RoleType.ROLE_SUPERVISOR.getName());
			roles.add(r);
			r = new Role();
			r.setUser(u);
			r.setRoleName(RoleType.ROLE_TEAM_SUPERVISOR.getName());
			roles.add(r);
			u.setRoles(roles);
			ws.create(u);
		}
	}
}
