package project.demo.post.DTO;

import lombok.Getter;
import lombok.Setter;
import project.demo.entity.PostEntity;
@Getter
@Setter

public class PostListDTO {
	private Long postId;
    private String title;
    private String content;
    private int viewCount;
    private String createdDate;
    private String lastModifiedDate;
    private String userName; 
    
    public PostListDTO(PostEntity post) {
    	this.postId = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.createdDate = post.getCreatedDate().toString();
        this.lastModifiedDate = post.getLastModifiedDate().toString();
        this.userName = post.getUserName();
        System.out.println(userName);
    }
}
