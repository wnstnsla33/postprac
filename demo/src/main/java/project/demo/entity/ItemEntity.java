package project.demo.entity;


import jakarta.persistence.*;
import lombok.*;
import  project.demo.entity.ItemLikeEntity;
import project.demo.entity.ItemReplyEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "img")
    private String img;

    @Column(name = "가격")
    private Integer price;

    @Column(name = "이름")
    private String name;

    @Column(name = "소개")
    private String description;

    @Column(name = "카테고리")
    private String category;

    @Column(name = "like_count")
    private Integer likeCount;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemLikeEntity> itemLikes = new ArrayList<>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemReplyEntity> itemReplies = new ArrayList<>();
}