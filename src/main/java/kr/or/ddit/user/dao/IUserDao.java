package kr.or.ddit.user.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVO;

public interface IUserDao {
	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	UserVO selectUser(SqlSession sqlSession, String userId);
}