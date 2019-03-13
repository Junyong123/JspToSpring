package kr.or.ddit.user.service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService{
	
	private SqlSessionFactory sqlSessionFactory;
	private SqlSession sqlSession;
	
	private IUserDao userDao;
	
	public UserServiceImpl() {
		sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		
		userDao = new UserDaoImpl();
	}
	
	/**
	 * Method : selectUser
	 * 작성자 : pc11
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 조회
	 */
	@Override
	public UserVO selectUser(String userId) {
		sqlSession = sqlSessionFactory.openSession();
		UserVO userVO = userDao.selectUser(sqlSession, userId);
		sqlSession.close();
		
		return userVO;
	}
	
}