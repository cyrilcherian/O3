/**
 * 
 */
package app.office.cube.convertors;

import java.beans.PropertyEditorSupport;

import app.office.cube.persistence.Days;

/**
 * @author Cyril
 *
 */
public class DaysTypeConvertor extends PropertyEditorSupport {
	public void setAsText(String text) {
		setValue(setDay(text));
	}
	@Override
	public boolean supportsCustomEditor() {
		return super.supportsCustomEditor();
	}
	private Days setDay(String day) {
		if ("".equals(day.trim())) {
			return null;
		}
		for (Days lDay : Days.values()){
			if (Integer.parseInt(day) == lDay.getCode()){
				return lDay;
			}
		}
		throw new RuntimeException("Invalid Day!");
	}

}
