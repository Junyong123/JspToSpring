package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.user.model.UserVO;

import org.junit.Before;
import org.junit.Test;

public class UserServiceImplTest {
	private IUserService userService;
	
	@Before
	public void setup(){
		userService = new UserServiceImpl();
	}

	@Test
	public void testSelectUser() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = userService.selectUser(userId);

		/***Then***/
		assertEquals(userId, userVO.getUserId());
	}

}