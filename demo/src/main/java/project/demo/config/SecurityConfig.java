package project.demo.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer{
	private final CustomOAuth2UserService customOAuth2UserService;
	private CustomSuccessHandler customSuccessHandler;
	private final JWTUtil jwtUtil;
	public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,CustomSuccessHandler customSuccessHandler,JWTUtil jwtUtil) {
		this.customOAuth2UserService=customOAuth2UserService;
		this.customSuccessHandler=customSuccessHandler;
		this.jwtUtil=jwtUtil;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
	            config.setAllowedOrigins(List.of("http://localhost:3000")); // 프론트 주소 허용
	            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
	            config.setAllowCredentials(true); // ✅ 중요: 쿠키 허용
	            config.setAllowedHeaders(List.of("*"));
	            return config;
//				// TODO Auto-generated method stub
//				CorsConfiguration configuration = new CorsConfiguration();
//
//                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//                configuration.setAllowedMethods(Collections.singletonList("*"));
//                configuration.setAllowCredentials(true);
//                configuration.setAllowedHeaders(Collections.singletonList("*"));
//                configuration.setMaxAge(3600L);
//
//                configuration.setExposedHeaders(Arrays.asList("Set-Cookie", "Authorization"));
//
//                return configuration;
			}
		}));
		http
			.csrf((auth)->auth.disable());
		http
			.formLogin((auth)->auth.disable());
		http
			.httpBasic((auth)->auth.disable());
		
		http
        .oauth2Login((oauth2) -> oauth2
                .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                        .userService(customOAuth2UserService)).successHandler(customSuccessHandler));
		http
			.authorizeHttpRequests((auth)->auth.requestMatchers("/","post","my","post/{id}").permitAll().requestMatchers("/post/**").hasRole("USER").anyRequest().authenticated());
		http.addFilterBefore(new JWTFilter(jwtUtil),UsernamePasswordAuthenticationFilter.class);
		http
			.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
		
	}
}
