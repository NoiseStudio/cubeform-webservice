package pl.platrykp.cubeformservice.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.platrykp.cubeformservice.services.AuthUserDetailsService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfiguration.class);
    public static final String LOGIN_PAGE_PATH = "/auth/login";
    public static final String REGISTER_PAGE_PATH = "/auth/users";
    public static final String LOGIN_FAILED_PAGE_PATH = "/auth/loginFailed";
    public static final String LOGIN_SUCCESS_PAGE_PATH = "/auth/loginSuccess";
    public static final String LOGOUT_PAGE_PATH = "/auth/logout";
    public static final String LOGOUT_DONE_PAGE_PATH = "/auth/logoutDone";
    public static final String[] CSRF_IGNORE = {
            REGISTER_PAGE_PATH, LOGIN_PAGE_PATH, LOGIN_FAILED_PAGE_PATH, LOGOUT_DONE_PAGE_PATH, LOGOUT_PAGE_PATH
    };
    public static final String[] NO_AUTH_PERMIT = {
            "/public/**", REGISTER_PAGE_PATH, LOGIN_PAGE_PATH, LOGIN_FAILED_PAGE_PATH, LOGOUT_DONE_PAGE_PATH
    };

    public static class NoPasswordEncoder implements PasswordEncoder {

        @Override
        public String encode(CharSequence rawPassword) {
            logger.info("encode '{}' to '{}'", rawPassword, rawPassword.toString());
            return rawPassword.toString();
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            logger.info("matches '{}' '{}' ? {}", rawPassword, encodedPassword, rawPassword.toString().equals(encodedPassword));
            return rawPassword.toString().equals(encodedPassword);
        }
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new AuthUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new NoPasswordEncoder();//new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new AuthAccessDeniedEntryPoint();
    }


//    @Bean
//    public JSonAuthenticationFilter jsonUsernamePasswordAuthFilter() throws Exception {
//        JSonAuthenticationFilter authFilter = new JSonAuthenticationFilter();
//        authFilter.setAuthenticationManager(authenticationManagerBean());
//        return authFilter;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .ignoringAntMatchers(CSRF_IGNORE)
                .and()
                    .authorizeRequests()
                    .antMatchers(NO_AUTH_PERMIT).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginProcessingUrl(LOGIN_PAGE_PATH)
                    .defaultSuccessUrl(LOGIN_SUCCESS_PAGE_PATH)
                    .failureUrl(LOGIN_FAILED_PAGE_PATH)
                    .successForwardUrl(LOGIN_SUCCESS_PAGE_PATH)
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl(LOGOUT_PAGE_PATH)
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .logoutSuccessUrl(LOGOUT_DONE_PAGE_PATH)
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
//                .and()
                ;

    }
}
