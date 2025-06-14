package com.javaex.debate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.user.model.JeffUserEventVo;
import com.javaex.user.model.JeffUserPersonalInfoEditVo;
import com.javaex.user.model.JeffUserProfileVo;
import com.javaex.cakedesign.model.JeffLikedDesignVo;
import com.javaex.cakedesign.model.VendorCakeDesignVo;
import com.javaex.common.storage.S3Service;
import com.javaex.common.util.JsonResult;
import com.javaex.common.util.JwtUtil;
import com.javaex.debate.model.JeffCommentVo;
import com.javaex.debate.model.JeffDebateVo;
import com.javaex.debate.model.JeffVoteVo;
import com.javaex.debate.service.JeffDebateService;
import com.javaex.product.model.JeffLikedProductVo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/debate")
public class JeffDebateController {


    @Autowired
    private JeffDebateService jeffDebateService;
    
    @Autowired
    private S3Service S3Service;
    
    // Fetch list of debates
    @GetMapping(value = "/board")
    public JsonResult getDebateList(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(required = false) String keyword,
                                    @RequestParam(required = false) String category,
                                    HttpServletRequest request) {
        System.out.println("JeffDebateController.getDebateList");
        try {
            int offset = (page - 1) * size;
            List<JeffDebateVo> debateVo = jeffDebateService.exeGetDebateList(offset, size, keyword, category);
            int totalCount = jeffDebateService.exeGetDebateListCount(keyword, category);
            int totalPages = (int) Math.ceil((double) totalCount / size);

            Map<String, Object> apiData = new HashMap<>();
            apiData.put("data", debateVo);
            apiData.put("totalCount", totalCount);
            apiData.put("totalPages", totalPages);

            return JsonResult.success(apiData);
        } catch (Exception e) {
            return JsonResult.fail("도안 리스트를 가져오는 중 오류가 발생했습니다.");
        }
    }

    // Add a new debate
    @PostMapping(value = "/debateinsert/post")
    public JsonResult addDebate(HttpServletRequest request,
                                @RequestParam("debate_title") String title,
                                @RequestParam("debate_category") String category,
                                @RequestParam("debate_left_item_type") String leftItemType,
                                @RequestParam(value = "debate_left_item_id", required = false) Integer leftItemId,
                                @RequestParam("debate_right_item_type") String rightItemType,
                                @RequestParam(value = "debate_right_item_id", required = false) Integer rightItemId,
                                @RequestParam("debate_content") String content,
                                @RequestPart(value = "debate_left_image_url") String leftImgUrl,
                                @RequestPart(value = "debate_right_image_url") String rightImgUrl,
                                @RequestPart(value = "leftImage", required = false) MultipartFile leftImage,
                                @RequestPart(value = "rightImage", required = false) MultipartFile rightImage) {
        int userId = JwtUtil.getNoFromHeader(request);
        try {
            JeffDebateVo debateVo = new JeffDebateVo();
            debateVo.setDebate_title(title);
            debateVo.setDebate_category(category);
            debateVo.setDebate_left_item_type(leftItemType);
            debateVo.setDebate_left_item_id(leftItemId);
            debateVo.setDebate_left_image_url(leftImgUrl);
            debateVo.setDebate_right_item_type(rightItemType);
            debateVo.setDebate_right_item_id(rightItemId);
            debateVo.setDebate_right_image_url(rightImgUrl);
            debateVo.setDebate_content(content);
            debateVo.setMember_id(userId);

            // Handle file uploads
            if (leftImage != null && !leftImage.isEmpty()) {
                String leftImagePath = S3Service.uploadFile(leftImage);
                debateVo.setDebate_left_image_url(leftImagePath);
            }
            if (rightImage != null && !rightImage.isEmpty()) {
                String rightImagePath = S3Service.uploadFile(rightImage);
                debateVo.setDebate_right_image_url(rightImagePath);
            }

            int count = jeffDebateService.exeAddDebate(debateVo);
            if (count != 1) {
                return JsonResult.fail("Failed to add debate");
            } else {
                return JsonResult.success(count);
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }
    																																																																																																																																																																																																												
    @PutMapping("/debateupdate/{debateId}")
    public JsonResult updateDebate(HttpServletRequest request,
                                   @PathVariable("debateId") int debateId,
                                   @RequestParam("debate_title") String title,
                                   @RequestParam("debate_category") String category,
                                   @RequestParam("debate_left_item_type") String leftItemType,
                                   @RequestParam(value = "debate_left_item_id", required = false) Integer leftItemId,
                                   @RequestParam("debate_right_item_type") String rightItemType,
                                   @RequestParam(value = "debate_right_item_id", required = false) Integer rightItemId,
                                   @RequestParam("debate_content") String content,
                                   @RequestPart(value = "debate_left_image_url", required = false) String leftImgUrl,
                                   @RequestPart(value = "debate_right_image_url", required = false) String rightImgUrl,
                                   @RequestPart(value = "leftImage", required = false) MultipartFile leftImage,
                                   @RequestPart(value = "rightImage", required = false) MultipartFile rightImage) {
        int userId = JwtUtil.getNoFromHeader(request);

        try {
            JeffDebateVo debateVo = new JeffDebateVo();
            debateVo.setDebate_id(debateId);
            debateVo.setDebate_title(title);
            debateVo.setDebate_category(category);
            debateVo.setDebate_left_item_type(leftItemType);
            debateVo.setDebate_left_item_id(leftItemId);
            debateVo.setDebate_left_image_url(leftImgUrl);
            debateVo.setDebate_right_item_type(rightItemType);
            debateVo.setDebate_right_item_id(rightItemId);
            debateVo.setDebate_right_image_url(rightImgUrl);
            debateVo.setDebate_content(content);
            debateVo.setMember_id(userId);

            // Handle file uploads
            if (leftImage != null && !leftImage.isEmpty()) {
                String leftImagePath = S3Service.uploadFile(leftImage);
                debateVo.setDebate_left_image_url(leftImagePath);
            }
            if (rightImage != null && !rightImage.isEmpty()) {
                String rightImagePath = S3Service.uploadFile(rightImage);
                debateVo.setDebate_right_image_url(rightImagePath);
            }

            int count = jeffDebateService.exeUpdateDebate(debateVo);
            if (count != 1) {
                return JsonResult.fail("Failed to update debate");
            } else {
                return JsonResult.success(count);
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    @DeleteMapping("/debatedel/{debateId}")
    public JsonResult deleteDebate(HttpServletRequest request, @PathVariable("debateId") int debateId) {
        int userId = JwtUtil.getNoFromHeader(request);

        try {
            // Create a VO object to pass user ID and debate ID
            JeffDebateVo debateVo = new JeffDebateVo();
            debateVo.setDebate_id(debateId);
            debateVo.setMember_id(userId);

            int count = jeffDebateService.exeDeleteDebate(debateVo);
            if (count != 1) {
                return JsonResult.fail("Failed to delete debate");
            } else {
                return JsonResult.success(count);
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }
    
    // Fetch wishlist products
    @GetMapping("/debateinsert/productmodal")
    public JsonResult getWishlistProducts(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "8") int size,
                                          @RequestParam(required = false) String keyword,
                                          HttpServletRequest request) {
        try {
            String token = JwtUtil.getTokenByHeader(request);
            if (token != null && JwtUtil.checkToken(token)) {
                int memberId = Integer.parseInt(JwtUtil.getSubjectFromToken(token));

                JeffLikedProductVo productVo = new JeffLikedProductVo();
                productVo.setMemberId(memberId);
                productVo.setPage(page);
                productVo.setSize(size);
                productVo.setSearchKeyword(keyword);

                Map<String, Object> result = jeffDebateService.exeGetLikedProduct(productVo);
                return JsonResult.success(result);
            } else {
                return JsonResult.fail("Invalid or missing token.");
            }
        } catch (Exception e) {
            return JsonResult.fail("위시리스트 조회 실패: " + e.getMessage());
        }
    }

    // Fetch wishlist designs
    @GetMapping("/debateinsert/designmodal")
    public JsonResult getWishlistDesign(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "8") int size,
                                        @RequestParam(required = false) String keyword,
                                        HttpServletRequest request) {
        try {
            String token = JwtUtil.getTokenByHeader(request);
            if (token != null && JwtUtil.checkToken(token)) {
                int memberId = Integer.parseInt(JwtUtil.getSubjectFromToken(token));

                JeffLikedDesignVo designVo = new JeffLikedDesignVo();
                designVo.setMemberId(memberId);
                designVo.setPage(page);
                designVo.setSize(size);
                designVo.setSearchKeyword(keyword);

                Map<String, Object> result = jeffDebateService.exeGetLikedDesign(designVo);
                return JsonResult.success(result);
            } else {
                return JsonResult.fail("Invalid or missing token.");
            }
        } catch (Exception e) {
            return JsonResult.fail("위시리스트 조회 실패: " + e.getMessage());
        }
    }

    // Fetch debate details
    @GetMapping("/debateview/{debateId}")
    public JsonResult getDebateDetail(HttpServletRequest request,
                                      @PathVariable("debateId") int debateId) {
        try {
            JeffDebateVo debateVo = jeffDebateService.exeGetDebateDetail(debateId);
            return JsonResult.success(debateVo);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    // Fetch comments for a debate
    @GetMapping("/getComment/{debateId}")
    public JsonResult getCommentList(@PathVariable("debateId") int debateId) {
        try {
            List<JeffCommentVo> commentList = jeffDebateService.exeGetCommentList(debateId);
            return JsonResult.success(commentList);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    // Post a new comment
    @PostMapping(value = "/commentpost")
    public JsonResult postComment(HttpServletRequest request,
                                  @RequestParam("debate_comment_content") String commentContent,
                                  @RequestParam("debate_id") Integer debateId) {
        int userId = JwtUtil.getNoFromHeader(request);
        try {
            JeffCommentVo commentVo = new JeffCommentVo();
            commentVo.setMember_id(userId);
            commentVo.setDebate_id(debateId);
            commentVo.setDebate_comment_content(commentContent);

            int count = jeffDebateService.exeAddComment(commentVo);
            if (count != 1) {
                return JsonResult.fail("댓글 등록 실패");
            }
            return JsonResult.success(commentVo);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    // Fetch vote list for a debate
    @GetMapping(value = "/votelist/{debateId}")
    public JsonResult getVoteList(@PathVariable("debateId") Integer debateId) {
        try {
        	System.out.println("JeffDebateController.getVoteList");
        	
            List<JeffVoteVo> voteList = jeffDebateService.exeGetVoteList(debateId);
            System.out.println(voteList.toString());
            
            return JsonResult.success(voteList);
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    // Cast a vote
    @PostMapping(value = "/castvote")
    public JsonResult castVote(HttpServletRequest request,
                               @RequestBody Map<String, Object> payload) {
        System.out.println("JeffDebateController.castVote - Start");
        int userId = JwtUtil.getNoFromHeader(request);
        System.out.println("User ID: " + userId);
        System.out.println("Payload: " + payload);

        try {
            // Safely parse debate_id from payload
            Integer debateId = Integer.parseInt(payload.get("debate_id").toString());
            String side = payload.get("side").toString();

            JeffVoteVo voteVo = new JeffVoteVo();
            voteVo.setDebate_id(debateId);
            voteVo.setMember_id(userId);
            voteVo.setSide(side);

            System.out.println("VoteVo: " + voteVo);

            int count = jeffDebateService.exeCastVote(voteVo);
            System.out.println("Service Response: " + count);

            if (count != 1) {
                return JsonResult.fail("투표 등록 실패");
            } else {
                return JsonResult.success(voteVo);
            }
        } catch (Exception e) {
            System.out.println("Exception in castVote: " + e.getMessage());
            return JsonResult.fail(e.getMessage());
        }
    }



    // Update a vote
    @PutMapping(value = "/updateVote")
    public JsonResult updateVote(HttpServletRequest request,
                                 @RequestBody Map<String, Object> payload) {
    	System.out.println("JeffDebateController.updateVote");
    	int userId = JwtUtil.getNoFromHeader(request);
        try {
            JeffVoteVo voteVo = new JeffVoteVo();
            voteVo.setDebate_id((Integer) payload.get("debate_id"));
            voteVo.setMember_id(userId);
            voteVo.setSide((String) payload.get("side"));
            
            System.out.println(voteVo.toString());

            int count = jeffDebateService.exeUpdateVote(voteVo);
            if (count != 1) {
                return JsonResult.fail("투표 수정 실패");
            } else {
                return JsonResult.success("투표 수정 성공");
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    // Delete a vote
    @DeleteMapping(value = "/deleteVote/{debateId}")
    public JsonResult deleteVote(HttpServletRequest request,
                                 @PathVariable("debateId") Integer debateId) {
        System.out.println("JeffDebateController.deleteVote");
    	
    	int userId = JwtUtil.getNoFromHeader(request);
    	System.out.println(userId);
    	System.out.println(debateId);
        
        try {
        	JeffVoteVo voteVo = new JeffVoteVo();
        	voteVo.setMember_id(userId);
        	voteVo.setDebate_id(debateId);
            int count = jeffDebateService.exeDeleteVote(voteVo);
            if (count != 1) {
                return JsonResult.fail("투표 취소 실패");
            } else {
                return JsonResult.success("투표 취소 성공");
            }
        } catch (Exception e) {
            return JsonResult.fail(e.getMessage());
        }
    }
    
}
