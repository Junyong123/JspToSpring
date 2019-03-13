package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements IBoardService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private IBoardDao boardDao;
	
	public BoardServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		
		boardDao = new BoardDaoImpl();
	}
	
	/**
	 * Method : getAllBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	@Override
	public List<BoardVO> getAllBoard() {
		sqlSession = sqlSessionFactory.openSession();
		List<BoardVO> boardList = boardDao.getAllBoard(sqlSession);
		sqlSession.close();
		
		return boardList;
	}
	
	/**
	 * Method : selectBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 게시판 조회
	 */
	@Override
	public BoardVO selectBoard(Integer board_num) {
		sqlSession = sqlSessionFactory.openSession();
		BoardVO boardVO = boardDao.selectBoard(sqlSession, board_num);
		sqlSession.close();
		
		return boardVO;
	}

	/**
	 * Method : insertBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	@Override
	public int insertBoard(BoardVO boardVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = boardDao.insertBoard(sqlSession, boardVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : updateBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param boardVO
	 * @return
	 * Method 설명 : 게시판 수정
	 */
	@Override
	public int updateBoard(BoardVO boardVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = boardDao.updateBoard(sqlSession, boardVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : deleteBoard
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 게시판 삭제
	 */
	@Override
	public int deleteBoard(Integer board_num) {
		sqlSession = sqlSessionFactory.openSession();
		int result = boardDao.deleteBoard(sqlSession, board_num);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
}