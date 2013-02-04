/**
 * 
 */
package app.office.cube.excel;

import org.apache.poi.ss.usermodel.Sheet;

import app.office.cube.core.persistence.IPersistable;

/**
 * @author Cyril
 * 
 */
public interface IReport<T extends IPersistable> {
	public void fillReport(Sheet worksheet, T jdo);
}
