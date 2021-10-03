package org.sid.achatproject.security;

import org.sid.achatproject.services.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity//nouvelle configuration de spring sécurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    //tous les info sur utilisateur logins
    private final UserService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;



    public WebSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super();
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                // frontend .cors
                .cors().and()
                //si on a des formulaires
                .csrf().disable()
                //autoriser excution des requetes http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,SecurityConstante.SIGN_UP_URL)
                //tous les privilége
                .permitAll()
                //ayi haja akhra man ghir post darorii dir authenticated
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                //pour autorisationFilter get
                .addFilter(new AutorisationFilter(authenticationManager()))
                //pour gerer uer authentifier hadi dima min kakono khadamin b les token
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }

    //peut fournir une instance de utilisateur authentifier jib les info dial user li dar login
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

    }


}
