package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoImplTest {
	private SqlSession sqlSession;
	private IBoardDao boardDao;
	
	private Logger logger = LoggerFactory.getLogger(BoardDaoImplTest.class);
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
		boardDao = new BoardDaoImpl();
	}
	
	@Test
	public void testGetAllBoard() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList =  boardDao.getAllBoard(sqlSession);
		for(BoardVO boardVO : boardList){
			logger.debug("boardVO : {}", boardVO);
		}
		
		/***Then***/
		
	}
	
	@Test
	public void testSelectBoard(){
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		BoardVO boardVO = boardDao.selectBoard(sqlSession, 1);

		/***Then***/
		assertEquals(board_num, boardVO.getBoard_num());
	}
	
	@Test
	public void testInsertBoard(){
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_name("자유게시판2");
		boardVO.setUse_exist("Y");
		
		/***When***/
		int result = boardDao.insertBoard(sqlSession, boardVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateBoard(){
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_num(1);
		boardVO.setBoard_name("자유게시판2");
		boardVO.setUse_exist("Y");
		
		/***When***/
		int result = boardDao.updateBoard(sqlSession, boardVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
}