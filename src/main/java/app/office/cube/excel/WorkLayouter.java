/**
 * 
 */
package app.office.cube.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * @author Cyril
 * 
 */
public class WorkLayouter implements ILayouter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * app.office.cube.excel.ILayouter#buildTitle(org.apache.poi.hssf.usermodel
	 * .HSSFSheet, int, int)
	 */
	public void buildTitle(Sheet worksheet) {
		worksheet.setColumnWidth(WorkEnum.NAME.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.CORPORATE_ID.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.DATE.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.PROJECT.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.TASK.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.ISSUES.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.DESCRIPTION.getIndex(), 5000);
		worksheet.setColumnWidth(WorkEnum.HOURS.getIndex(), 5000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * app.office.cube.excel.ILayouter#buildHeaders(org.apache.poi.hssf.usermodel
	 * .HSSFSheet, int, int)
	 */
	public void buildHeaders(Sheet worksheet, int startRowIndex,
			int startColIndex) {

		// Create the column headers
		Row rowHeader = worksheet.createRow((short) startRowIndex);
		rowHeader.setHeight((short) 500);
		// Create the header
		createCell(WorkEnum.NAME, rowHeader);
		createCell(WorkEnum.CORPORATE_ID, rowHeader);
		createCell(WorkEnum.DATE, rowHeader);
		createCell(WorkEnum.PROJECT, rowHeader);
		createCell(WorkEnum.TASK, rowHeader);
		createCell(WorkEnum.ISSUES, rowHeader);
		createCell(WorkEnum.DESCRIPTION, rowHeader);
		createCell(WorkEnum.HOURS, rowHeader);
	}

	private void createCell(WorkEnum we, Row rowHeader) {
		Cell cell = rowHeader.createCell(we.getIndex());

		cell.setCellValue(we.getName());
		cell.setCellStyle(we.getStyle(rowHeader.getSheet()));
	}
}
