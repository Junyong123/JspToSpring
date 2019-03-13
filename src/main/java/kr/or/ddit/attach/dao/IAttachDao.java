package kr.or.ddit.attach.dao;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

import org.apache.ibatis.session.SqlSession;

public interface IAttachDao {
	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	int insertAttach(SqlSession sqlSession, AttachVO attachVO);
	
	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	int updateAttach(SqlSession sqlSession, AttachVO attachVO);
	
	/**
	 * Method : selectAttachList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 조회
	 */
	List<AttachVO> selectAttachList(SqlSession sqlSession, Integer posting_num);
	
	/**
	 * Method : selectAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param attach_num
	 * @return
	 * Method 설명 : 첨부파일 조회
	 */
	AttachVO selectAttach(SqlSession sqlSession, Integer attach_num);
	
	/**
	 * Method : deleteAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 삭제
	 */
	int deleteAttach(SqlSession sqlSession, Integer posting_num);
}