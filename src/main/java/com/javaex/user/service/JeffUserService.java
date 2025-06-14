package com.javaex.user.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.debate.model.JeffCommentVo;
import com.javaex.debate.model.JeffDebateVo;
import com.javaex.review.model.JeffReviewVo;
import com.javaex.user.model.JeffUserEventVo;
import com.javaex.user.model.JeffUserProfileVo;
import com.javaex.user.repository.JeffUserDao;

@Service
public class JeffUserService {

    @Autowired
    private JeffUserDao jeffUserDao;

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

    /* Get User Profile */
    public JeffUserProfileVo exeGetUserProfile(int userId) {
        System.out.println("JeffUserService.exeGetUserProfile()");
        return jeffUserDao.getUserDetail(userId);
    }

    /* Get User Events */
    public List<JeffUserEventVo> exeGetUserEventsList(int userId) {
    	
    	
        System.out.println("JeffUserService.exeGetUserEvents()");
        return jeffUserDao.getUserEventList(userId);
    }

    /* Update User Profile */
    public int exeUpdateUserProfile(int userId, JeffUserProfileVo jeffUserProfileVo) {
        System.out.println("JeffUserService.exeUpdateUserProfile()");

        // Attach userId to the VO
        jeffUserProfileVo.setMemberId(userId);

        // Update profile in the database
        return jeffUserDao.updateUserProfile(jeffUserProfileVo, userId);
    }

    /* Add User Event */
    public int exeAddUserEvent(JeffUserEventVo jeffUserEventVo) {
    	
        System.out.println("Adding user event in service");
        return jeffUserDao.insertUserEvent(jeffUserEventVo);
    }

    /* Delete User Event */
    public int exeDeleteUserEvent(int eventId) {
        System.out.println("Deleting user event in service");
        return jeffUserDao.deleteUserEvent(eventId);
    }

    /* Withdraw User */
    public int exeWithdrawUser(int userId) {
        System.out.println("JeffUserService.exeWithdrawUser()");
        return jeffUserDao.withdrawUser(userId);
    }
    
    //my writing list ----------------------------------------------------
    
    public List<JeffDebateVo> exeGetDebateList(int memberId, int offset, int size, String keyword) {
        System.out.println("JeffDebateService.exeGetDebateList()");
        return jeffUserDao.selectDebateList(memberId, offset, size, keyword); // Fetch debates for the user
    }

    public int exegetDebateListCount(int memberId, String keyword) {
        System.out.println("JeffDebateService.exegetDebateListCount()");
        return jeffUserDao.selectDebateListCount(memberId, keyword); // Count debates for the user
    }
    
    
    public List<JeffCommentVo> exeGetCommentList(int memberId, int offset, int size, String keyword) {
        System.out.println("JeffDebateService.exeGetCommentList()");
        return jeffUserDao.selectCommentList(memberId, offset, size, keyword); // Fetch comments for the user
    }

    public int exegetCommentListCount(int memberId, String keyword) {
        System.out.println("JeffDebateService.exegetCommentListCount()");
        return jeffUserDao.selectCommentListCount(memberId, keyword); // Count comments for the user
    }

    public List<JeffReviewVo> getReviewList(int userId, int offset, int size, String keyword) {
        System.out.println("JeffUserService.getReviewList()");
        return jeffUserDao.selectReviewList(userId, offset, size, keyword);
    }

    public int getReviewListCount(int userId, String keyword) {
        System.out.println("JeffUserService.getReviewListCount()");
        return jeffUserDao.selectReviewListCount(userId, keyword);
    }

    
    
    
    
}
