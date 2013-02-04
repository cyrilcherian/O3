/**
 * 
 */
package app.office.cube.core.services;

import java.util.Date;
import java.util.List;

/**
 * @author cyril
 *
 */
public interface IUserLeaveService<UserLeave> extends IOfficeService<UserLeave>{
	public List<Date> getWeekHolidays(Date from, Date to);
	public List<Date> getOfficeHolidays(Date from, Date to);
	public int getDays(Date from, Date to);
	public boolean isOfficeHoliday(Date on);
	public boolean isWeekHoliday(Date on);
}
