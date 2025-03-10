package project.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import project.demo.entity.ItemEntity;
import project.demo.entity.UserEntity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item_reply")
public class ItemReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "item_reply_id")
    private Long itemReplyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    @Column(name = "reply_content")
    private String replyContent;

    @Column(name = "modified_date")
    private LocalDate modifiedDate;
}