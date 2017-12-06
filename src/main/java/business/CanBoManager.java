package business;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import models.CanBo;
import utils.ReadExcelFile;

public class CanBoManager {
	List<CanBo> listCB;
	
	public CanBoManager(InputStream inputStream) {
		ReadExcelFile reader = new ReadExcelFile();
		listCB = reader.getListCB(inputStream);
	}
	
	public List<CanBo> getRandomListCB() {
		Collections.shuffle(listCB);
		
		return listCB;
	}
}
