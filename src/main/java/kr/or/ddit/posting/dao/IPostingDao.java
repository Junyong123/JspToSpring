package kr.or.ddit.posting.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.posting.model.PostingVO;

public interface IPostingDao {
	/**
	 * Method : getAllPosting
	 * 작성자 : lee
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	List<PostingVO> getAllPosting(SqlSession sqlSession, Integer board_num);
	
	/**
	 * Method : selectHierar
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 계층형으로 조회
	 */
	List<PostingVO> selectHierar(SqlSession sqlSession, Integer board_num);
	
	/**
	 * Method : insertPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 등록
	 */
	int insertPosting(SqlSession sqlSession, PostingVO postingVO);
	
	/**
	 * Method : updatePosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 수정
	 */
	int updatePosting(SqlSession sqlSession, PostingVO postingVO);
	
	/**
	 * Method : selectPostingPaging
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param map
	 * @return
	 * Method 설명 : 게시글 계층형으로 페이징 조회
	 */
	List<PostingVO> selectPostingPaging(SqlSession sqlSession, Map<String, Object> map);
	
	/**
	 * Method : getPostingCnt
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param board_num
	 * @return
	 * Method 설명 : 해당 게시판 전체 게시글 수 조회
	 */
	int getPostingCnt(SqlSession sqlSession, Integer board_num);
	
	/**
	 * Method : updateLevel
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param postingVO
	 * @return
	 * Method 설명 : 게시글 level 업데이트
	 */
	int updateLevel(SqlSession sqlSession, PostingVO postingVO);
	
	/**
	 * Method : selectPosting
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param posting_num
	 * @return
	 * Method 설명 : 해당 게시글 조회
	 */
	PostingVO selectPosting(SqlSession sqlSession, Integer posting_num);
}