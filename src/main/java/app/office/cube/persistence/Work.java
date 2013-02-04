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
public class Work extends Persistable {
	private static final long serialVersionUID = -5762001853504980990L;
	private Task task;
	private Project project;
	private User user;
	private String description;
	private String issues;
	private float hours;
	@DateTimeFormat(pattern="d-M-yyyy")
	private Date date;

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIssues() {
		return issues;
	}

	public void setIssues(String issues) {
		this.issues = issues;
	}

	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
