/**
 * 
 */
package app.office.cube.convertors;

import java.beans.PropertyEditorSupport;

import app.office.cube.helpers.RoleType;
import app.office.cube.persistence.Role;

/**
 * @author Cyril
 * 
 */
public class RolesTypeConvertor extends PropertyEditorSupport {
	public void setAsText(String text) {
		setValue(setRoles(text));
	}

	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}

	private Role setRoles(String role) {
		if ("".equals(role.trim())) {
			return null;
		}
		for (RoleType rt : RoleType.values()) {
			Role r = new Role();
			if (rt.getCode().equals(role)) {
				r.setRoleName(rt.getName());
				return r;
			}
		}
		throw new RuntimeException("Invalid RoleType!");
	}

}
