package project.demo.postreply;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.demo.config.CustomOAuth2User;
import project.demo.entity.PostEntity;
import project.demo.entity.ReplyEntity;
import project.demo.entity.UserEntity;
import project.demo.post.PostService;
import project.demo.postreply.DTO.ReplyListDTO;
import project.demo.user.UserService;

@Controller
public class PostReplyController {

	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@Autowired 
	PostReplyService postReplyService;
	@PostMapping("/post/writeReply")
	public ResponseEntity<?> writePostReply(@AuthenticationPrincipal CustomOAuth2User customOAuth2User,@Param("PostId")PostEntity postEntity,@Param("replyText")String replyText){
		System.out.println("**************************** 경로 /post/replyPost");
		UserEntity user =  userService.findUser(customOAuth2User.getUsername());
		ReplyEntity replyEntity = new ReplyEntity(replyText, postEntity, user);
		Long replyId = postReplyService.savePostReply(replyEntity);
		if(replyId!=null) {
			return ResponseEntity.status(HttpStatus.OK).body("댓글 정상등록 완료");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글이 정상 등록되지 않았습니다");
	}
	@GetMapping("/post/{id}/reply")
	public ResponseEntity<?> ReplyList(@RequestParam("id")Long postId){
		List<ReplyListDTO> list = null;
		for(ReplyEntity replyList:postReplyService.postReplyList(postId)) {
			list.add(new ReplyListDTO(replyList));
		}
		if(list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("댓글이 없습니다");
		}
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
