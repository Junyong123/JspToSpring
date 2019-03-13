package kr.or.ddit.reply.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class ReplyServiceImplTest {
	private IReplyService replyService;
	
	@Before
	public void setup(){
		replyService = new ReplyServiceImpl();
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
		int result = replyService.insertReply(replyVO);

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
		int result = replyService.updateReply(replyVO);

		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testSelectReply(){
		/***Given***/
		Integer reply_num = 1;
		
		/***When***/
		ReplyVO replyVO = replyService.selectReply(reply_num);

		/***Then***/
		assertNotNull(replyVO);
	}
	
	@Test
	public void testGetAllReply(){
		/***Given***/
		Integer posting_num = 36;
		
		/***When***/
		List<ReplyVO> replyList = replyService.getAllReply(posting_num);

		/***Then***/
		assertEquals(2, replyList.size());
	}

}