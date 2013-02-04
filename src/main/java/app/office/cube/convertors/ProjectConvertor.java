/**
 * 
 */
package app.office.cube.convertors;

import java.beans.PropertyEditorSupport;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.Project;

/**
 * @author Cyril
 * 
 */
public class ProjectConvertor extends PropertyEditorSupport {
	private IOfficeService<Project> projectService;

	public ProjectConvertor(IOfficeService<Project> ProjectService) {
		super();
		this.projectService = ProjectService;
	}
	
	public void setAsText(String text) {
		setValue(setProject(text));
	}

	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}

	private Project setProject(String element) {
		if (element == null) {
			return null;
		}
		if ("".equals(element.toString().trim())) {
			return null;
		}
		return projectService.get(Long.parseLong(element.toString()));
	}

}
