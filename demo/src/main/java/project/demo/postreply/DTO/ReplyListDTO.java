package project.demo.postreply.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.demo.entity.ReplyEntity;
@Getter
@Setter
@NoArgsConstructor
public class ReplyListDTO {
	private Long replyId;
	private String content;
	private Long postId;
	private String userName;
	private int likeCount;
	public ReplyListDTO(ReplyEntity replyEntity) {
		super();
		this.replyId = replyEntity.getReplyId();
		this.content = replyEntity.getContent();
		this.postId = replyEntity.getPost().getPostId();
		this.userName = replyEntity.getUser().getName();
		this.likeCount=0;
	}
	
	
}
