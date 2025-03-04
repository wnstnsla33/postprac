package project.demo.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Transactional
@Service
public class postService {
	@Autowired
	PostRepository postRepository;
	public Optional<PostEntity>findPost(Long id) {
		return postRepository.findById(id);
	}
	public Long savePost(PostEntity postEntity) {
		postRepository.save(postEntity);
		return postEntity.getPostId();
	}
	public List<PostEntity> postList(){
		return 	postRepository.findAll();
	}
}
