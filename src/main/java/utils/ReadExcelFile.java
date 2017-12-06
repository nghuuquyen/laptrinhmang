package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import business.CanBoManager;
import business.PhongThiManager;
import models.CanBo;
import models.PhongThi;

public class ReadExcelFile {
	private static final String FILE_NAME = "/tmp/ds_canbo_va_phongthi.xlsx";
	private static Workbook workbook;

	private static Object getCellValue(Cell cell) {
		DataFormatter formatter = new DataFormatter();
		
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue() + "";
		}

		return null;
	}

	public List<CanBo> getListCB(InputStream inputStream) {
		List<CanBo> listCB = new ArrayList<>();
		try {
			workbook = new XSSFWorkbook(inputStream);
			Sheet canBoSheet = workbook.getSheetAt(0);

			Iterator<Row> cbIterator = canBoSheet.iterator();

			if (cbIterator.hasNext())
				cbIterator.next();

			// Read List Can Bo
			while (cbIterator.hasNext()) {

				Row currentRow = cbIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				CanBo cb = new CanBo();

				while (cellIterator.hasNext()) {

					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();
					switch (columnIndex) {
					case 0:
						cb.setMaSo(String.valueOf(getCellValue(nextCell)));
						break;
					case 1:
						cb.setHoTen(String.valueOf(getCellValue(nextCell)));
						break;
					case 2:
						cb.setTruong(String.valueOf(getCellValue(nextCell)));
						break;
					}
				}

				listCB.add(cb);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listCB;
	}

	public List<PhongThi> getListPT(InputStream inputStream) {
		List<PhongThi> listPT = new ArrayList<>();
		try {
			workbook = new XSSFWorkbook(inputStream);
			Sheet phongThiSheet = workbook.getSheetAt(1);

			Iterator<Row> ptIterator = phongThiSheet.iterator();

			if (ptIterator.hasNext())
				ptIterator.next();

			// Read List Phong Thi
			while (ptIterator.hasNext()) {

				Row currentRow = ptIterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				PhongThi pt = new PhongThi();

				while (cellIterator.hasNext()) {

					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 0:
						pt.setMaPhongThi(String.valueOf(getCellValue(nextCell)));
						break;
					}
				}

				listPT.add(pt);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listPT;
	}

	public static void main(String[] args) throws Exception {

		ReadExcelFile app = new ReadExcelFile();

		try {
			CanBoManager cbMgm = new CanBoManager(new FileInputStream(new File(FILE_NAME)));
			PhongThiManager ptMgm = new PhongThiManager(new FileInputStream(new File(FILE_NAME)));

			List<CanBo> cbs = cbMgm.getRandomListCB();
			List<PhongThi> pts = ptMgm.getRandomListPT();
			
			System.out.println("So Phong Thi: " + pts.size());
			System.out.println("So Can Bo: " + cbs.size());
			
			if (pts.size() * 2 > cbs.size()) {
				throw new Exception("So Can Bo Khong Du Cho Phong Thi");
			}

			Iterator<CanBo> iCbs = cbs.iterator();

			for (PhongThi pt : pts) {
				if (iCbs.hasNext()) {
					CanBo gt_1 = iCbs.next();
					gt_1.setRole("GT_1");
					pt.getListCB().add(gt_1);
				}

				if (iCbs.hasNext()) {
					CanBo gt_2 = iCbs.next();
					gt_2.setRole("GT_2");
					pt.getListCB().add(gt_2);
				}
				
				System.out.println("--------------------------");
				System.out.println(pt);
				for (CanBo cb : pt.getListCB()) {
					System.out.println(cb);
				}
				System.out.println("--------------------------");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
