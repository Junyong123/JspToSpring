package kr.or.ddit.reply.dao;

import java.util.List;

import kr.or.ddit.reply.model.ReplyVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("replyDao")
public class ReplyDaoImpl implements IReplyDao{

	/**
	 * Method : insertReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@Override
	public int insertReply(SqlSession sqlSession, ReplyVO replyVO) {
		int result = sqlSession.insert("reply.insertReply", replyVO);
		return result;
	}

	/**
	 * Method : updateReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	@Override
	public int updateReply(SqlSession sqlSession, ReplyVO replyVO) {
		int result = sqlSession.update("reply.updateReply", replyVO);
		return result;
	}

	/**
	 * Method : selectReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param reply_num
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	@Override
	public ReplyVO selectReply(SqlSession sqlSession, Integer reply_num) {
		ReplyVO replyVO = sqlSession.selectOne("reply.selectReply", reply_num);
		return replyVO;
	}

	/**
	 * Method : getAllReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 댓글 전체 조회
	 */
	@Override
	public List<ReplyVO> getAllReply(SqlSession sqlSession, Integer posting_num) {
		List<ReplyVO> replyList = sqlSession.selectList("reply.getAllReply", posting_num);
		return replyList;
	}

}