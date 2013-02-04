/**
 * 
 */
package app.office.cube.formbean;

/**
 * @author Cyril
 * 
 */
public class UserLeavePolicyFormBean {

	private Long leavePolicy;
	private float daysLeaveAllowed;
	private Long[] users;

	public Long getLeavePolicy() {
		return leavePolicy;
	}

	public void setLeavePolicy(Long leavePolicy) {
		this.leavePolicy = leavePolicy;
	}

	public float getDaysLeaveAllowed() {
		return daysLeaveAllowed;
	}

	public void setDaysLeaveAllowed(float daysLeaveAllowed) {
		this.daysLeaveAllowed = daysLeaveAllowed;
	}

	public Long[] getUsers() {
		return users;
	}

	public void setUsers(Long[] users) {
		this.users = users;
	}
}
