package business;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;

import models.CanBo;
import utils.ReadExcelFile;

public class CanBoManager {
	List<CanBo> listCB;
	
	public CanBoManager(FileInputStream inputStream) {
		ReadExcelFile reader = new ReadExcelFile();
		listCB = reader.getListCB(inputStream);
	}
	
	public List<CanBo> getRandomListCB() {
		Collections.shuffle(listCB);
		
		return listCB;
	}
}
