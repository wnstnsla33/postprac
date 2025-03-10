package project.demo.post.DTO;

import lombok.Getter;
import lombok.Setter;
import project.demo.entity.PostEntity;
import project.demo.entity.UserEntity;
@Getter
@Setter
public class PostDTO {
	private String title;
    private String content;

    public PostEntity toEntity(UserEntity user) {
        PostEntity post = new PostEntity();
        post.setTitle(this.title);
        post.setContent(this.content);
        post.setUserName(user.getName());
        post.setUser(user);
        return post;
}
}