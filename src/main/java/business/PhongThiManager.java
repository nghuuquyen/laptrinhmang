package business;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;

import models.PhongThi;
import utils.ReadExcelFile;

public class PhongThiManager {
	List<PhongThi> listPT;

	public PhongThiManager(FileInputStream inputStream) {
		ReadExcelFile reader = new ReadExcelFile();
		listPT = reader.getListPT(inputStream);
	}

	public List<PhongThi> getRandomListPT() {
		Collections.shuffle(listPT);
		return listPT;
	}
}
