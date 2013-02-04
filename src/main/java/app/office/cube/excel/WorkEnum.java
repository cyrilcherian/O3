/**
 * 
 */
package app.office.cube.excel;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Cyril
 * 
 */
public enum WorkEnum {
	NAME("Name", 0), CORPORATE_ID("Corporate ID", 1), DATE("Date", 2), 
	PROJECT("Project", 3), TASK("task", 4), ISSUES("Issues", 5), 
	DESCRIPTION("Description", 6), HOURS("Hours", 7);
	
	private WorkEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public int getIndex() {
		return index;
	}

	public CellStyle getStyle(Sheet worksheet) {
		Font font = worksheet.getWorkbook().createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);

		// Create cell style for the headers
		CellStyle style = worksheet.getWorkbook().createCellStyle();
		style.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		style.setFillPattern(CellStyle.FINE_DOTS);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		style.setFont(font);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		return style;
	}

	private String name;
	private int index = 0;
}
