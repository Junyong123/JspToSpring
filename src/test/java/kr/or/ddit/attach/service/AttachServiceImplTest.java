package kr.or.ddit.attach.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.attach.model.AttachVO;

import org.junit.Before;
import org.junit.Test;

public class AttachServiceImplTest {
	private IAttachService attachService;
	
	@Before
	public void setup(){
		attachService = new AttachServiceImpl();
	}
	
	@Test
	public void testInsertAttach() {
		/***Given***/
		AttachVO attachVO = new AttachVO();
		attachVO.setFilename("brown.png");
		attachVO.setRealfilename("D:\\attach\\brown.png");
		attachVO.setPosting_num(3);
		
		/***When***/
		int result = attachService.insertAttach(attachVO);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateAttach() {
		/***Given***/
		AttachVO attachVO = new AttachVO();
		attachVO.setFilename("cony.png");
		attachVO.setRealfilename("D:\\attach\\brown.png");
		attachVO.setAttach_num(1);
		
		/***When***/
		int result = attachService.updateAttach(attachVO);
		
		/***Then***/
		assertEquals(1, result);
	}

	@Test
	public void testSelectAttachList(){
		/***Given***/
		Integer posting_num = 1;
		
		/***When***/
		List<AttachVO> attachList = attachService.selectAttachList(posting_num);
		
		/***Then***/
		assertNotNull(attachList);
	}
	
	@Test
	public void testSelectAttach(){
		/***Given***/
		Integer attach_num = 1;
		
		/***When***/
		AttachVO attachVO = attachService.selectAttach(attach_num);
		
		/***Then***/
		assertNotNull(attachVO);
	}
	
	@Test
	public void testDeleteAttach(){
		/***Given***/
		Integer posting_num = 36;
		
		/***When***/
		int result = attachService.deleteAttach(posting_num);
		
		/***Then***/
		assertEquals(8, result);
	}
}
