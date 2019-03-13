package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVO;

public interface IUserService {
	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	UserVO selectUser(String userId);
}