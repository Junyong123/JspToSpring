package kr.or.ddit.reply.dao;

import static org.junit.Assert.*;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class ReplyDaoImplTest {
	
	private SqlSession sqlSession;
	private IReplyDao replyDao;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		replyDao = new ReplyDaoImpl();
	}

	@Test
	public void testInsertReply() {
		/***Given***/
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReply_content("오늘 끝내야합니다.");
		replyVO.setPosting_num(36);
		replyVO.setReply_userid("brown");
		replyVO.setReply_admin("brown");
		
		/***When***/
		int result = replyDao.insertReply(sqlSession, replyVO);

		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateReply() {
		/***Given***/
		ReplyVO replyVO = new ReplyVO();
		replyVO.setReply_content("오늘 끝내야합니다.2");
		replyVO.setDelete_exist("N");
		replyVO.setReply_num(1);
		
		/***When***/
		int result = replyDao.updateReply(sqlSession, replyVO);

		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testSelectReply(){
		/***Given***/
		Integer reply_num = 1;
		
		/***When***/
		ReplyVO replyVO = replyDao.selectReply(sqlSession, reply_num);

		/***Then***/
		assertNotNull(replyVO);
	}
	
	@Test
	public void testGetAllReply(){
		/***Given***/
		Integer posting_num = 36;
		
		/***When***/
		List<ReplyVO> replyList = replyDao.getAllReply(sqlSession, posting_num);

		/***Then***/
		assertEquals(1, replyList.size());
	}

}