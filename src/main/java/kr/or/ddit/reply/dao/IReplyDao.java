package kr.or.ddit.reply.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyDao {
	/**
	 * Method : insertReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	int insertReply(SqlSession sqlSession, ReplyVO replyVO);
	
	/**
	 * Method : updateReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	int updateReply(SqlSession sqlSession, ReplyVO replyVO);
	
	/**
	 * Method : selectReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param reply_num
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	ReplyVO selectReply(SqlSession sqlSession, Integer reply_num);
	
	/**
	 * Method : getAllReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 댓글 전체 조회
	 */
	List<ReplyVO> getAllReply(SqlSession sqlSession, Integer posting_num);
}