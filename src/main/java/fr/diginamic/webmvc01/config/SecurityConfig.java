package fr.diginamic.webmvc01.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import fr.diginamic.webmvc01.providers.AppAuthProvider;
import fr.diginamic.webmvc01.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	public static final String[] ENDPOINTS_WHITELIST = {
			"/css/**",
			"/",
			"/login"
	};
	public static final String LOGIN_URL = "/login";
	public static final String LOGOUT_URL = "/logout";
	public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
	public static final String DEFAULT_SUCCESS_URL = "/";
	public static final String USERNAME = "username";
 	public static final String PASSWORD = "password";
 	
 	@Autowired
 	UserService userDetailsService;
 	
 	@Bean
 	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 		// Gestion des accès
 		http.authorizeHttpRequests(request ->
 									request.antMatchers(ENDPOINTS_WHITELIST)  										   .permitAll()
 										   .anyRequest().authenticated())
 			.csrf().disable()
 			.authenticationProvider(getProvider())
 			.formLogin(form ->
 						form.loginProcessingUrl(LOGIN_URL)
 							.failureUrl(LOGIN_FAIL_URL) 
 							.usernameParameter(USERNAME)
 							.passwordParameter(PASSWORD)
 							.defaultSuccessUrl(DEFAULT_SUCCESS_URL)
 					   );
 		return http.build();
 	}
 	
 	@Bean
	public AuthenticationProvider getProvider() {
		AppAuthProvider provider = new AppAuthProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
 	

}
