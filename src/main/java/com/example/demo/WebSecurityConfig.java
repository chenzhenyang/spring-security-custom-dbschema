package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	private static final String DEF_USERS_BY_USERNAME_QUERY = "select username,password,enabled from sys_user where username = ?";
	private static final String DEF_AUTHORITIES_BY_USERNAME_QUERY = "select g.role_id, gat.authority_name "
			+ "from sys_role g, sys_user_role gm, sys_role_authority ga ,sys_authority gat,sys_user u" + " where "
			+ "u.username =?" + " and " + "gm.user_id = u.user_id" + " and " + "g.role_id = ga.role_id" + " and "
			+ "g.role_id = gm.role_id" + " and " + "ga.authority_id = gat.authority_id";
	private static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY = "select g.role_id, g.role_name, gat.authority_name "
			+ "from sys_role g, sys_user_role gm, sys_role_authority ga ,sys_authority gat,sys_user u" + " where "
			+ "u.username =?" + " and " + "gm.user_id = u.user_id" + " and " + "g.role_id = ga.role_id" + " and "
			+ "g.role_id = gm.role_id" + " and " + "ga.authority_id = gat.authority_id";

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
				.authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
				.groupAuthoritiesByUsername(DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY).rolePrefix("");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/ignore/**");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		 httpSecurity.authorizeRequests().antMatchers("/auth/**").hasAuthority("authority_hello");
		httpSecurity.httpBasic();
	}

	// @Bean
	// public BCryptPasswordEncoder passwordEncoder() {
	// return new BCryptPasswordEncoder();
	// }

}
