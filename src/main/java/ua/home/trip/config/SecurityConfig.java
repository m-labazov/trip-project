package ua.home.trip.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import ua.home.trip.service.SocialUserService;
import ua.home.trip.service.UserService;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
              //Configures form login
                .formLogin()
                    .loginPage("/login.htm")
                    .loginProcessingUrl("/login/authenticate")
                    .failureUrl("/login?error=bad_credentials")
                //Configures the logout function
                .and()
                    .logout()
                        .deleteCookies("JSESSIONID")
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                //Configures url based authorization
                .and()
                    .authorizeRequests()
                        //Anyone can access the urls
                        .antMatchers(
                                "/auth/**",
                                "/login.htm",
                                "/signup**",
                                "/user/register/**"
                        ).permitAll()
                        //The rest of the our application is protected.
                .antMatchers("/**").hasRole("USER")
                //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                .and()
                    .apply(new SpringSocialConfigurer())
                .and()
                    .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // auth
        // .inMemoryAuthentication()
        // .withUser("user").password("password").roles("USER");
    }

    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SocialUserService(userDetailsService());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return new UserService();
    }

}