package oauthtest.backend.domain.oauth.service;

import lombok.RequiredArgsConstructor;
import oauthtest.backend.domain.oauth.composite.AuthCodeRequestUrlProviderComposite;
import oauthtest.backend.domain.oauth.composite.OAuthMemberClientComposite;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import oauthtest.backend.domain.oauth.repository.OAuthMemberRepository;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import oauthtest.backend.global.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    @Value("${jwt.secret}")
    private String secretKey;
    private final Long expiredMs = 1000 * 60 * 60L;

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OAuthMemberClientComposite oauthMemberClientComposite;
    private final OAuthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OAuthServerType oAuthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oAuthServerType);
    }

    public String login(OAuthServerType oauthServerType, String authCode) {
        OAuthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OAuthMember savedMember = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return JwtUtil.createJwt(savedMember.getId(), secretKey, expiredMs);
    }
}
