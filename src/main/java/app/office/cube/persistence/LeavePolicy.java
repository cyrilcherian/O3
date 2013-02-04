/**
 * 
 */
package app.office.cube.persistence;

/**
 * @author Cyril
 *
 */
public class LeavePolicy extends Persistable {
	private static final long serialVersionUID = -6812943871003207033L;
	private String leaveType;
	private String description;

	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
