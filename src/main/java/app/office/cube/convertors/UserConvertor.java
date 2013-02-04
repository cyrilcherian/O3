/**
 * 
 */
package app.office.cube.convertors;

import java.beans.PropertyEditorSupport;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.User;

/**
 * @author Cyril
 * 
 */
public class UserConvertor extends PropertyEditorSupport {
	private IOfficeService<User> userService;

	public UserConvertor(IOfficeService<User> userService) {
		super();
		this.userService = userService;
	}
	
	public void setAsText(String text) {
		setValue(setUser(text));
	}

	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}

	private User setUser(String element) {
		if (element == null) {
			return null;
		}
		if ("".equals(element.toString().trim())) {
			return null;
		}
		return userService.get(Long.parseLong(element.toString()));
	}

}
