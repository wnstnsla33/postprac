package project.demo.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.demo.config.CustomOAuth2User;
import project.demo.entity.PostEntity;
import project.demo.entity.UserEntity;
import project.demo.post.DTO.PostDTO;
import project.demo.post.DTO.PostListDTO;
import project.demo.user.UserService;


@RestController
public class PostCotroller {
	
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;
	@GetMapping("/post")
	public ResponseEntity<List<PostListDTO>> postList(){
		System.out.println("**************************** 경로 /post");
		List<PostEntity> list = postService.postList();
		List<PostListDTO> listDTO = new ArrayList<PostListDTO>();
		for(PostEntity post : list) {
			listDTO.add(new PostListDTO(post));
		}
		return ResponseEntity.status(HttpStatus.OK).body(listDTO);
		}
	@PostMapping("/post/new")
    public ResponseEntity<?> writePost(@AuthenticationPrincipal CustomOAuth2User customOAuth2User ,@RequestBody PostDTO postDTO) {
		System.out.println("**************************** 경로 /posyNew");
		UserEntity user =  userService.findUser(customOAuth2User.getUsername());
		PostEntity postEntity= postDTO.toEntity(user);
        try {
        	postService.savePost(postEntity);
            return ResponseEntity.ok("성공");
        } catch (Exception e) {
            // 오류 발생 시 적절한 메시지와 상태 코드를 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패: " + e.getMessage());
        }
	}
	@GetMapping("/post/{id}")
	public ResponseEntity<?> postDetail(@PathVariable("id") Long id){
		System.out.println("**************************** 경로 /post/{id}");
		postService.hitUp(id);
		PostListDTO post = new PostListDTO(postService.findPost(id).get());
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
	@DeleteMapping("/post/delete/{id}")
	public ResponseEntity<?> deletePost(@PathVariable("id")Long id){
		System.out.println("**************************** 경로 /post/delete/{id}");
		postService.deletePost(id);
		return ResponseEntity.status(HttpStatus.OK).body("정상 삭제완료");
	}
	@PutMapping("/post/update/{id}")
	public ResponseEntity<?> updatePost(@PathVariable("id")Long id ,@RequestBody PostDTO postDTO){
		PostListDTO post = new PostListDTO( postService.updatePost(id, postDTO.getTitle(),postDTO.getContent()).get());
		return ResponseEntity.status(HttpStatus.OK).body(post);
	}
}
