package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {
	/**
	 * Method : getAllBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	List<BoardVO> getAllBoard();
	
	/**
	 * Method : selectBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 :	게시판 조회
	 */
	BoardVO selectBoard(Integer board_num);
	
	/**
	 * Method : insertBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	int insertBoard(BoardVO boardVO);
	
	/**
	 * Method : updateBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	int updateBoard(BoardVO boardVO);
	
	/**
	 * Method : deleteBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 게시판 삭제
	 */
	int deleteBoard(Integer board_num);
}
