package project.demo.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import project.demo.entity.PostEntity;
import project.demo.post.repository.PostRepository;
@Transactional
@Service
public class PostService {
	private final PostRepository postRepository;
	
	@Autowired
	public PostService(@Qualifier("jpaPostRepositoryImpl")PostRepository postRepository) {
		this.postRepository=postRepository;
	}
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
	public void hitUp(Long id) {
		postRepository.incrementHit(id);
	}
	public void deletePost(Long id) {
		postRepository.delete(id);
	}
	public Optional<PostEntity> updatePost(Long id,String title,String content){
		int result = postRepository.updatePost(id,title,content);
		if(result==0) throw new EntityNotFoundException("해당 게시글을 찾을 수 없습니다.");
		return postRepository.findById(id);
	}
}
