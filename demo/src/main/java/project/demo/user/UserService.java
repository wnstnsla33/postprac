package project.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import project.demo.entity.UserEntity;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public UserEntity findUser(String username) {
		return userRepository.findByUsername(username);
	}
}
