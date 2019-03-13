package kr.or.ddit.attach.model;

public class AttachVO {
	private Integer attach_num;
	private String filename;
	private String realfilename;
	private Integer posting_num;
	
	public Integer getAttach_num() {
		return attach_num;
	}
	public void setAttach_num(Integer attach_num) {
		this.attach_num = attach_num;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getRealfilename() {
		return realfilename;
	}
	public void setRealfilename(String realfilename) {
		this.realfilename = realfilename;
	}
	public Integer getPosting_num() {
		return posting_num;
	}
	public void setPosting_num(Integer posting_num) {
		this.posting_num = posting_num;
	}
	
	@Override
	public String toString() {
		return "AttachVO [attach_num=" + attach_num + ", filename=" + filename
				+ ", realfilename=" + realfilename + ", posting_num="
				+ posting_num + "]";
	}
}