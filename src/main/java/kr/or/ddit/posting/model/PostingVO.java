package kr.or.ddit.posting.model;

import java.util.Date;

public class PostingVO {
	private Integer posting_num;
	private Integer board_num;
	private String posting_title;
	private String posting_content;
	private String posting_userid;
	private Date posting_date;
	private String delete_exist;
	private Integer parentposting_num;
	private String posting_level;
	
	public PostingVO() {
		
	}
	
	public PostingVO(Integer board_num, String posting_title,
			String posting_content, String posting_userid,
			Integer parentposting_num) {
		this.board_num = board_num;
		this.posting_title = posting_title;
		this.posting_content = posting_content;
		this.posting_userid = posting_userid;
		this.parentposting_num = parentposting_num;
	}
	
	public Integer getPosting_num() {
		return posting_num;
	}
	public void setPosting_num(Integer posting_num) {
		this.posting_num = posting_num;
	}
	public Integer getBoard_num() {
		return board_num;
	}
	public void setBoard_num(Integer board_num) {
		this.board_num = board_num;
	}
	public String getPosting_title() {
		return posting_title;
	}
	public void setPosting_title(String posting_title) {
		this.posting_title = posting_title;
	}
	public String getPosting_content() {
		return posting_content;
	}
	public void setPosting_content(String posting_content) {
		this.posting_content = posting_content;
	}
	public String getPosting_userid() {
		return posting_userid;
	}
	public void setPosting_userid(String posting_userid) {
		this.posting_userid = posting_userid;
	}
	public Date getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}
	public String getDelete_exist() {
		return delete_exist;
	}
	public void setDelete_exist(String delete_exist) {
		this.delete_exist = delete_exist;
	}
	public Integer getParentposting_num() {
		return parentposting_num;
	}
	public void setParentposting_num(Integer parentposting_num) {
		this.parentposting_num = parentposting_num;
	}
	public String getPosting_level() {
		return posting_level;
	}
	public void setPosting_level(String posting_level) {
		this.posting_level = posting_level;
	}
	
	@Override
	public String toString() {
		return "PostingVO [posting_num=" + posting_num + ", board_num="
				+ board_num + ", posting_title=" + posting_title
				+ ", posting_content=" + posting_content + ", posting_userid="
				+ posting_userid + ", posting_date=" + posting_date
				+ ", delete_exist=" + delete_exist + ", parentposting_num="
				+ parentposting_num + ", posting_level=" + posting_level + "]";
	}
	
}