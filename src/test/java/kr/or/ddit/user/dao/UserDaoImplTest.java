package kr.or.ddit.user.dao;

import static org.junit.Assert.*;
import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
	private SqlSession sqlSession;
	private IUserDao userDao;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
		
		userDao = new UserDaoImpl();
	}
	
	@Test
	public void testSelectUser() {
		/***Given***/
		String userId = "brown";
		
		/***When***/
		UserVO userVO = userDao.selectUser(sqlSession, userId);

		/***Then***/
		assertEquals(userId, userVO.getUserId());
	}

}
