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

public class JWTFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;

	public JWTFilter(JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = null;
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("Authorization")) {

				authorization = cookie.getValue();
			}
		}

		String token = authorization;
		String requestURI = request.getRequestURI();

		// 허용할 경로: /post, /post/{id}
		boolean isAllowedPath = requestURI.equals("/post") || requestURI.matches("/post/\\d+");

		if (isAllowedPath || requestURI.startsWith("/my") && token == null) {
			filterChain.doFilter(request, response);
			return;
		}
		if(token==null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다");
		}
		
		if (jwtUtil.isExpired(token)) {
			filterChain.doFilter(request, response);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "토큰이 만료되었습니다.");
			return;
		}
		// TODO Auto-generated method stub
		String username = jwtUtil.getUsername(token);
		String role = jwtUtil.getRole(token);

		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		userDTO.setRole(role);

		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);
		Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null,
				customOAuth2User.getAuthorities());
		// 세션에 사용자 등록
		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);

	}

}
