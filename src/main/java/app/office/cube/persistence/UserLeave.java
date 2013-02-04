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
public class UserLeave extends Persistable {
	private static final long serialVersionUID = 5615033063552518413L;
	private UserLeavePolicy userLeavePolicy;
	private float days;
	private HourlyLeave hour;
	private LeaveStatus status;
	@DateTimeFormat(pattern="d-M-yyyy")
	private Date startDate;
	@DateTimeFormat(pattern="d-M-yyyy")
	private Date endDate;
	private String description;
	
	
	public HourlyLeave getHour() {
		return hour;
	}
	public void setHour(HourlyLeave hour) {
		this.hour = hour;
	}
	
	public HourlyLeave[] getHours(){
		return HourlyLeave.values();
	}
	
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UserLeavePolicy getUserLeavePolicy() {
		return userLeavePolicy;
	}
	public void setUserLeavePolicy(UserLeavePolicy myUserLeavePolicy) {
		this.userLeavePolicy = myUserLeavePolicy;
	}
	public float getDays() {
		return days;
	}
	public void setDays(float days) {
		this.days = (days > 0) ? days : 0F;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public LeaveStatus getStatus() {
		return status;
	}
	public void setStatus(LeaveStatus status) {
		this.status = status;
	}
}
