package project.demo.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.demo.entity.BookMarkEntity;

import project.demo.entity.ReplyEntity;
import project.demo.entity.UserEntity;
import project.demo.entityConfig.DateEntityConfig;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class PostEntity extends DateEntityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")
    private Long postId;

    private String title;
    private String content;

    @Column(name = "view_count", nullable = false, columnDefinition = "int default 0")
    private int viewCount;
    private String userName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference  // 순환 참조를 방지하기 위해 사용
    private UserEntity user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookMarkEntity> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyEntity> replies = new ArrayList<>();

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
