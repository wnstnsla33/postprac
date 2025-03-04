package project.demo.post;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.demo.entityConfig.DateEntityConfig;
import project.demo.user.UserEntity;
@Entity
@Getter
@Table(name = "Post_Entity")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity extends DateEntityConfig{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "post_id")
	private Long postId;
	private String title;
	private String content;
	@Column(name = "view_count", nullable = false, columnDefinition = "int default 0")
	private int viewCount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonManagedReference
	private UserEntity user;

	public void setUser(UserEntity user) {
		this.user = user;
	}
	
}
