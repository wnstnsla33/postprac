package project.demo.entity;


import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import project.demo.entity.PostEntity;
import project.demo.entity.UserEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookmark")
public class BookMarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "bookmark_id")
    private Long bookmarkId;

    @Column(name = "create_bookmark", columnDefinition = "DATE DEFAULT sysdate")
    private LocalDate createBookmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;
}