/**
 * 
 */
package app.office.cube.formbean;

import java.util.LinkedHashSet;

/**
 * @author Cyril
 * 
 */
public class NotificationFormBean {

	private String summary;
	private String description;
	private Long[] notifiers;

	public Long[] getNotifiers() {
		return notifiers;
	}

	public void setUsers(Long[] notifiers) {
		this.notifiers = notifiers;
	}

	public LinkedHashSet<Long> getUserSet() {
		LinkedHashSet<Long> userSet = new LinkedHashSet<Long>();
		if (notifiers == null) {
			return userSet;
		}
		for (Long key : notifiers) {
			userSet.add(key);
		}
		return userSet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
