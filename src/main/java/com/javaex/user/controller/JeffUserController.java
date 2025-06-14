package com.javaex.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaex.user.model.JeffUserEventVo;
import com.javaex.user.model.JeffUserPersonalInfoEditVo;
import com.javaex.user.model.JeffUserProfileVo;
import com.javaex.user.service.JeffUserService;
import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;
import com.javaex.debate.model.JeffCommentVo;
import com.javaex.debate.model.JeffDebateVo;
import com.javaex.review.model.JeffReviewVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/user/mypage")
public class JeffUserController {

	@Autowired
	private JeffUserService jeffuserservice;
	
	@Autowired
	private S3Service S3Service;
	
	//상품 정보 가져오기
	 @GetMapping("/userpersonalinfoedit/me")
	 public JsonResult getUserPersonalInfo(HttpServletRequest request) {
		 System.out.println("controller.getUserPersonalInfo() ");   
	     
		 int userId = JwtUtil.getNoFromHeader(request);
		 System.out.println(userId);
	     try {
	            // Fetch user profile
	            JeffUserProfileVo userProfile = jeffuserservice.exeGetUserProfile(userId);
	            System.out.println(userProfile.toString());

	            // Fetch user events
	            List<JeffUserEventVo> userEvents = jeffuserservice.exeGetUserEventsList(userId);
	            if (userEvents == null) {
	                userEvents = List.of(); // Return an empty list if null
	            }
	          

	            
	            // Combine profile and events into a response object
	            JeffUserPersonalInfoEditVo combined = new JeffUserPersonalInfoEditVo();
	            System.out.println(userProfile.toString());
	            combined.setUserProfile(userProfile);
	            
	            System.out.println("---------------------------------------------");
	            System.out.println(userProfile.toString());

	            combined.setUserEvents(userEvents);
	            System.out.println(userEvents.toString());
	            System.out.println("---------------------------------------------");
	            
	            System.out.println(combined.toString());
	            

	            return JsonResult.success(combined);
	        } catch (Exception e) {
	            return JsonResult.fail(e.getMessage());
	        }
	 }
	 
	 @PutMapping("/userpersonalinfoedit/update")
	 public JsonResult updateUserProfile(
	     HttpServletRequest request,
	     @RequestParam("memberId") int memberId,
	     @RequestParam("email") String email,
	     @RequestParam("phoneNumber") String phoneNumber,
	     @RequestParam("name") String name,
	     @RequestParam("userNickname") String userNickname,
	     @RequestParam(value = "passwordHash", required = false) String passwordHash,
	     @RequestParam(value = "userProfileImageUrl", required = false) String userProfileImageUrl,
	     @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture
	 ) {
	     System.out.println("Received memberId: " + memberId);
	     System.out.println("Received email: " + email);
	     System.out.println("Received phoneNumber: " + phoneNumber);
	     System.out.println("Received name: " + name);
	     System.out.println("Received userNickname: " + userNickname);
	     System.out.println("Received passwordHash: " + passwordHash);
	     System.out.println("Received userProfileImageUrl: " + userProfileImageUrl);

	     // Handle profile picture upload
	     String savedFileName = null;
	     if (profilePicture != null && !profilePicture.isEmpty()) {
	         try {
	             savedFileName = S3Service.uploadFile(profilePicture); // Implement your file upload logic
	             System.out.println("Uploaded profile picture: " + savedFileName);
	         } catch (Exception e) {
	             return JsonResult.fail("Profile picture upload failed: " + e.getMessage());
	         }
	     }

	     // Update user profile with the received data
	     JeffUserProfileVo userProfile = new JeffUserProfileVo();
	     userProfile.setMemberId(memberId);
	     userProfile.setEmail(email);
	     userProfile.setPhoneNumber(phoneNumber);
	     userProfile.setName(name);
	     userProfile.setUserNickname(userNickname);
	     userProfile.setPasswordHash(passwordHash);
	     userProfile.setUserProfileImageUrl(savedFileName != null ? savedFileName : userProfileImageUrl);

	     try {
	         int updateCount = jeffuserservice.exeUpdateUserProfile(memberId, userProfile);
	         if (updateCount == 2) {
	             return JsonResult.success("Profile updated successfully");
	         } else {
	             return JsonResult.fail("Profile update failed");
	         }
	     } catch (Exception e) {
	         return JsonResult.fail("Error updating profile: " + e.getMessage());
	     }
	 }

	 @PostMapping("/userevent/add")
	 public JsonResult addUserEvent(
	         HttpServletRequest request,
	         @RequestBody JeffUserEventVo jeffUserEventVo
	 ) {
	     System.out.println(jeffUserEventVo.toString());

	     try {
	         int count = jeffuserservice.exeAddUserEvent(jeffUserEventVo);

	         if (count != 1) { // Insert failed
	             return JsonResult.fail("Failed to add event");
	         } else { // Insert success
	             return JsonResult.success(count);
	         }
	     } catch (Exception e) {
	         return JsonResult.fail(e.getMessage());
	     }
	 }


	    /**
	     * Delete an existing user event
	     */
	 @DeleteMapping("/userevent/delete/{anniversaryId}")
	 public JsonResult deleteUserEvent(@PathVariable("anniversaryId") int anniversaryId) {
	        System.out.println("Deleting event with eventId: " + anniversaryId);

	        try {
	            int count = jeffuserservice.exeDeleteUserEvent(anniversaryId);

	            if (count != 1) { // Delete failed
	                return JsonResult.fail("Failed to delete event");
	            } else { // Delete success
	                return JsonResult.success(count);
	            }
	        } catch (Exception e) {
	            return JsonResult.fail(e.getMessage());
	        }
	    }
	    
	 @PutMapping("/userpersonalinfoedit/withdraw")
	 public JsonResult withdrawUser(
		         HttpServletRequest request
		 ) {
		     int userId = JwtUtil.getNoFromHeader(request);
		     System.out.println("JeffUserController.withdrawUser()");

		     try {

		         int count = jeffuserservice.exeWithdrawUser(userId);

		         if (count != 1) { // Update failed
		             return JsonResult.fail("Update failed");
		         } else { // Update success
		             return JsonResult.success(count);
		         }
		     } catch (Exception e) {
		         return JsonResult.fail(e.getMessage());
		     }
		 }
	
	 // 내 라이팅 리스트

	 @GetMapping(value = "/mywriting/debatelist")
	 public JsonResult getDebateList(
	     @RequestParam(defaultValue = "1") int page,
	     @RequestParam(defaultValue = "10") int size,
	     @RequestParam(required = false) String keyword,
	     HttpServletRequest request
	 ) {
	     int userId = JwtUtil.getNoFromHeader(request); // Get userId from JWT
	     System.out.println("JeffDebateController.getDebateList");

	     try {
	         // Calculate offset for pagination
	         int offset = (page - 1) * size;

	         // Fetch debate list and total count
	         List<JeffDebateVo> debateVo = jeffuserservice.exeGetDebateList(userId, offset, size, keyword);
	         int totalCount = jeffuserservice.exegetDebateListCount(userId, keyword);

	         // Calculate total pages
	         int totalPages = (int) Math.ceil((double) totalCount / size);

	         // Create response data
	         Map<String, Object> apiData = new HashMap<>();
	         apiData.put("data", debateVo);
	         apiData.put("totalCount", totalCount);
	         apiData.put("totalPages", totalPages);

	         System.out.println(apiData.toString());

	         return JsonResult.success(apiData);
	     } catch (Exception e) {
	         return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
	     }
	 }
	 
	 @GetMapping(value = "/mywriting/commentlist")
	 public JsonResult getCommentList(
	     @RequestParam(defaultValue = "1") int page,
	     @RequestParam(defaultValue = "10") int size,
	     @RequestParam(required = false) String keyword,
	     HttpServletRequest request
	 ) {
	     System.out.println("JeffDebateController.getCommentList");
	     int userId = JwtUtil.getNoFromHeader(request); // Extract user ID from JWT

	     try {
	         // Pagination logic
	         int offset = (page - 1) * size;

	         // Fetch comment list and total count
	         List<JeffCommentVo> commentVoList = jeffuserservice.exeGetCommentList(userId, offset, size, keyword);
	         int totalCount = jeffuserservice.exegetCommentListCount(userId, keyword);

	         // Calculate total pages
	         int totalPages = (int) Math.ceil((double) totalCount / size);

	         // Prepare response data
	         Map<String, Object> apiData = new HashMap<>();
	         apiData.put("data", commentVoList);
	         apiData.put("totalCount", totalCount);
	         apiData.put("totalPages", totalPages);

	         System.out.println(apiData.toString());

	         return JsonResult.success(apiData);
	     } catch (Exception e) {
	         return JsonResult.fail("댓글 리스트를 가져오는 중 오류가 발생했습니다.");
	     }
	 }

	 
	 @GetMapping(value = "/mywriting/reviewlist")
	 public JsonResult getReviewList(
	         @RequestParam(defaultValue = "1") int page,
	         @RequestParam(defaultValue = "10") int size,
	         @RequestParam(required = false) String keyword,
	         HttpServletRequest request) {
	     int userId = JwtUtil.getNoFromHeader(request); // Extract user ID from token
	     System.out.println("JeffReviewController.getReviewList");
	     try {
	         // Pagination logic
	         int offset = (page - 1) * size;

	         // Fetch reviews and total count
	         List<JeffReviewVo> reviews = jeffuserservice.getReviewList(userId, offset, size, keyword);
	         int totalCount = jeffuserservice.getReviewListCount(userId, keyword);
	         int totalPages = (int) Math.ceil((double) totalCount / size);

	         // Prepare API response
	         Map<String, Object> apiData = new HashMap<>();
	         apiData.put("data", reviews);
	         apiData.put("totalCount", totalCount);
	         apiData.put("totalPages", totalPages);

	         return JsonResult.success(apiData);
	     } catch (Exception e) {
	         return JsonResult.fail("리뷰 리스트를 가져오는 중 오류가 발생했습니다.");
	     }
	 }

	 
}

