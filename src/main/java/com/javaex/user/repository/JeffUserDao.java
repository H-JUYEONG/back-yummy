package com.javaex.user.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.cakedesign.model.JeffLikedDesignVo;
import com.javaex.debate.model.JeffCommentVo;
import com.javaex.debate.model.JeffDebateVo;
import com.javaex.review.model.JeffReviewVo;
import com.javaex.user.model.JeffUserEventVo;
import com.javaex.user.model.JeffUserProfileVo;

@Repository
public class JeffUserDao {
	
	@Autowired
	private SqlSession sqlSession;

	public JeffUserProfileVo getUserDetail(int userId) {
		System.out.println("JeffUserDao.getUserDetail()");
		
		return sqlSession.selectOne("JeffUser.getUserDetail", userId);
	}
	
	public List<JeffUserEventVo> getUserEventList(int userId) {
		System.out.println("JeffUserDao.getUserEventList()");
		
		return sqlSession.selectList("JeffUser.getUserEventList", userId);
	}
	
	public int updateUserProfile(JeffUserProfileVo jeffUserProfileVo, int userId) {
        System.out.println("JeffUserDao.updateUserProfile()");
        System.out.println(jeffUserProfileVo.toString());
        
        int count = sqlSession.update("JeffUser.updateUserProfile", jeffUserProfileVo);
        System.out.println(count + "update");
        
        return count;
    }
	
	
	public int insertUserEvent(JeffUserEventVo jeffUserEventVo) {
        System.out.println("Inserting user event in DAO");
        System.out.println(jeffUserEventVo.toString());
        return sqlSession.insert("JeffUser.insertUserEvent", jeffUserEventVo);
    }

    public int deleteUserEvent(int eventId) {
        System.out.println("Deleting user event in DAO");
        return sqlSession.delete("JeffUser.deleteUserEvent", eventId);
    }
    
	public int withdrawUser(int userId) {
	    System.out.println("JeffUserDao.withdrawUser()");
	    return sqlSession.update("JeffUser.withdrawUser", userId);
	}
	
	//writing list --------------------------------------------------------------
	public List<JeffDebateVo> selectDebateList(int memberId, int offset, int size, String keyword) {
	    System.out.println("JeffUserDao.selectDebateList()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("memberId", memberId); // Filter by user ID
	    params.put("offset", offset); // Pagination offset
	    params.put("size", size); // Pagination size
	    params.put("searchTerm", keyword); // Search keyword

	    return sqlSession.selectList("JeffUser.selectDebateList", params);
	}

	public int selectDebateListCount(int memberId, String keyword) {
	    System.out.println("JeffUserDao.selectDebateListCount()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("memberId", memberId); // Filter by user ID
	    params.put("searchTerm", keyword); // Search keyword

	    return sqlSession.selectOne("JeffUser.selectDebateListCount", params);
	}
	
	public List<JeffCommentVo> selectCommentList(int memberId, int offset, int size, String keyword) {
	    System.out.println("JeffUserDao.selectCommentList()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("memberId", memberId); // Filter by user ID
	    params.put("offset", offset); // Pagination offset
	    params.put("size", size); // Pagination size
	    params.put("searchTerm", keyword); // Search keyword

	    return sqlSession.selectList("JeffUser.selectCommentList", params);
	}

	public int selectCommentListCount(int memberId, String keyword) {
	    System.out.println("JeffUserDao.selectCommentListCount()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("memberId", memberId); // Filter by user ID
	    params.put("searchTerm", keyword); // Search keyword

	    return sqlSession.selectOne("JeffUser.selectCommentListCount", params);
	}

	public List<JeffReviewVo> selectReviewList(int userId, int offset, int size, String keyword) {
	    System.out.println("JeffUserDao.selectReviewList()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("userId", userId); // Filter by user ID
	    params.put("offset", offset); // Pagination offset
	    params.put("size", size); // Pagination size
	    params.put("searchTerm", keyword); // Search term
	    
	    List<JeffReviewVo> vo = sqlSession.selectList("JeffUser.selectReviewList", params);
	    
	    return vo;
	}

	public int selectReviewListCount(int userId, String keyword) {
	    System.out.println("JeffUserDao.selectReviewListCount()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("userId", userId); // Filter by user ID
	    params.put("searchTerm", keyword); // Search term

	    return sqlSession.selectOne("JeffUser.selectReviewListCount", params);
	}

    
}
