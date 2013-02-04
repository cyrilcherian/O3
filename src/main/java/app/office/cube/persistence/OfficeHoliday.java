/**
 * 
 */
package app.office.cube.persistence;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Cyril
 *
 */
public class OfficeHoliday extends Persistable {
	private static final long serialVersionUID = 1484311842744063588L;
	private String description;
	@DateTimeFormat(pattern="d-M-yyyy")
	private Date date;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
