package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.service.IUserService;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceImplTest {
	private IBoardService boardService;
	
	private Logger logger = LoggerFactory.getLogger(BoardServiceImplTest.class);
	
	@Before
	public void setup(){
		boardService = new BoardServiceImpl();
	}
	
	
	@Test
	public void testGetAllBoard() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = boardService.getAllBoard();
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
		BoardVO boardVO = boardService.selectBoard(1);

		/***Then***/
		assertEquals(board_num, boardVO.getBoard_num());
	}
	
	@Test
	public void testInsertBoard(){
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_name("오늘의 게시판");
		boardVO.setUse_exist("Y");
		
		/***When***/
		int result = boardService.insertBoard(boardVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateBoard(){
		/***Given***/
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_num(1);
		boardVO.setBoard_name("자유게시판");
		boardVO.setUse_exist("N");
		
		/***When***/
		int result = boardService.updateBoard(boardVO);
		
		/***Then***/
		assertEquals(1, result);
	}

}
