package kr.or.ddit.board.model;

public class BoardVO {
	private Integer board_num;
	private String board_name;
	private String use_exist;
	
	public Integer getBoard_num() {
		return board_num;
	}
	public void setBoard_num(Integer board_num) {
		this.board_num = board_num;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getUse_exist() {
		return use_exist;
	}
	public void setUse_exist(String use_exist) {
		this.use_exist = use_exist;
	}

	@Override
	public String toString() {
		return "BoardVO [board_num=" + board_num + ", board_name=" + board_name
				+ ", use_exist=" + use_exist + "]";
	}
}