package kr.or.ddit.reply.service;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyService {
	/**
	 * Method : insertReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	int insertReply(ReplyVO replyVO);
	
	/**
	 * Method : updateReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	int updateReply(ReplyVO replyVO);
	
	/**
	 * Method : selectReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param reply_num
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	ReplyVO selectReply(Integer reply_num);
	
	/**
	 * Method : getAllReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 댓글 전체 조회
	 */
	List<ReplyVO> getAllReply(Integer posting_num);
}