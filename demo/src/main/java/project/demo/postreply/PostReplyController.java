package project.demo.postreply;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import project.demo.config.CustomOAuth2User;
import project.demo.entity.PostEntity;
import project.demo.entity.ReplyEntity;
import project.demo.entity.UserEntity;
import project.demo.post.PostService;
import project.demo.postreply.DTO.ReplyListDTO;
import project.demo.user.UserService;

@RestController
public class PostReplyController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    PostReplyService postReplyService;

    @PostMapping("/post/writeReply")
    public ResponseEntity<?> writePostReply(
        @AuthenticationPrincipal CustomOAuth2User customOAuth2User,
        @RequestBody ReplyListDTO replyListDTO) {

        System.out.println("**************************** 경로 /post/replyPost");

        UserEntity user = userService.findUser(customOAuth2User.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 정보를 찾을 수 없습니다.");
        }

        PostEntity postEntity = postService.findPost(replyListDTO.getPostId()).get();
        if (postEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }

        ReplyEntity replyEntity = new ReplyEntity(replyListDTO.getContent(), postEntity, user);
        Long replyId = postReplyService.savePostReply(replyEntity);

        if (replyId != null) {
            return ResponseEntity.status(HttpStatus.OK).body("댓글 정상등록 완료");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글이 정상 등록되지 않았습니다");
    }

    @GetMapping("/post/{id}/reply")
    public ResponseEntity<?> ReplyList(@PathVariable("id") Long postId) {
        System.out.println("**************************** 경로 /post/{id}/reply"+"  replyId: "+postId);

        List<ReplyEntity> replyList = postReplyService.postReplyList(postId);
        if (replyList == null || replyList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("댓글이 없습니다");
        }

        List<ReplyListDTO> list = new ArrayList<ReplyListDTO>();
        for (ReplyEntity reply : replyList) {
            list.add(new ReplyListDTO(reply));
        }

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/post/{id}/reply")
    public ResponseEntity<?> DeleteReply(@PathVariable("id") Long replyId) {
        System.out.println("**************************** 경로delete /post/{id}/reply"+"  replyId: "+replyId);

        try {
            postReplyService.DeleteReply(replyId);
            return ResponseEntity.status(HttpStatus.OK).body("정상 삭제완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 중 오류가 발생했습니다.");
        }
    }

    @PutMapping("/post/{id}/reply")
    public ResponseEntity<?> UpdateReply(
        @PathVariable("id") Long id,
        @RequestBody ReplyListDTO replyListDTO) {

        System.out.println("**************************** 경로update /post/{id}/reply");

        try {
            postReplyService.updatePostReply(id, replyListDTO.getContent());
            return ResponseEntity.status(HttpStatus.OK).body("정상 수정 완료");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 중 오류가 발생했습니다.");
        }
    }
    @PutMapping("/post/{id}/{replyId}")
    public ResponseEntity<?> likeReply(@PathVariable("postId")Long id,@PathVariable("replyId")Long replyId){
    	
    	return null;
    }
}