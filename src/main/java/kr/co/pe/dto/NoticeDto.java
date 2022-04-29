package kr.co.pe.dto;

public class NoticeDto {
	
	private int notice_idx;
	private String notice_title;
	private String notice_contents;
	private String reg_dt;
	private String mod_dt;
	
	
	public int getNotice_idx() {
		return notice_idx;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public String getNotice_contents() {
		return notice_contents;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public String getMod_dt() {
		return mod_dt;
	}
	public void setNotice_idx(int notice_idx) {
		this.notice_idx = notice_idx;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public void setNotice_contents(String notice_contents) {
		this.notice_contents = notice_contents;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public void setMod_dt(String mod_dt) {
		this.mod_dt = mod_dt;
	}
	


}
