package piktoproject.pikto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.*;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/Index/**").permitAll()
                .antMatchers("/Admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/css/**","/img/**","/font/**","/js/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().permitAll() // enable form based login
                .loginPage("/login")
                .successForwardUrl("/formLogin")
                .and().logout() // enable logout
                .and().oauth2Login() // enable OAuth2
                .loginPage("/login").defaultSuccessUrl("/oauth2LoginSuccess", true)
                .and()
                .csrf().disable();
    }


    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                .usersByUsernameQuery("select email, password, seller from piktodb.user where email=?")
                .authoritiesByUsernameQuery("SELECT email, IF(admin>0, \"ROLE_ADMIN\", \"ROLE_USER\") from piktodb.user where email=?")
        ;
    }
}
