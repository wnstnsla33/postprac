package project.demo.user;

import java.net.http.HttpRequest;
import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import project.demo.config.CustomOAuth2User;

@RestController
public class Usercontoller {

	@Autowired UserService userService;
	@GetMapping("/")
	public String login(@AuthenticationPrincipal CustomOAuth2User customOAuth2User ) {
		String username = customOAuth2User.getUsername();
		return username;
	}
	@GetMapping("/my")
	public UserEntity myApi(@AuthenticationPrincipal CustomOAuth2User customOAuth2User ) {
		return userService.findUser(customOAuth2User.getUsername()); 
	}
}
