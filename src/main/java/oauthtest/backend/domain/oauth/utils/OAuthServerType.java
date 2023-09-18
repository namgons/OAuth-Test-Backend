package oauthtest.backend.domain.oauth.utils;

import java.util.Locale;

public enum OAuthServerType {

    KAKAO,
    ;

    public static OAuthServerType fromName(String source) {
        return OAuthServerType.valueOf(source.toUpperCase(Locale.ENGLISH));
    }
}
