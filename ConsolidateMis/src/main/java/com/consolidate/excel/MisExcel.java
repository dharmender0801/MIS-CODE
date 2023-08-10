package com.consolidate.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.consolidate.Model.Quiz2playMisModel;

public class MisExcel {

	
	
	
	public static void CreateExcelSheet(List<Country> list, Map<String, List<Quiz2playMisModel>> maplist) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet summarySheet;

		for (Country c : list) {
			XSSFSheet sheet = workbook.createSheet(c.getOperatorName());
			sheet = CreateExcel.SetSheet(sheet);
			for (Map.Entry<String, List<Quiz2playMisModel>> lisofData : maplist.entrySet()) {
				if (lisofData.getKey().equalsIgnoreCase(c.getOperatorName())) {
					Map<String, Object[]> qui2play = CreateTable(lisofData.getValue());
					fillExcel(sheet, qui2play, workbook);
				}
			}

		}

//		FileOutputStream out = new FileOutputStream(new File("C://Users/teams/Downloads/Reports.xlsx"));
		FileOutputStream out = new FileOutputStream(new File("/usr/shfiles/quiz2play_mis/zain_iq/Report.xlsx"));

		workbook.write(out);
		out.close();
		System.out.println("Excel file written successfully on disk.");
	}
	private static Map<String, Object[]> CreateTable(List<Quiz2playMisModel> list) {
		// TODO Auto-generated method stub
		Map<String, Object[]> data = new LinkedHashMap<String, Object[]>();
		int sno = 1;
		data.put("0",
				new Object[] { "Date", " Operator Name", "Active Base", "New Subscription", "Billed Activation",
						"New Billed Activation", "Renewal Activation", "Churn Count", "Invol Churn", "Vol Churn",
						"New Activation Revenue", "Renewal Revenue", "Total Revenue", "Mtd" });

		for (int i = 0; i < list.size(); i++) {
			Quiz2playMisModel mrb = list.get(i);
			data.put(Integer.toString(sno),
					new Object[] { mrb.getDate().toString(), mrb.getOperatorName(), mrb.getActiveBase(), mrb.getNewSubscription(),
							mrb.getBilledActivation(), mrb.getNewBilledActivation(), mrb.getRenewalActivation(),
							mrb.getChurnCount(), mrb.getInvolChurn(), mrb.getVolChurn(), mrb.getNewActivationRevenue(),
							mrb.getRenewalRevenue(), mrb.getTotalRevenue(), mrb.getMtd() });
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
