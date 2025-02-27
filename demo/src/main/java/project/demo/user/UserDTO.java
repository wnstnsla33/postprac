package project.demo.user;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String username;
	private String name;
	private String role;
	
}
