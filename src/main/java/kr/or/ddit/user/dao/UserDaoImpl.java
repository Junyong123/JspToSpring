package kr.or.ddit.user.dao;

import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{

	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param sqlSession
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	@Override
	public UserVO selectUser(SqlSession sqlSession, String userId) {
		UserVO userVO = sqlSession.selectOne("user.selectUser", userId);
		
		return userVO;
	}

}
