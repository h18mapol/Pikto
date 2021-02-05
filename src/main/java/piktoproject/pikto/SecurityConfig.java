package piktoproject.pikto;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/**").authorizeRequests()
                .antMatchers("/Admin").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();
        httpSecurity.logout()
                .logoutUrl("/logout/")
                .addLogoutHandler(new SecurityContextLogoutHandler());
    }
}
