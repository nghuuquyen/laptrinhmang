package models;

import java.util.ArrayList;
import java.util.List;

public class PhongThi {
	String maPhongThi;
	List<CanBo> listCB;

	public PhongThi() {
		listCB = new ArrayList<CanBo>();
	}

	public String getMaPhongThi() {
		return maPhongThi;
	}

	public void setMaPhongThi(String maPhongThi) {
		this.maPhongThi = maPhongThi;
	}

	public List<CanBo> getListCB() {
		return listCB;
	}

	public void setListCB(List<CanBo> listCB) {
		this.listCB = listCB;
	}

	@Override
	public String toString() {
		return "[" + this.maPhongThi + "]";
	}
}
