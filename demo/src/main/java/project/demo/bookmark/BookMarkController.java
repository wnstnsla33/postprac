package project.demo.bookmark;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.demo.config.CustomOAuth2User;
import project.demo.entity.BookMarkEntity;
import project.demo.entity.PostEntity;
import project.demo.entity.UserEntity;
import project.demo.post.PostService;
import project.demo.user.UserService;

@RestController
public class BookMarkController {
	
	
	@Autowired
	BookMarkService bookMarkService;
	
	@PostMapping("/post/bookMark/{postId}")
	public ResponseEntity<?> createBookmark(@RequestParam("postId")Long postId,@AuthenticationPrincipal CustomOAuth2User customOAuth2User){
		UserEntity user =  userService.findUser(customOAuth2User.getUsername());
		PostEntity post = postService.findPost(postId).get();
		BookMarkEntity bookMarkEntity = new BookMarkEntity(user, post);
		Long bmId = bookMarkService.createBookmark(bookMarkEntity);
		if(bmId>0) 	return ResponseEntity.status(HttpStatus.OK).body("정상 등록 완료");
		else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록중 오류 발생");
	}
//	@DeleteMapping("/post/bookMark/{postId}")
//	public ResponseEntity<?> DeleteBookmark(@RequestParam("postId")Long postId,@AuthenticationPrincipal CustomOAuth2User customOAuth2User){
//		bookMarkService.deleteBookmark(postId);
//	} 아직 고민좀 //그리고 bookmarkcontroller에 있는 service들 다 옮기기
	@GetMapping("/post/bookMark/{postId}")
	public ResponseEntity<Integer> findBookmarkByPost(@RequestParam("postId")Long postId){
		int bookmarkCnt = bookMarkService.findBookmarkByPost(postId);
		return ResponseEntity.status(HttpStatus.OK).body(bookmarkCnt);
	}
	@GetMapping("/post/bookmarkList")
	public ResponseEntity<List<PostEntity>> myBookMarkList(@AuthenticationPrincipal CustomOAuth2User customOAuth2User){
		UserEntity user =  userService.findUser(customOAuth2User.getUsername());
		
		return null;
	}
	
}
