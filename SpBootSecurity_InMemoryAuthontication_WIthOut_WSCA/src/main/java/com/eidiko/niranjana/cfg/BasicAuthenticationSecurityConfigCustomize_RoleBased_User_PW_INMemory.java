package com.eidiko.niranjana.cfg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfigCustomize_RoleBased_User_PW_INMemory
{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
			return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService userDetailService() 
	{	
		//UserDetails normalUser = User.withUsername("sisu").password(passwordEncoder().encode("sisu")).roles("NORMAL USER").build();
		//UserDetails adminUser = User.withUsername("susanta").password(passwordEncoder().encode("susanta")).roles("ADMIN").build();
		UserDetails manager = User.withUsername("niranjana").password(passwordEncoder().encode("niranjana")).roles("MANAGER").build();
		UserDetails developer = User.withUsername("susantaa").password(passwordEncoder().encode("susantaa")).roles("DEV").build();
		InMemoryUserDetailsManager inMemoryuserdetailsManager = new InMemoryUserDetailsManager(manager,developer);
		return inMemoryuserdetailsManager;		
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity
		.csrf().disable()
		//.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
		//.and()
		.authorizeHttpRequests()
		//.requestMatchers("/buy","/visit").hasRole("NORMAL USER")
		//.requestMatchers("/billDetails").hasRole("ADMIN")
		.requestMatchers("/employee/fetch","/employee/insert","employee/fetchEmployeeData/{id}").hasRole("DEV")
		.requestMatchers("/buy","/visit","/billDetails","/allDetails").hasRole("MANAGER")               
		
		.anyRequest()
		.authenticated()
	     .and().httpBasic();
	   //  .formLogin(); 
		return httpSecurity.build();
	}
}
