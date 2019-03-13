package kr.or.ddit.attach.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.attach.dao.AttachDaoImpl;
import kr.or.ddit.attach.dao.IAttachDao;
import kr.or.ddit.attach.model.AttachVO;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;

@Service("attachService")
public class AttachServiceImpl implements IAttachService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private IAttachDao attachDao;
	
	public AttachServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		attachDao = new AttachDaoImpl();
	}

	/**
	 * Method : insertAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 등록
	 */
	@Override
	public int insertAttach(AttachVO attachVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = attachDao.insertAttach(sqlSession, attachVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : updateAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attachVO
	 * @return
	 * Method 설명 : 첨부파일 수정
	 */
	@Override
	public int updateAttach(AttachVO attachVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = attachDao.updateAttach(sqlSession, attachVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : selectAttachList
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 조회
	 */
	@Override
	public List<AttachVO> selectAttachList(Integer posting_num) {
		sqlSession = sqlSessionFactory.openSession();
		List<AttachVO> attachList = attachDao.selectAttachList(sqlSession, posting_num);
		sqlSession.close();
		
		return attachList;
	}

	/**
	 * Method : selectAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param attach_num
	 * @return
	 * Method 설명 : 첨부파일 조회
	 */
	@Override
	public AttachVO selectAttach(Integer attach_num) {
		sqlSession = sqlSessionFactory.openSession();
		AttachVO attachVO = attachDao.selectAttach(sqlSession, attach_num);
		sqlSession.close();
		
		return attachVO;
	}

	/**
	 * Method : deleteAttach
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 첨부파일 전체 삭제
	 */
	@Override
	public int deleteAttach(Integer posting_num) {
		sqlSession = sqlSessionFactory.openSession();
		int result = attachDao.deleteAttach(sqlSession, posting_num);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}
	
}