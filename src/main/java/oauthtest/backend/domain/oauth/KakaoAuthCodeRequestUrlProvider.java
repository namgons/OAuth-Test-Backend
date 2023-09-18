package oauthtest.backend.domain.oauth;

import lombok.RequiredArgsConstructor;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import oauthtest.backend.global.config.KakaoOAuthConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

    private final KakaoOAuthConfig kakaoOAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.KAKAO;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com/oauth/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", kakaoOAuthConfig.getClientId())
                .queryParam("redirect_uri", kakaoOAuthConfig.getRedirectUri())
                .queryParam("scope", String.join(",", kakaoOAuthConfig.getScope()))
                .toUriString();
    }
}