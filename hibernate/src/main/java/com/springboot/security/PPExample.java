package com.springboot.security;

public class PPExample {

}
//package com.symbio.epb.configurations.security;
//
//import static com.symbio.epb.configurations.EPBConstans.HTTP.METHOD.METHOD_POST;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.REALM_NAME;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.LOGIN.PASSWORD;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.LOGIN.USERNAME;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.URL.AJAX_SECURITY_CHECK;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.URL.LOGIN;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.URL.LOGOUT;
//import static com.symbio.epb.configurations.EPBConstans.SECURITY.URL.SECURITY_CHECK;
//import static com.symbio.epb.configurations.GlobalHolder.getHomePage;
//import static com.symbio.epb.configurations.GlobalHolder.getLoginPage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDecisionVoter;
//import org.springframework.security.access.vote.AffirmativeBased;
//import org.springframework.security.access.vote.AuthenticatedVoter;
//import org.springframework.security.access.vote.RoleVoter;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.authentication.event.LoggerListener;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
//import org.springframework.security.web.access.expression.WebExpressionVoter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//@EnableAutoConfiguration
//public class EpbWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//	private Logger logger = Logger.getLogger(EpbWebSecurityConfigurerAdapter.class);
//	public static final String ADMIN = "/Admin/";
//	private static String[][] protectedUrls = {{ADMIN+"**/*","ADMIN,VP/SVP ADMIN"},{"/adaptationRate/**/*","AGENT"},{"/Coach/**/*","AGENT"},{"/Configuration/**/*","AGENT"},	{"/Performance/**/*","AGENT"},{"/Productivity/**/*","AGENT"},{"/Roster/**/*","AGENT"},{"/Routine/**/*","AGENT"},{"/Trend/**/*","AGENT"}};
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/tablet/*", "/tablet/*");
//		web.ignoring().antMatchers("/tablet/**/*", "/tablet/**/*");
//		web.ignoring().antMatchers("/keepalive.html*", "/keepalive.html*");
//		web.ignoring().antMatchers("/**/*.html*", "/**/*.html*");
//		web.ignoring().antMatchers("/Public/**/*", "/Public/**/*");
//		web.ignoring().antMatchers("/**/*.ico", "/**/*.ico");
//
//		web.ignoring().antMatchers("/**/*.css", "/**/*.css");
//		web.ignoring().antMatchers("/**/*.js", "/**/*.js");
//		web.ignoring().antMatchers("/**/*.png", "/**/*.png");
//		web.ignoring().antMatchers("/**/*.jpg", "/**/*.jpg");
//		web.ignoring().antMatchers("/**/*.gif", "/**/*.gif");
//		web.ignoring().antMatchers("/tablet/*", "/tablet/*");
//
//		web.ignoring().antMatchers("/**/*.eot", "/**/*.eot");
//		web.ignoring().antMatchers("/**/*.svg", "/**/*.svg");
//		web.ignoring().antMatchers("/**/*.ttf", "/**/*.ttf");
//		web.ignoring().antMatchers("/**/*.woff", "/**/*.woff");
//
//		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources",
//				"/configuration/security", "/swagger-ui.html", "/webjars/**");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		EPBAuthenticationEntryPoint ep = new EPBAuthenticationEntryPoint();
//		http.exceptionHandling().authenticationEntryPoint(ep);
//		http.addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//		http.csrf().disable().formLogin().loginPage(LOGIN).failureUrl(getLoginPage() + "?error=1")
//				.loginProcessingUrl(SECURITY_CHECK).usernameParameter(USERNAME).passwordParameter(PASSWORD)
//				.permitAll();
//
//		http.logout().logoutUrl(LOGOUT).logoutSuccessUrl(getLoginPage()).invalidateHttpSession(true);
//
//		http.sessionManagement().sessionFixation().changeSessionId().maximumSessions(1).expiredUrl(getHomePage());
//
//		http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");
//		
//		protectUrls(http);
//	}
//	
//	private void protectUrls(HttpSecurity http) throws Exception {
//		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configuredHttp = http.authorizeRequests().accessDecisionManager(accessDecisionManager())
//		.expressionHandler(webSecurityExpressionHandler());
//		
//		for(String[] unit : protectedUrls){
//			String url = unit[0];
//			String role = unit[1];
//			
//			if (role.indexOf(",") != -1) {
//				configuredHttp.antMatchers(url).hasAnyRole(role.split(","));
//			} else {
//				configuredHttp.antMatchers(url).hasRole(role);
//			}
//		}
//		
//		configuredHttp.and().exceptionHandling().accessDeniedPage(LOGIN);
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setPasswordEncoder(passwordEncoder());
//		authProvider.setUserDetailsService(userDetailsService());
//
//		auth.authenticationProvider(authProvider);
//
//	}
//
//	@Bean
//	public EPBAuthenticationSuccessHandler customSuccessHandler() {
//		EPBAuthenticationSuccessHandler customSuccessHandler = new EPBAuthenticationSuccessHandler();
//		return customSuccessHandler;
//	}
//
//	@Bean
//	public EPBAuthenticationFailureHandler customFailureHandler() {
//		EPBAuthenticationFailureHandler customFailureHandler = new EPBAuthenticationFailureHandler();
//		return customFailureHandler;
//	}
//
//	@Bean
//	public EPBAjaxAuthFilter ajaxAuthenticationFilter() throws Exception {
//		EPBAjaxAuthFilter ajaxLoginFilter = new EPBAjaxAuthFilter();
//		ajaxLoginFilter
//				.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(AJAX_SECURITY_CHECK, METHOD_POST));
//
//		ajaxLoginFilter.setAuthenticationManager(this.authenticationManager());
//		ajaxLoginFilter.setAuthenticationSuccessHandler(new EPBAuthenticationSuccessHandler());
//		ajaxLoginFilter.setAuthenticationFailureHandler(new EPBAuthenticationFailureHandler());
//		ajaxLoginFilter.setUsernameParameter(USERNAME);
//		ajaxLoginFilter.setPasswordParameter(PASSWORD);
//
//		return ajaxLoginFilter;
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder passwordEncoder = new DESPasswordEncoder();
//		return passwordEncoder;
//	}
//
//	@Override
//	@Bean
//	public UserDetailsService userDetailsService() {
//		UserDetailsService userDetailsService = new EpbUserDetailsService();
//		return userDetailsService;
//	}
//
//	@Bean
//	public LoggerListener loggerListener() {
//		logger.debug("org.springframework.security.authentication.event.LoggerListener");
//		LoggerListener loggerListener = new LoggerListener();
//
//		return loggerListener;
//	}
//
//	@Bean
//	public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
//		logger.debug("org.springframework.security.access.event.LoggerListener");
//		org.springframework.security.access.event.LoggerListener eventLoggerListener = new org.springframework.security.access.event.LoggerListener();
//
//		return eventLoggerListener;
//	}
//
//	@Bean(name = "accessDecisionManager")
//	public AccessDecisionManager accessDecisionManager() {
//		logger.debug("AccessDecisionManager");
//		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
//		decisionVoters.add(new RoleVoter());
//		decisionVoters.add(new AuthenticatedVoter());
//		decisionVoters.add(webExpressionVoter());
//
//		AffirmativeBased accessDecisionManager = new AffirmativeBased(decisionVoters);
//
//		return accessDecisionManager;
//	}
//
//	@Bean(name = "expressionHandler")
//	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
//		logger.debug("DefaultWebSecurityExpressionHandler");
//		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
//		return webSecurityExpressionHandler;
//	}
//
//	@Bean(name = "expressionVoter")
//	public WebExpressionVoter webExpressionVoter() {
//		logger.debug("WebExpressionVoter");
//		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
//		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
//		return webExpressionVoter;
//	}
//
//	@Configuration
//	@Order(1)
//	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//	    @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http.csrf().disable();
//	        // the ant matcher is what limits the scope of this configuration.
//	        http.antMatcher("/Cron/**").authorizeRequests()
//	            .antMatchers("/Cron/**").hasAnyRole("SUPERADMIN")
//	            .and().httpBasic().realmName(REALM_NAME);
//
//			http.antMatcher("/health").authorizeRequests()
//					.antMatchers("/health").hasAnyRole("SUPERADMIN")
//					.and().httpBasic().realmName(REALM_NAME);
//	    }
//	    
//	    @Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			auth.inMemoryAuthentication().withUser("epb_cron_user").password("lpsqW4MBcRoH3IzdQEO7VltVQRX/vpLj").roles("SUPERADMIN");
//			auth.inMemoryAuthentication().withUser("epb_health_user").password("lx18980822520").roles("SUPERADMIN");
//		}
//	}
//}
