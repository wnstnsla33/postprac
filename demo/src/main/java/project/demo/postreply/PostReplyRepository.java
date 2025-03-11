package project.demo.postreply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.demo.entity.ReplyEntity;

public interface PostReplyRepository extends JpaRepository<ReplyEntity, Long>{
	@Query("SELECT r FROM ReplyEntity r JOIN FETCH r.post WHERE r.post.id = :postId")
	List<ReplyEntity> findByPostId(@Param("postId")Long postId);
	@Modifying
	@Query("DELETE FROM ReplyEntity r WHERE r.replyId = :id")
	void deleteReplyById(@Param("id") Long id);

}
