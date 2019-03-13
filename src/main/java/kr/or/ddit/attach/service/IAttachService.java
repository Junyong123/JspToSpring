package kr.or.ddit.attach.service;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

public interface IAttachService {
	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	int insertAttach(AttachVO attachVO);
	
	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	int updateAttach(AttachVO attachVO);
	
	/**
	 * Method : selectAttachList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 조회
	 */
	List<AttachVO> selectAttachList(Integer posting_num);
	
	
	/**
	 * Method : selectAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attach_num
	 * @return
	 * Method 설명 : 첨부파일 조회
	 */
	AttachVO selectAttach(Integer attach_num);
	
	/**
	 * Method : deleteAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 삭제
	 */
	int deleteAttach(Integer posting_num);
}