package oauthtest.backend.domain.oauth.utils;

import oauthtest.backend.domain.oauth.utils.OAuthServerType;

public interface AuthCodeRequestUrlProvider {

    OAuthServerType supportServer();

    String provide();
}
