package oauthtest.backend.domain.oauth.service;

import lombok.RequiredArgsConstructor;
import oauthtest.backend.domain.oauth.AuthCodeRequestUrlProviderComposite;
import oauthtest.backend.domain.oauth.OAuthMemberClientComposite;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import oauthtest.backend.domain.oauth.repository.OAuthMemberRepository;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OAuthMemberClientComposite oauthMemberClientComposite;
    private final OAuthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OAuthServerType oAuthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oAuthServerType);
    }

    public Long login(OAuthServerType oauthServerType, String authCode) {
        OAuthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode);
        OAuthMember savedMember = oauthMemberRepository.findByOauthId(oauthMember.getOauthId())
                .orElseGet(() -> oauthMemberRepository.save(oauthMember));
        return savedMember.getId();
    }
}
