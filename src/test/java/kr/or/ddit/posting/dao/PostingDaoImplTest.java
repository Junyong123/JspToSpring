package kr.or.ddit.posting.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.util.model.PageVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class PostingDaoImplTest {
	private SqlSession sqlSession;
	private IPostingDao postingDao;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		postingDao = new PostingDaoImpl();
	}
	
	@Test
	public void testGetAllPosting() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingDao.getAllPosting(sqlSession, board_num);
		
		/***Then***/
		assertEquals(1, postingList.size());
	}
	
	@Test
	public void testSelectHierar() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingDao.selectHierar(sqlSession, board_num);
		
		/***Then***/
		assertEquals(2, postingList.size());
	}
	
	@Test
	public void testInsertPosting(){
		/***Given***/
		PostingVO postingVO = new PostingVO();
		postingVO.setBoard_num(1);
		postingVO.setPosting_title("테스트1");
		postingVO.setPosting_content("테스트1");
		postingVO.setPosting_userid("brown");
		
		/***When***/
		int result = postingDao.insertPosting(sqlSession, postingVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdatePosting(){
		/***Given***/
		PostingVO postingVO = new PostingVO();
		postingVO.setPosting_num(3);
		postingVO.setPosting_title("테스트2");
		postingVO.setPosting_content("테스트2");
		postingVO.setDelete_exist("N");
		
		/***When***/
		int result = postingDao.updatePosting(sqlSession, postingVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testSelectPostingPaging(){
		/***Given***/
		Integer board_num = 1;
		PageVO pageVO = new PageVO(1, 10);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_num", board_num);
		map.put("page", pageVO.getPage());
		map.put("pageSize", pageVO.getPageSize());
		
		/***When***/
		List<PostingVO> postingList = postingDao.selectPostingPaging(sqlSession, map);
		
		/***Then***/
		assertEquals(10, postingList.size());
	}
	
	@Test
	public void testGetPostingCnt(){
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		int result = postingDao.getPostingCnt(sqlSession, board_num);
		
		/***Then***/
		assertEquals(10, result);
	}
	
	@Test
	public void testUpdateLevel(){
		/***Given***/
		Integer board_num = 1;
		List<PostingVO> postingList = postingDao.selectHierar(sqlSession, board_num);
		
		/***When***/
		int result = 0;
		for(PostingVO postingVO : postingList){
			String posting_level = postingVO.getPosting_level();
			
			postingVO.setPosting_level(posting_level);
			
			result += postingDao.updateLevel(sqlSession, postingVO);
		}
		
		/***Then***/
		assertEquals(11, result);
	}
	
	@Test
	public void testSelectPosting(){
		/***Given***/
		Integer posting_num = 2;
		
		/***When***/
		PostingVO postingVO = postingDao.selectPosting(sqlSession, posting_num);
			
		/***Then***/
		assertNotNull(postingVO);
	}
	
}