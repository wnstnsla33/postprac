package project.demo.postreply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project.demo.entity.ReplyEntity;

public interface PostReplyRepository extends JpaRepository<ReplyEntity, Long>{
	List<ReplyEntity> findByPostIdList(Long postid);
}
