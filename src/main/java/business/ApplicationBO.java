package business;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import models.CanBo;
import models.PhongThi;
import utils.WriteExcelFile;

public class ApplicationBO {
	public List<PhongThi> chiaPhongThi(List<CanBo> cbs, List<PhongThi> pts) throws Exception {
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
		}
		
		if(iCbs.hasNext()) Collections.shuffle(pts);
		
		while(iCbs.hasNext()) {
			for (PhongThi pt : pts) {
				if(iCbs.hasNext()) pt.getListCB().add(iCbs.next());
			}
		}
		
		return pts;
	}

	public void writeDataToFile(List<PhongThi> pts, DataOutputStream dos) throws IOException {
		WriteExcelFile writer = new WriteExcelFile();
		writer.writeExcel(pts, dos);
	}
}
