package kr.or.ddit.reply.model;

import java.util.Date;

public class ReplyVO {
	private Integer reply_num;
	private String reply_content;
	private Date reply_date;
	private String delete_exist;
	private Integer posting_num;
	private String reply_userid;
	private String reply_admin;
	
	public Integer getReply_num() {
		return reply_num;
	}
	public void setReply_num(Integer reply_num) {
		this.reply_num = reply_num;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReply_date() {
		return reply_date;
	}
	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	public String getDelete_exist() {
		return delete_exist;
	}
	public void setDelete_exist(String delete_exist) {
		this.delete_exist = delete_exist;
	}
	public Integer getPosting_num() {
		return posting_num;
	}
	public void setPosting_num(Integer posting_num) {
		this.posting_num = posting_num;
	}
	public String getReply_userid() {
		return reply_userid;
	}
	public void setReply_userid(String reply_userid) {
		this.reply_userid = reply_userid;
	}
	public String getReply_admin() {
		return reply_admin;
	}
	public void setReply_admin(String reply_admin) {
		this.reply_admin = reply_admin;
	}
	
	@Override
	public String toString() {
		return "ReplyVO [reply_num=" + reply_num + ", reply_content="
				+ reply_content + ", reply_date=" + reply_date
				+ ", delete_exist=" + delete_exist + ", posting_num="
				+ posting_num + ", reply_userid=" + reply_userid
				+ ", reply_admin=" + reply_admin + "]";
	}
}