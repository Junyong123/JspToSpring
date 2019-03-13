package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("boardDao")
public class BoardDaoImpl implements IBoardDao{

	/**
	 * Method : getAllBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	@Override
	public List<BoardVO> getAllBoard(SqlSession sqlSession) {
		List<BoardVO> boardList = sqlSession.selectList("board.getAllBoard");
		return boardList;
	}
	
	/**
	 * Method : selectBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 게시판 조회
	 */
	@Override
	public BoardVO selectBoard(SqlSession sqlSession, Integer board_num) {
		BoardVO boardVO = sqlSession.selectOne("board.selectBoard", board_num);		
		return boardVO;
	}

	/**
	 * Method : insertBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	@Override
	public int insertBoard(SqlSession sqlSession, BoardVO boardVO) {
		int result = sqlSession.insert("board.insertBoard", boardVO);
		return result;
	}

	/**
	 * Method : updateBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	@Override
	public int updateBoard(SqlSession sqlSession, BoardVO boardVO) {
		int result = sqlSession.update("board.updateBoard", boardVO);
		return result;
	}

	/**
	 * Method : deleteBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 게시판 삭제
	 */
	@Override
	public int deleteBoard(SqlSession sqlSession, Integer board_num) {
		int result = sqlSession.delete("board.deleteBoard", board_num);
		return result;
	}
}