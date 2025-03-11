package project.demo.postreply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.demo.entity.ReplyEntity;
import project.demo.post.repository.PostRepository;
import project.demo.postreply.DTO.ReplyListDTO;

@Service
@Transactional
public class PostReplyService {
	@Autowired
	PostReplyRepository postReplyRepository;
	public Long savePostReply(ReplyEntity replyEntity) {
		return postReplyRepository.save(replyEntity).getReplyId();
	}
	public List<ReplyEntity> postReplyList(Long postId){
		return postReplyRepository.findByPostId(postId);
	}
	public void DeleteReply(Long id) {
		postReplyRepository.deleteById(id);
	}
	public Long updatePostReply(Long id,String content) {
		ReplyEntity replyEntity = postReplyRepository.findById(id).get();
		replyEntity.setContent(content);
		return postReplyRepository.save(replyEntity).getReplyId();
	}
}
