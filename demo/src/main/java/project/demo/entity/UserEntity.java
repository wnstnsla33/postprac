package project.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import  project.demo.entity.BookMarkEntity;
import project.demo.entity.ItemLikeEntity;
import project.demo.entity.ItemReplyEntity;
import project.demo.entity.PostEntity;
import project.demo.entity.ReplyEntity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;

    private String username;
    private String name;
    private String email;
    private String role;
    private String birthYear;
    private String regDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference  // 순환 참조를 방지하기 위해 사용
    private List<PostEntity> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<BookMarkEntity> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ReplyEntity> replies = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ItemLikeEntity> itemLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ItemReplyEntity> itemReplies = new ArrayList<>();
}
