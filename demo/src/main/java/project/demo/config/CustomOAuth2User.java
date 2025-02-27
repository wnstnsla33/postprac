package project.demo.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import project.demo.user.UserDTO;

public class CustomOAuth2User implements OAuth2User,UserDetails{

	private final UserDTO user;
	public CustomOAuth2User(UserDTO user) {
		this.user=user;
	}
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				System.out.println("hasRole 확인 :"+user.getRole());
				return user.getRole();
			}	
		});
		return collection;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return user.getName();
	}
	 public String getUsername() {

	        return user.getUsername();
	    }
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


}
