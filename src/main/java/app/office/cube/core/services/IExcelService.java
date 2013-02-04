/**
 * 
 */
package app.office.cube.core.services;

import java.util.Date;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Cyril
 *
 */
public interface IExcelService<T> extends IOfficeService<T> {
	public Workbook generateReport(Set<Long> users, Date startDate, Date endDates);
}
