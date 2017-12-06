package utils;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import models.CanBo;
import models.PhongThi;

public class WriteExcelFile {
	private Workbook workbook;

	private void createHeaderRow(Sheet sheet) {
		Row row = sheet.createRow(0);
		Cell c0 = row.createCell(0);

		c0.setCellValue("ma_phong_thi");

		Cell c1 = row.createCell(1);
		c1.setCellValue("ho_ten_gt_1");

		Cell c2 = row.createCell(2);
		c2.setCellValue("ma_gt_1");

		Cell c3 = row.createCell(3);
		c3.setCellValue("ho_ten_gt_2");

		Cell c4 = row.createCell(4);
		c4.setCellValue("ma_gt_2");

	}

	private void writePT(PhongThi pt, Row row) {
		Cell cell = row.createCell(0);
		Iterator<CanBo> iCbs = pt.getListCB().iterator();
		
		// Ma phong thi
		cell.setCellValue(pt.getMaPhongThi());

		// Giam thi 1
		cell = row.createCell(1);
		CanBo gt1 = iCbs.next();
		cell.setCellValue(gt1.getHoTen());

		cell = row.createCell(2);
		cell.setCellValue(gt1.getMaSo());

		// Giam thi 2
		cell = row.createCell(3);
		CanBo gt2 = iCbs.next();
		cell.setCellValue(gt2.getHoTen());

		cell = row.createCell(4);
		cell.setCellValue(gt2.getMaSo());
		
		int i = 5;
		
		while(iCbs.hasNext()) {
			CanBo gk = iCbs.next();
			cell = row.createCell(i);
			cell.setCellValue(gk.getHoTen());
			
			cell = row.createCell(i+1);
			cell.setCellValue(gk.getMaSo());
			
			i = i + 2;
		}
	}

	public void writeExcel(List<PhongThi> listPT, String excelFilePath) throws IOException {
		System.out.println("Creating excel file.");
		workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		createHeaderRow(sheet);

		int rowCount = 0;

		for (PhongThi pt : listPT) {
			Row row = sheet.createRow(++rowCount);
			writePT(pt, row);
		}

		try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
			workbook.write(outputStream);
		}

		System.out.println("Done Creat excel file.");
	}

	public void writeExcel(List<PhongThi> listPT, DataOutputStream out) throws IOException {
		System.out.println("Creating excel file.");
		workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		createHeaderRow(sheet);

		int rowCount = 0;

		for (PhongThi pt : listPT) {
			Row row = sheet.createRow(++rowCount);
			writePT(pt, row);
		}

		workbook.write(out);
		System.out.println("Done Creat excel file.");
	}
}
