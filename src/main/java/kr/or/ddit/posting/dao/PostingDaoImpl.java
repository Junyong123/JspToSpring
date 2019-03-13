package kr.or.ddit.posting.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.posting.model.PostingVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("postingDao")
public class PostingDaoImpl implements IPostingDao{
	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	@Override
	public List<PostingVO> getAllPosting(SqlSession sqlSession, Integer board_num) {
		List<PostingVO> postingList = sqlSession.selectList("posting.getAllPosting", board_num);
		return postingList;
	}

	/**
	 * Method : selectHierar
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 계층형으로 조회
	 */
	@Override
	public List<PostingVO> selectHierar(SqlSession sqlSession, Integer board_num) {
		List<PostingVO> postingList = sqlSession.selectList("posting.selectHierar", board_num);
		return postingList;
	}

	/**
	 * Method : insertPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	@Override
	public int insertPosting(SqlSession sqlSession, PostingVO postingVO) {
		int result = sqlSession.insert("posting.insertPosting", postingVO);
		return result;
	}

	/**
	 * Method : updatePosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	@Override
	public int updatePosting(SqlSession sqlSession, PostingVO postingVO) {
		int result = sqlSession.update("posting.updatePosting", postingVO);
		return result;
	}
	
	/**
	 * Method : selectPostingPaging
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param map
	 * @return
	 * Method 설명 : 게시글 계층형으로 페이징 조회
	 */
	@Override
	public List<PostingVO> selectPostingPaging(SqlSession sqlSession,
			Map<String, Object> map) {
		List<PostingVO> postingList = sqlSession.selectList("posting.selectPostingPaging", map);
		System.out.println(postingList.size());
		return postingList;
	}

	/**
	 * Method : getPostingCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 수 조회
	 */
	@Override
	public int getPostingCnt(SqlSession sqlSession, Integer board_num) {
		int result = sqlSession.selectOne("posting.getPostingCnt", board_num);
		return result;
	}

	/**
	 * Method : updateLevel
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 level 업데이트
	 */
	@Override
	public int updateLevel(SqlSession sqlSession, PostingVO postingVO) {
		int result = sqlSession.update("posting.updateLevel", postingVO);
		return result;
	}

	/**
	 * Method : selectPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 조회
	 */
	@Override
	public PostingVO selectPosting(SqlSession sqlSession, Integer posting_num) {
		PostingVO postingVO = sqlSession.selectOne("posting.selectPosting", posting_num);
		return postingVO;
	}
}