package project.demo.post.DTO;

import lombok.Getter;
import lombok.Setter;
import project.demo.post.PostEntity;
import project.demo.user.UserEntity;
@Getter
@Setter
public class PostDTO {
	private String title;
    private String content;

    public PostEntity toEntity(UserEntity user) {
        PostEntity post = new PostEntity();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setUser(user);
        return post;
}
}