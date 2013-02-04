/**
 * 
 */
package app.office.cube.helpers;

/**
 * @author Cyril
 * 
 */
public enum RoleType {
	ROLE_USER(true, true), ROLE_SUPERVISOR(true, false), ROLE_TEAM_SUPERVISOR(true, false);
	
	private boolean checked = false;
	private boolean disabled = false;
	private long code;
	
	private RoleType(boolean checked, boolean disabled) {
		this.checked=checked;
		this.disabled=disabled;
	}
	public String getName() {
		return this.toString();
	}

	public String getCode() {
		return this.toString();
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
