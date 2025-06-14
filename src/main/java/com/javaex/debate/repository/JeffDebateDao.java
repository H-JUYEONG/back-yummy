package com.javaex.debate.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.cakedesign.model.JeffLikedDesignVo;
import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.debate.model.JeffCommentVo;
import com.javaex.debate.model.JeffDebateVo;
import com.javaex.debate.model.JeffVoteVo;
import com.javaex.mypage.model.DdrMypageWishlistVo;
import com.javaex.product.model.JeffLikedProductVo;
import com.javaex.user.model.JeffUserEventVo;
import com.javaex.user.model.JeffUserProfileVo;

@Repository
public class JeffDebateDao {

	@Autowired
	private SqlSession sqlSession;
	
	/* 도안 게시판 리스트 전체 */
	public List<JeffDebateVo> selectDebateList(int offset, int size, String keyword, String category) {
	    System.out.println("JeffDebateDao.selectDebateList()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("offset", offset);
	    params.put("size", size);
	    params.put("searchTerm", keyword);
	    params.put("category", category);
	    List<JeffDebateVo> vo = sqlSession.selectList("JeffDebate.selectDebateList", params);
	    System.out.println(vo.toString());
	    
	    
	    return sqlSession.selectList("JeffDebate.selectDebateList", params);
	}

	
	/* 총 데이터 개수 가져오기 */
	public int selectDebateListCount(String keyword, String category) {
	    System.out.println("JeffDebateDao.selectDebateListCount()");
	    Map<String, Object> params = new HashMap<>();
	    params.put("searchTerm", keyword); // Add searchTerm explicitly
	    params.put("category", category);
	    return sqlSession.selectOne("JeffDebate.selectDebateListCount", params);
	}
	
	public int insertDebate(JeffDebateVo debateVo) {
        System.out.println("JeffDebateDao.insertDebate");
        
        System.out.println(debateVo.toString());
        System.out.println("---------------------------------------");
        
        return sqlSession.insert("JeffDebate.insertDebate", debateVo);
    }
	
	public int updateDebate(JeffDebateVo debateVo) {
        System.out.println("JeffDebateDao.updateDebate");
        System.out.println(debateVo.toString());
        System.out.println("---------------------------------------");

        return sqlSession.update("JeffDebate.updateDebate", debateVo);
    }
	
    public List<JeffLikedProductVo> selectLikedProduct(JeffLikedProductVo productVo) {
    	System.out.println("JeffDebateDao.selectLikedProduct()");
    	List<JeffLikedProductVo> vo = sqlSession.selectList("JeffDebate.selectWishlistProducts", productVo);
    	System.out.println(vo.toString());
        return sqlSession.selectList("JeffDebate.selectWishlistProducts", productVo);
    }
    
    public int selectLikedProductCount(JeffLikedProductVo productVo) {
    	System.out.println("JeffDebateDao.selectLikedProductCount()");
        return sqlSession.selectOne("JeffDebate.selectWishlistProductsCount", productVo);
    }
    
    public List<JeffLikedDesignVo> selectLikedDesign(JeffLikedDesignVo designVo) {
    	System.out.println("JeffDebateDao.selectLikedDesign()");
    	List<JeffLikedDesignVo> vo = sqlSession.selectList("JeffDebate.selectWishlistDesign", designVo);
    	System.out.println(vo.toString());
        return sqlSession.selectList("JeffDebate.selectWishlistDesign", designVo);
    }
    
    public int selectLikedDesignCount(JeffLikedDesignVo designVo) {
    	System.out.println("JeffDebateDao.selectLikedDesignCount()");
        return sqlSession.selectOne("JeffDebate.selectWishlistDesignCount", designVo);
    }
    
	public JeffDebateVo getDebateDetail(int debateId) {
		System.out.println("JeffUserDao.getDebateDetail()");
		
		sqlSession.update("JeffDebate.updateViewCount", debateId);
		JeffDebateVo vo= sqlSession.selectOne("JeffDebate.getDebateDetail", debateId);
		System.out.println(vo.toString());
		return vo;
	}
	
	public List<JeffCommentVo> getCommentList(int debateId) {
		System.out.println("JeffUserDao.getCommentList()");
		
		return sqlSession.selectList("JeffDebate.getCommentList", debateId);
	}
	    
	public int insertComment(JeffCommentVo vo) {
        System.out.println("JeffDebateDao.insertComment");
        
        return sqlSession.insert("JeffDebate.insertComment", vo);
    }
	
	public int insertVote(JeffVoteVo voteVo) {
		System.out.println("JeffDebateDao.insertVote");
        return sqlSession.insert("JeffDebate.insertVote", voteVo);
    }
	
    public List<JeffVoteVo> selectVoteList(int debateId) {
        return sqlSession.selectList("JeffDebate.selectVoteList", debateId);
    }
	
    public int updateVote(JeffVoteVo voteVo) {
        return sqlSession.update("JeffDebate.updateVote", voteVo);
    }
    
    public int deleteVote(JeffVoteVo voteVo) {
        System.out.println("JeffDebateDao.deleteVote()");
        System.out.println(voteVo.toString());
        return sqlSession.delete("JeffDebate.deleteVote", voteVo);
    }

    public int deleteDebate(JeffDebateVo debateVo) {
        System.out.println("JeffDebateDao.deleteDebate()");
        System.out.println("Debate ID: " + debateVo.getDebate_id() + ", User ID: " + debateVo.getMember_id());

        // Delete votes related to the debate
        int votesDeleted = sqlSession.delete("JeffDebate.deleteDebateVotes", debateVo);
        System.out.println("Votes deleted: " + votesDeleted);

        // Delete comments related to the debate
        int commentsDeleted = sqlSession.delete("JeffDebate.deleteDebateComments", debateVo);
        System.out.println("Comments deleted: " + commentsDeleted);

        // Delete the debate
        int debateDeleted = sqlSession.delete("JeffDebate.deleteDebate", debateVo);
        System.out.println("Debate deleted: " + debateDeleted);

        return debateDeleted;
    }



}
