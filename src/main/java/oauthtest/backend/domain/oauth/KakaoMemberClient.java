package oauthtest.backend.domain.oauth;

import lombok.RequiredArgsConstructor;
import oauthtest.backend.domain.oauth.dto.KakaoMemberResponse;
import oauthtest.backend.domain.oauth.dto.KakaoToken;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import oauthtest.backend.global.config.KakaoOAuthConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OAuthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOAuthConfig kakaoOAuthConfig;

    @Override
    public OAuthServerType supportServer() {
        return OAuthServerType.KAKAO;
    }

    @Override
    public OAuthMember fetch(String authCode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authCode));
        KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember(tokenInfo.getAccessToken());
        return kakaoMemberResponse.toDomain();
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOAuthConfig.getClientId());
        params.add("redirect_uri", kakaoOAuthConfig.getRedirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOAuthConfig.getClientSecret());
        return params;
    }

}
