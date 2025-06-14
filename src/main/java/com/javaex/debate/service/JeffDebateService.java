package com.javaex.debate.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.cakedesign.model.JeffLikedDesignVo;
import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.debate.model.JeffCommentVo;
import com.javaex.debate.model.JeffDebateVo;
import com.javaex.debate.model.JeffVoteVo;
import com.javaex.debate.repository.JeffDebateDao;
import com.javaex.mypage.model.DdrMypageWishlistVo;
import com.javaex.product.model.JeffLikedProductVo;
import com.javaex.user.model.JeffUserProfileVo;

@Service
public class JeffDebateService {
	@Autowired
	private JeffDebateDao jeffDebateDao;
	
	//Get DebateList
	public Map<String, Object> exeGetLikedProduct (JeffLikedProductVo productVo){
		 // 페이징 계산
		System.out.println("JeffDebateService.exeGetLikedProduct()");
       int offset = (productVo.getPage() - 1) * productVo.getSize();
       productVo.setOffset(offset);
       
       // 데이터 조회 전 로그
       System.out.println("조회 조건: " + productVo.toString());
       
       // 데이터 조회
       List<JeffLikedProductVo> content = jeffDebateDao.selectLikedProduct(productVo);
       int totalElements = jeffDebateDao.selectLikedProductCount(productVo);
       
       // 조회 결과 로그
       System.out.println("총 데이터 수: " + totalElements);
       System.out.println("조회된 데이터: " + content.size() + "건");
       
       int totalPages = (int) Math.ceil((double) totalElements / productVo.getSize());
       
       Map<String, Object> result = new HashMap<>();
       result.put("content", content);
       result.put("totalPages", totalPages);
       result.put("totalElements", totalElements);
       result.put("currentPage", productVo.getPage());
       
       return result;
	}
	
	public Map<String, Object> exeGetLikedDesign (JeffLikedDesignVo designVo){
		 // 페이징 계산
		System.out.println("JeffDebateService.exeGetLikedProduct()");
      int offset = (designVo.getPage() - 1) * designVo.getSize();
      designVo.setOffset(offset);
      
      // 데이터 조회 전 로그
      System.out.println("조회 조건: " + designVo.toString());
      
      // 데이터 조회
      List<JeffLikedDesignVo> content = jeffDebateDao.selectLikedDesign(designVo);
      int totalElements = jeffDebateDao.selectLikedDesignCount(designVo);
      
      // 조회 결과 로그
      System.out.println("총 데이터 수: " + totalElements);
      System.out.println("조회된 데이터: " + content.size() + "건");
      
      int totalPages = (int) Math.ceil((double) totalElements / designVo.getSize());
      
      Map<String, Object> result = new HashMap<>();
      result.put("content", content);
      result.put("totalPages", totalPages);
      result.put("totalElements", totalElements);
      result.put("currentPage", designVo.getPage());
      
      return result;
	}
	
	/* File Upload */
    public String exeUpload(MultipartFile file) {
        System.out.println("JeffUserService.exeUpload()");

        String saveDir;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("linux")) {
            saveDir = "/app/upload";
        } else {
            saveDir = "C:\\javaStudy\\upload";
        }

        String orgName = file.getOriginalFilename();
        String exeName = orgName.substring(orgName.lastIndexOf("."));
        String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exeName;
        String filePath = saveDir + File.separator + saveName;

        try {
            byte[] fileData = file.getBytes();
            OutputStream os = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bos.write(fileData);
            bos.close();
        } catch (Exception e) {
            System.out.println("File upload error: " + e.getMessage());
            return null;
        }

        return saveName;
    }
	
	public int exeAddDebate(JeffDebateVo debateVo) {
		System.out.println("JeffDebateService.exeAddDebate	()");
		System.out.println("Adding user event in service");
		int ans = jeffDebateDao.insertDebate(debateVo);
		System.out.println(ans);
	
		return ans;
	}
	
    public int exeUpdateDebate(JeffDebateVo debateVo) {
        System.out.println("Updating debate in service");
        int result = jeffDebateDao.updateDebate(debateVo);
        if (result != 1) {
            throw new RuntimeException("Failed to update debate.");
        }
        return result;
    }
	
	public List<JeffDebateVo> exeGetDebateList(int offset, int size, String keyword, String category) {
		System.out.println("JeffDebateService.exeGetDebateList()");
		return jeffDebateDao.selectDebateList(offset, size, keyword, category);
	}
	
	public int exeGetDebateListCount(String keyword, String category) {
		System.out.println("JeffDebateService.exegetDebateListCount()");
		return jeffDebateDao.selectDebateListCount(keyword, category);
	}
	
    public JeffDebateVo exeGetDebateDetail(int debateId ) {
        System.out.println("JeffDebateService.exeGetDebateDetail()");
        return jeffDebateDao.getDebateDetail(debateId);
    }
	
	public List<JeffCommentVo> exeGetCommentList(int debateId) {
		System.out.println("JeffDebateService.exeGetCommentList()");
		return jeffDebateDao.getCommentList(debateId);
	}
    
	public int exeAddComment(JeffCommentVo vo) {
		System.out.println("JeffDebateService.exeAddComment	()");
		int ans = jeffDebateDao.insertComment(vo);
		System.out.println(ans);
	
		return ans;
	}
	
	public int exeCastVote(JeffVoteVo voteVo) {
		System.out.println("JeffDebateService.exeCastVote()");
        return jeffDebateDao.insertVote(voteVo);
    }
	
	public List<JeffVoteVo> exeGetVoteList(int debateId) {
        return jeffDebateDao.selectVoteList(debateId);
    }
	
	public int exeUpdateVote(JeffVoteVo voteVo) {
	    // Call the DAO to update the vote
	    return jeffDebateDao.updateVote(voteVo);
	}
	
	public int exeDeleteVote(JeffVoteVo voteVo) {
	    // Call the DAO to delete the vote
		System.out.println("JeffDebateService.exeDeleteVote	()");
	    return jeffDebateDao.deleteVote(voteVo);
	}

	public int exeDeleteDebate(JeffDebateVo debateVo) {
	    System.out.println("JeffDebateService.exeDeleteDebate()");
	    System.out.println("Deleting debate with ID: " + debateVo.getDebate_id());
	    return jeffDebateDao.deleteDebate(debateVo);
	}

}
