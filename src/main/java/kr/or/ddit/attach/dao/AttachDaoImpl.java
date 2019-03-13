package kr.or.ddit.attach.dao;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("attachDao")
public class AttachDaoImpl implements IAttachDao{
	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAttach(SqlSession sqlSession, AttachVO attachVO) {
		int result = sqlSession.insert("attach.insertAttach", attachVO);
		return result;
	}

	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	@Override
	public int updateAttach(SqlSession sqlSession, AttachVO attachVO) {
		int result = sqlSession.update("attach.updateAttach", attachVO);
		return result;
	}

	/**
	 * Method : selectAttachList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 조회
	 */
	@Override
	public List<AttachVO> selectAttachList(SqlSession sqlSession, Integer posting_num) {
		List<AttachVO> attachList = sqlSession.selectList("attach.selectAttachList", posting_num);
		return attachList;
	}

	/**
	 * Method : selectAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attach_num
	 * @return
	 * Method 설명 : 첨부파일 조회
	 */
	@Override
	public AttachVO selectAttach(SqlSession sqlSession, Integer attach_num) {
		AttachVO attachVO = sqlSession.selectOne("attach.selectAttach", attach_num);
		return attachVO;
	}

	/**
	 * Method : deleteAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 삭제
	 */
	@Override
	public int deleteAttach(SqlSession sqlSession, Integer posting_num) {
		int result = sqlSession.delete("attach.deleteAttach", posting_num);
		return result;
	}

}