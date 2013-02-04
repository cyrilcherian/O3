/**
 * 
 */
package app.office.cube.excel;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Cyril
 * 
 */
public interface ILayouter {
	public void buildTitle(Sheet worksheet);

	public void buildHeaders(Sheet worksheet, int startRowIndex,
			int startColIndex);
}
