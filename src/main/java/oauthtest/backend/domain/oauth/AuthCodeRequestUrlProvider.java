package oauthtest.backend.domain.oauth;

import oauthtest.backend.domain.oauth.utils.OAuthServerType;

public interface AuthCodeRequestUrlProvider {

    OAuthServerType supportServer();

    String provide();
}
