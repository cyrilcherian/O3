/**
 * 
 */
package app.office.cube.convertors;

import java.beans.PropertyEditorSupport;

import app.office.cube.core.services.IOfficeService;
import app.office.cube.persistence.Task;

/**
 * @author Cyril
 * 
 */
public class TaskConvertor extends PropertyEditorSupport {
	private IOfficeService<Task> taskService;

	public TaskConvertor(IOfficeService<Task> taskService) {
		super();
		this.taskService = taskService;
	}
	
	public void setAsText(String text) {
		setValue(setTask(text));
	}

	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}

	private Task setTask(String element) {
		if (element == null) {
			return null;
		}
		if ("".equals(element.toString().trim())) {
			return null;
		}
		return taskService.get(Long.parseLong(element.toString()));
	}

}
