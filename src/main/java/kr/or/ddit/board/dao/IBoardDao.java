package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {
	/**
	 * Method : getAllBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	List<BoardVO> getAllBoard(SqlSession sqlSession);
	
	/**
	 * Method : selectBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 게시판 조회
	 */
	BoardVO selectBoard(SqlSession sqlSession, Integer board_num);
	
	/**
	 * Method : insertBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	int insertBoard(SqlSession sqlSession, BoardVO boardVO);
	
	/**
	 * Method : updateBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	int updateBoard(SqlSession sqlSession, BoardVO boardVO);
	
	/**
	 * Method : deleteBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 게시판 삭제
	 */
	int deleteBoard(SqlSession sqlSession, Integer board_num);
}