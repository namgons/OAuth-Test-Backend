package oauthtest.backend.domain.oauth.dto;

import lombok.Getter;

@Getter
public class KakaoToken {

    private String tokenType;
    private String accessToken;
    private String idToken;
    private Integer expiresIn;
    private String refreshToken;
    private Integer refreshTokenExpiresIn;
    private String scope;
}
