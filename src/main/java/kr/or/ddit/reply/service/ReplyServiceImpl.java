package kr.or.ddit.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.dao.ReplyDaoImpl;
import kr.or.ddit.reply.model.ReplyVO;

@Service("replyService")
public class ReplyServiceImpl implements IReplyService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private IReplyDao replyDao;
	
	public ReplyServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		replyDao = new ReplyDaoImpl();
	}

	/**
	 * Method : insertReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 등록
	 */
	@Override
	public int insertReply(ReplyVO replyVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = replyDao.insertReply(sqlSession, replyVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : updateReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param replyVO
	 * @return
	 * Method 설명 : 댓글 수정
	 */
	@Override
	public int updateReply(ReplyVO replyVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = replyDao.updateReply(sqlSession, replyVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : selectReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param reply_num
	 * @return
	 * Method 설명 : 댓글 조회
	 */
	@Override
	public ReplyVO selectReply(Integer reply_num) {
		sqlSession = sqlSessionFactory.openSession();
		ReplyVO replyVO = replyDao.selectReply(sqlSession, reply_num);
		sqlSession.close();
		
		return replyVO;
	}

	/**
	 * Method : getAllReply
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 댓글 전체 조회
	 */
	@Override
	public List<ReplyVO> getAllReply(Integer posting_num) {
		sqlSession = sqlSessionFactory.openSession();
		List<ReplyVO> replyList = replyDao.getAllReply(sqlSession, posting_num);
		sqlSession.close();
		
		return replyList;
	}

}