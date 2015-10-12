package com.common.template.entity;

public class PmsOption {

	public String pmsCode;
	public String parentCode;
	public String pmsName;
	public String selected = "";
	public String checked = "false";

	public PmsOption() {
	}

	public String getPmsCode() {
		return pmsCode;
	}

	public void setPmsCode(String pmsCode) {
		this.pmsCode = pmsCode;
	}

	public String getPmsName() {
		return pmsName;
	}

	public void setPmsName(String pmsName) {
		this.pmsName = pmsName;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
