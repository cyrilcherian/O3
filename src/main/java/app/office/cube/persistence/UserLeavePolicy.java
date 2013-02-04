/**
 * 
 */
package app.office.cube.persistence;

/**
 * @author Cyril
 * 
 */
public class UserLeavePolicy extends Persistable {
	private static final long serialVersionUID = -5762081853504980990L;
	private LeavePolicy leavePolicy;
	private float daysLeaveAllowed;
	private User user;

	public LeavePolicy getLeavePolicy() {
		return leavePolicy;
	}

	public void setLeavePolicy(LeavePolicy myLeavePolicy) {
		this.leavePolicy = myLeavePolicy;
	}

	public float getDaysLeaveAllowed() {
		return daysLeaveAllowed;
	}

	public void setDaysLeaveAllowed(float daysLeaveAllowed) {
		this.daysLeaveAllowed = daysLeaveAllowed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User myUser) {
		this.user = myUser;
	}

}
