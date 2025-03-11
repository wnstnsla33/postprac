package project.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import project.demo.entity.PostEntity;
import project.demo.entity.UserEntity;
import project.demo.entityConfig.DateEntityConfig;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "reply")
public class ReplyEntity extends DateEntityConfig{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "reply_id")
    private Long replyId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "like_count")
    private Integer likeCount;

	public ReplyEntity(String content, PostEntity post, UserEntity user) {
		super();
		this.content = content;
		this.post = post;
		this.user = user;
	}

   
}
