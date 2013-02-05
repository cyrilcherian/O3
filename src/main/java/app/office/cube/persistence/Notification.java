/**
 * 
 */
package app.office.cube.persistence;

/**
 * @author Cyril
 * 
 */
public class Notification extends Persistable {
	private static final long serialVersionUID = -5848453516147129011L;
	private String summary;
	private String description;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User myUser) {
		this.user = myUser;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
