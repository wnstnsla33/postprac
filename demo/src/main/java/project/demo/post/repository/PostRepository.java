package project.demo.post.repository;

import java.util.List;
import java.util.Optional;

import project.demo.entity.PostEntity;

public interface PostRepository {
	void incrementHit(Long postId);
	int updatePost(Long postId,String content,String title);
	Optional<PostEntity> findById(Long id);
	void save(PostEntity postEntity);
	List<PostEntity> findAll();
	void delete(Long id);
}
