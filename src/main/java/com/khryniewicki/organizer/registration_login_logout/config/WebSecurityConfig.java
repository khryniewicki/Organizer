package com.khryniewicki.organizer.registration_login_logout.config;


import com.khryniewicki.organizer.registration_login_logout.services.LoggingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@ComponentScan("com.khryniewicki.organizer")
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final LoggingUserService loggingUserService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/webjars/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/register", "/", "/login**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/projects", true)
                .failureForwardUrl("/login-error")
                .successHandler(successHandler())
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutHandler())
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
                .and()
                .sessionManagement().maximumSessions(1).and().invalidSessionUrl("/login");
    }

    @Bean
    AuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler(loggingUserService);
    }

    @Bean
    LogoutHandler logoutHandler() {
        return new CustomLogoutHandler();
    }

    @Bean
    public SessionManagementFilter sessionManagementFilter() {
        SessionManagementFilter sessionManagementFilter = new SessionManagementFilter(httpSessionSecurityContextRepository());
        sessionManagementFilter.setInvalidSessionStrategy(simpleRedirectInvalidSessionStrategy());
        return sessionManagementFilter;
    }

    @Bean
    public SimpleRedirectInvalidSessionStrategy simpleRedirectInvalidSessionStrategy() {
        SimpleRedirectInvalidSessionStrategy simpleRedirectInvalidSessionStrategy = new SimpleRedirectInvalidSessionStrategy("/expired");
        return simpleRedirectInvalidSessionStrategy;
    }

    @Bean
    public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
        HttpSessionSecurityContextRepository httpSessionSecurityContextRepository = new HttpSessionSecurityContextRepository();
        return httpSessionSecurityContextRepository;
    }
}