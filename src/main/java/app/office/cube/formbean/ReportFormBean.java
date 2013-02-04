/**
 * 
 */
package app.office.cube.formbean;

import java.util.Date;
import java.util.LinkedHashSet;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Cyril
 * 
 */
public class ReportFormBean {
	@DateTimeFormat(pattern="d-M-yyyy")
	private Date from;
	@DateTimeFormat(pattern="d-M-yyyy")
	private Date to;
	private Long[] users;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Long[] getUsers() {
		return users;
	}

	public void setUsers(Long[] users) {
		this.users = users;
	}

	public LinkedHashSet<Long> getUserSet() {
		LinkedHashSet<Long> userSet = new LinkedHashSet<Long>();
		if (users == null) {
			return userSet;
		}
		for (Long key : users) {
			userSet.add(key);
		}
		return userSet;
	}
}
