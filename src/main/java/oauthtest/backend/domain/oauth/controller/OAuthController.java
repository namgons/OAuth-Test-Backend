package oauthtest.backend.domain.oauth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import oauthtest.backend.domain.oauth.service.OAuthService;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
@Slf4j
public class OAuthController {

    private final OAuthService oauthService;

    @GetMapping("/{oauthServerType}")
    public ResponseEntity<?> redirectAuthCodeRequestUrl(@PathVariable OAuthServerType oauthServerType) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).headers(headers).build();
    }

    @GetMapping("/{oauthServerType}/login")
    ResponseEntity<String> login(
            @PathVariable OAuthServerType oauthServerType,
            @RequestParam("code") String code) {
        String jwtToken = oauthService.login(oauthServerType, code);

        log.info("토큰 : {}", jwtToken);
        
        return ResponseEntity.ok(jwtToken);
    }
}
