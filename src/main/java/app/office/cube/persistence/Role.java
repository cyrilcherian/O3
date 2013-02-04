/**
 * 
 */
package app.office.cube.persistence;

/**
 * @author Cyril
 *
 */
public class Role  extends Persistable{
	private static final long serialVersionUID = 1356585981055825461L;
	private String roleName;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
