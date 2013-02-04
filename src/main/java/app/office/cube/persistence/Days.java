/**
 * 
 */
package app.office.cube.persistence;

import java.util.Calendar;

/**
 * @author Cyril
 *
 */
public enum Days {
	MONDAY(Calendar.MONDAY),	TUESDAY(Calendar.TUESDAY), WEDNESDAY(Calendar.WEDNESDAY),	THURSDAY(Calendar.THURSDAY),
	FRIDAY(Calendar.FRIDAY),	SATURDAY(Calendar.SATURDAY), SUNDAY(Calendar.SUNDAY);
	private Days(int code) {
		this.code = code;
	}
	public int getCode(){
		return this.code;
	}
	public String getName(){
		return this.toString();
	}
	int code;
}
