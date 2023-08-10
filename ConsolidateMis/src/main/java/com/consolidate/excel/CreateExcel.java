package com.consolidate.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.consolidate.Model.Country;
import com.consolidate.Model.MisColumn;

public class CreateExcel {

	public static XSSFSheet SetSheet(XSSFSheet sheet) {
		// TODO Auto-generated method stub

		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 4000);
		sheet.setColumnWidth(15, 4000);
		sheet.setColumnWidth(16, 4000);
		sheet.setColumnWidth(17, 4000);
		sheet.setColumnWidth(18, 4000);
		sheet.setColumnWidth(19, 4000);
		sheet.setColumnWidth(20, 4000);
		sheet.setColumnWidth(21, 4000);
		sheet.setColumnWidth(22, 4000);
		sheet.setColumnWidth(23, 4000);
		sheet.setColumnWidth(24, 4000);
		sheet.setColumnWidth(25, 4000);
		sheet.setColumnWidth(26, 4000);
		sheet.setColumnWidth(27, 4000);
		sheet.setColumnWidth(28, 4000);
		sheet.setColumnWidth(29, 4000);
		sheet.setColumnWidth(30, 4000);
		sheet.setColumnWidth(31, 4000);
		sheet.setColumnWidth(32, 4000);
		sheet.setColumnWidth(33, 4000);
		sheet.setColumnWidth(34, 4000);
		return sheet;
	}

	public void CreateExcelSheet(List<Country> list, Map<String, List<MisColumn>> maplist) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet summarySheet;

		for (Country c : list) {
			XSSFSheet sheet = workbook.createSheet(c.getOperatorName());
			sheet = SetSheet(sheet);
			for (Map.Entry<String, List<MisColumn>> lisofData : maplist.entrySet()) {
				if (lisofData.getKey().equalsIgnoreCase(c.getOperatorName())) {
					Map<String, Object[]> qui2play = CreateTable(lisofData.getValue());
					fillExcel(sheet, qui2play, workbook);
				}
			}

		}

		FileOutputStream out = new FileOutputStream(new File("D://VendorWap.xlsx"));

		workbook.write(out);
		out.close();
		System.out.println("Excel file written successfully on disk.");

	}

	private static Map<String, Object[]> CreateTable(List<MisColumn> list) {
		// TODO Auto-generated method stub
		Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
		int sno = 1;
		data.put("0", new Object[] { "Date", "Country ", " Operator Name", "Total Hit", "Unique Hit", "Same Day Billed",
				"Activation", "Cpid", "Vendor Name", "Postback Sent" });

		for (int i = 0; i < list.size(); i++) {
			MisColumn mrb = list.get(i);
			data.put(Integer.toString(sno),
					new Object[] { mrb.getProcessDate().toString(), mrb.getCountryName(), mrb.getOperatorName(),
							mrb.getTotalHits(), mrb.getUniqueHits(), mrb.getSameDayBilled(), mrb.getActivation(),
							mrb.getCpId(), mrb.getVendorName(), mrb.getPostbackSent() });
			sno++;
		}
		return data;

	}

	private static void fillExcel(XSSFSheet sheet, Map<String, Object[]> data, XSSFWorkbook workbook) {
		Set<String> keyset = data.keySet();
		int rownum = 0;
		Row row = null;
		try {
			for (String key : keyset) {
				row = sheet.createRow(rownum++);
				Object[] objArr = data.get(key);
				int cellnum = 0;
				XSSFCellStyle style = null;
				if (key == "0") {
					XSSFFont font = workbook.createFont();
					font.setFontHeightInPoints((short) 12);
					font.setFontName("CALIBRI");
					font.setBold(true);
//					font.setColor(HSSFColor.BLACK.index);

					// Set font into style
					style = workbook.createCellStyle();
					style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
					style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
					style.setFont(font);
					style.setWrapText(false);
				} else {
					style = workbook.createCellStyle();
					style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
					style.setBorderRight(BorderStyle.THIN);
					style.setBorderLeft(BorderStyle.THIN);
					style.setBorderBottom(BorderStyle.THIN);
					style.setBorderTop(BorderStyle.THIN);
					style.setWrapText(false);
				}
				for (Object obj : objArr) {
					Cell cell = row.createCell(cellnum++);
					if (obj instanceof String) {
						cell.setCellValue((String) obj);
						cell.setCellStyle(style);
					} else if (obj instanceof Integer) {
						cell.setCellValue((Integer) obj);
						cell.setCellValue((Integer) obj);
						cell.setCellStyle(style);
						style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
						style.setBorderRight(BorderStyle.THIN);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
