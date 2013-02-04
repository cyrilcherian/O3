/**
 * 
 */
package app.office.cube.convertors;

import java.beans.PropertyEditorSupport;

import app.office.cube.persistence.Frequency;

/**
 * @author Cyril
 *
 */
public class FrequencyTypeConvertor extends PropertyEditorSupport {
	public void setAsText(String text) {
		setValue(setFrequency(text));
	}
	public Frequency setFrequency(String frequency) {
		if ("".equals(frequency.trim())) {
			return null;
		}

		for (Frequency freq : Frequency.values()){
			if (freq.getCode().equals(frequency)){
				return freq;
			}
		}
		throw new RuntimeException("Invalid Frequency!");
	}

}
