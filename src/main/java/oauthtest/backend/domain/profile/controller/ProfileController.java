package oauthtest.backend.domain.profile.controller;

import lombok.extern.slf4j.Slf4j;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProfileController {

    @GetMapping("/profile")
    public ResponseEntity<?> selectProfile(@AuthenticationPrincipal OAuthMember member) {
        log.info("member : {}", member);
        return ResponseEntity.ok().body(member);
    }

}

