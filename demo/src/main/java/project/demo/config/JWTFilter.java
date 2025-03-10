package project.demo.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.demo.user.UserDTO;

public class JWTFilter extends OncePerRequestFilter{

	private final JWTUtil jwtUtil;
	public JWTFilter(JWTUtil jwtUtil) {
		this.jwtUtil=jwtUtil;
	}
	@Override 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = null;
		Cookie[] cookies = request.getCookies();
		
		
		
		for(Cookie cookie: cookies) {
			 if (cookie.getName().equals("Authorization")) {

	                authorization = cookie.getValue();
			}
		}
		
		String token = authorization;
		 String requestURI = request.getRequestURI();
		    if ((requestURI.startsWith("/post")||requestURI.startsWith("/my"))&&token==null) {
		        filterChain.doFilter(request, response);
		        return;
		    }
		
		if(jwtUtil.isExpired(token)) {
			System.out.println("token expired");
			filterChain.doFilter(request, response);
			return;
		}
		// TODO Auto-generated method stub
		String username=jwtUtil.getUsername(token);
		String role =jwtUtil.getRole(token);
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setRole(role);
		
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);
		Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User,null, customOAuth2User.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
		
	}

}
