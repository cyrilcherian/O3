/**
 * 
 */
package app.office.cube.helpers;

import java.util.Calendar;
import java.util.Date;

import app.office.cube.persistence.Days;
import app.office.cube.persistence.Frequency;
import app.office.cube.persistence.WeekHoliday;

/**
 * @author Cyril
 *
 */
public class LeaveHelper {
	public static boolean isOffDay(WeekHoliday wh, Date date) {
		if (wh.getFrequency().equals(Frequency.EVERY)){
			return isEvery(wh.getDay(), date);
		} else if (wh.getFrequency().equals(Frequency.FIRST)){
			return inWeek(wh.getDay(), date, 1);
		} else if (wh.getFrequency().equals(Frequency.SECOND)){
			return inWeek(wh.getDay(), date, 2);
		} else if (wh.getFrequency().equals(Frequency.THIRD)){
			return inWeek(wh.getDay(), date, 3);
		} else if (wh.getFrequency().equals(Frequency.FOURTH)){
			return inWeek(wh.getDay(), date, 4);
		}  else if (wh.getFrequency().equals(Frequency.FIFTH)){
			return inWeek(wh.getDay(), date, 5);
		}else if (wh.getFrequency().equals(Frequency.LAST)){
			return isLast(wh.getDay(), date);
		}
		return false;
	}
	private static boolean isEvery (Days day, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int i = cal.get(Calendar.DAY_OF_WEEK);
		if (day.getCode() == i){
			return true;
		}
		return false;
	}
	private static boolean inWeek (Days day, Date date, int weekOfMonth) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayCode = cal.get(Calendar.DAY_OF_WEEK);
		int weekIndex = cal.get(Calendar.WEEK_OF_MONTH);
		if (day.getCode() == dayCode && (weekIndex == weekOfMonth)) {
			return true;
		}
		return false;
	}
	private static boolean isLast (Days day, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int dayCode = cal.get(Calendar.DAY_OF_WEEK);
		int monthCode = cal.get(Calendar.MONTH);
		cal.add(Calendar.DATE, 7);
		int recMonthCode = cal.get(Calendar.MONTH);
		if (day.getCode() == dayCode && recMonthCode != monthCode) {
			return true;
		}
		return false;
	}
}
