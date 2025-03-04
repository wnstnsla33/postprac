package project.demo.user;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import project.demo.post.PostEntity;

@Entity
@Getter
@Setter
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="user_id")
	private Long userId;
	private String username;
	private String name;
	private String email;
	private String role;
	private String birthYear;
	private String regDate;
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<PostEntity> posts= new ArrayList<PostEntity>();
}
