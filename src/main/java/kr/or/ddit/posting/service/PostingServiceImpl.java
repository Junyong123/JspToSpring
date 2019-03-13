package kr.or.ddit.posting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.posting.dao.IPostingDao;
import kr.or.ddit.posting.dao.PostingDaoImpl;
import kr.or.ddit.posting.model.PostingVO;

@Service("postingService")
public class PostingServiceImpl implements IPostingService{
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	private IPostingDao postingDao;
	
	public PostingServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		postingDao = new PostingDaoImpl();
	}
	
	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 조회
	 */
	@Override
	public List<PostingVO> getAllPosting(Integer board_num) {
		sqlSession = sqlSessionFactory.openSession();
		List<PostingVO> postingList = postingDao.getAllPosting(sqlSession, board_num);
		sqlSession.close();
		
		return postingList;
	}


	/**
	 * Method : selectHierar
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 계층형으로 조회
	 */
	@Override
	public List<PostingVO> selectHierar(Integer board_num) {
		sqlSession = sqlSessionFactory.openSession();
		List<PostingVO> postingList = postingDao.selectHierar(sqlSession, board_num);
		sqlSession.close();
		
		return postingList;
	}

	/**
	 * Method : insertPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	@Override
	public int insertPosting(PostingVO postingVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = postingDao.insertPosting(sqlSession, postingVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : updatePosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePosting(PostingVO postingVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = postingDao.updatePosting(sqlSession, postingVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}


	/**
	 * Method : selectPostingPaging
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param map
	 * @param board_num
	 * @return
	 * Method 설명 : 게시글 계층형으로 페이징 조회(해당 게시판 전체 게시글 수 또한 여기서 조회)
	 */
	@Override
	public Map<String, Object> selectPostingPaging(Map<String, Object> map, Integer board_num) {
		sqlSession = sqlSessionFactory.openSession();
		
		//결과값을 두개 담아서 return 하기위해 Map 객체 사용
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("postingList", postingDao.selectPostingPaging(sqlSession, map));
		resultMap.put("postingCnt", postingDao.getPostingCnt(sqlSession, board_num));
		
		sqlSession.close();
		
		return resultMap;
	}

	/**
	 * Method : updateLevel
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 level 업데이트
	 */
	@Override
	public int updateLevel(PostingVO postingVO) {
		sqlSession = sqlSessionFactory.openSession();
		int result = postingDao.updateLevel(sqlSession, postingVO);
		sqlSession.commit();
		sqlSession.close();
		
		return result;
	}

	/**
	 * Method : selectPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 조회
	 */
	@Override
	public PostingVO selectPosting(Integer posting_num) {
		sqlSession = sqlSessionFactory.openSession();
		PostingVO postingVO = postingDao.selectPosting(sqlSession, posting_num);
		sqlSession.close();
		
		return postingVO;
	}

}