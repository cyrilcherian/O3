/**
 * 
 */
package app.office.cube.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import app.office.cube.persistence.Work;

/**
 * @author Cyril
 * 
 */
public class WorkReporter implements IReport<Work> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * app.office.cube.excel.IReport#fillReport(org.apache.poi.hssf.usermodel
	 * .HSSFSheet, app.office.cube.core.persistence.IPersistable, int)
	 */
	public void fillReport(Sheet worksheet, Work work) {
		CellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellStyle.setWrapText(true);
		// Create a new row
		Row sRow = worksheet.createRow((short) (worksheet.getLastRowNum() + 1));
		Cell cell = sRow.createCell(WorkEnum.NAME.getIndex());
		cell.setCellValue(work.getUser().getDisplayName());
		cell.setCellStyle(bodyCellStyle);
		cell = sRow.createCell(WorkEnum.CORPORATE_ID.getIndex());
		cell.setCellValue(work.getUser().getCorporateID());
		cell.setCellStyle(bodyCellStyle);
		cell = sRow.createCell(WorkEnum.DATE.getIndex());
		CellStyle cellStyle = worksheet.getWorkbook().createCellStyle();
		short code = worksheet.getWorkbook().getCreationHelper().createDataFormat().getFormat("d/M/yyyy");
		cellStyle.setDataFormat(code);
		//String str = String.format("%1$td-%1$tm-%1$tY", jdo.getDate());
		cell.setCellValue(work.getDate());
		cell.setCellStyle(cellStyle);
		cell = sRow.createCell(WorkEnum.PROJECT.getIndex());
		cell.setCellValue(work.getProject().getName());
		cell.setCellStyle(bodyCellStyle);
		cell = sRow.createCell(WorkEnum.TASK.getIndex());
		cell.setCellValue(work.getTask().getName());
		cell.setCellStyle(bodyCellStyle);
		cell = sRow.createCell(WorkEnum.ISSUES.getIndex());
		cell.setCellValue(work.getIssues());
		cell.setCellStyle(bodyCellStyle);
		cell = sRow.createCell(WorkEnum.DESCRIPTION.getIndex());
		cell.setCellValue(work.getDescription());
		cell.setCellStyle(bodyCellStyle);
		cell = sRow.createCell(WorkEnum.HOURS.getIndex());
		cell.setCellValue(work.getHours());
		cell.setCellStyle(bodyCellStyle);
	}

}
