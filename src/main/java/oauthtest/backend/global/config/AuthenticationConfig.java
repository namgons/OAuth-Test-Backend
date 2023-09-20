package oauthtest.backend.global.config;

import lombok.RequiredArgsConstructor;
import oauthtest.backend.domain.oauth.repository.OAuthMemberRepository;
import oauthtest.backend.domain.oauth.service.OAuthService;
import oauthtest.backend.global.filter.CustomJwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final OAuthMemberRepository oAuthMemberRepository;
    private final OAuthService oAuthService;

    @Value("${jwt.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("/oauth/kakao/**").permitAll()
                .antMatchers(HttpMethod.GET).authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new CustomJwtFilter(oAuthMemberRepository, oAuthService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
