package project.demo.postreply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.demo.entity.ReplyEntity;
import project.demo.post.repository.PostRepository;

@Service
public class PostReplyService {
	@Autowired
	PostReplyRepository postReplyRepository;
	public Long savePostReply(ReplyEntity replyEntity) {
		return postReplyRepository.save(replyEntity).getReplyId();
	}
	public List<ReplyEntity> postReplyList(Long postId){
		return postReplyRepository.findByPostIdList(postId);
	}
}
