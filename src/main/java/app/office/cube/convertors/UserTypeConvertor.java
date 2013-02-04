/**
 * 
 */
package app.office.cube.convertors;

import java.util.Set;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.User;

/**
 * @author Cyril
 * 
 */
public class UserTypeConvertor extends CustomCollectionEditor {
	private IOfficeService<User> userService;

	public UserTypeConvertor(IOfficeService<User> userService) {
		super(Set.class);
		this.userService = userService;
	}

	@Override
	protected Object convertElement(Object element) {
		if (element == null) {
			throw new RuntimeException("Invalid User!");
		}
		if ("".equals(element.toString().trim())) {
			throw new RuntimeException("Invalid User!");
		}
		return userService.get(Long.parseLong(element.toString()));
	}

	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}

}
