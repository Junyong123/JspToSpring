package kr.or.ddit.posting.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.posting.model.PostingVO;
import kr.or.ddit.util.model.PageVO;

import org.junit.Before;
import org.junit.Test;

public class PostingServiceImplTest {
	private IPostingService postingService;
	
	@Before
	public void setup(){
		postingService = new PostingServiceImpl();
	}
	
	@Test
	public void testGetAllPosting() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingService.getAllPosting(board_num);
		
		/***Then***/
		assertEquals(1, postingList.size());
	}
	
	@Test
	public void testSelectHierar() {
		/***Given***/
		Integer board_num = 1;
		
		/***When***/
		List<PostingVO> postingList = postingService.selectHierar(board_num);
		
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
		int result = postingService.insertPosting(postingVO);
		
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
		int result = postingService.updatePosting(postingVO);
		
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
		Map<String, Object> resultMap = postingService.selectPostingPaging(map, board_num);
		List<PostingVO> postingList = (List<PostingVO>) resultMap.get("postingList");
		int postingCnt = (int) resultMap.get("postingCnt");
		
		/***Then***/
		assertEquals(10, postingList.size());
		assertEquals(10, postingCnt);
	}
	
	@Test
	public void testUpdateLevel(){
		/***Given***/
		Integer board_num = 1;
		List<PostingVO> postingList = postingService.selectHierar(board_num);
		
		/***When***/
		int result = 0;
		for(PostingVO postingVO : postingList){
			String posting_level = postingVO.getPosting_level();
			
			postingVO.setPosting_level(posting_level);
			
			result += postingService.updateLevel(postingVO);
		}
		
		/***Then***/
		assertEquals(11, result);
	}
	
	@Test
	public void testSelectPosting(){
		/***Given***/
		Integer posting_num = 2;
		
		/***When***/
		PostingVO postingVO = postingService.selectPosting(posting_num);
			
		/***Then***/
		assertNotNull(postingVO);
	}

}