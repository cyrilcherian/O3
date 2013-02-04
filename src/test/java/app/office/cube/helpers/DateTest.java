/**
 * 
 */
package app.office.cube.helpers;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @author Cyril
 *
 */
public class DateTest {

	@Test
	public void test() {
		Date date = new Date();
		String str = String.format("%1$td-%1$tm-%1$tY", date);
		
	}
	@Test
	public void anotherTest() {
		Date d = new Date();
		System.out.println(d);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DATE, -17);
		 d = c.getTime();
		System.out.println(d);
	}

}
