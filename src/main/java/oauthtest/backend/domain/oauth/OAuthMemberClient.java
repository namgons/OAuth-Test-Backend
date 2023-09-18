package oauthtest.backend.domain.oauth;

import oauthtest.backend.domain.oauth.entity.OAuthMember;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;

public interface OAuthMemberClient {

    OAuthServerType supportServer();

    OAuthMember fetch(String code);
}
