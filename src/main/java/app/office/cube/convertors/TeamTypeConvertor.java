/**
 * 
 */
package app.office.cube.convertors;

import java.util.Set;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.Team;

/**
 * @author Cyril
 * 
 */
public class TeamTypeConvertor extends CustomCollectionEditor {
	private IOfficeService<Team> teamService;

	public TeamTypeConvertor(IOfficeService<Team> teamService) {
		super(Set.class);
		this.teamService = teamService;
	}

	@Override
	protected Object convertElement(Object element) {
		if (element == null) {
			throw new RuntimeException("Invalid Team!");
		}
		if ("".equals(element.toString().trim())) {
			throw new RuntimeException("Invalid Team!");
		}
		return teamService.get(Long.parseLong(element.toString()));
	}

	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}

}
