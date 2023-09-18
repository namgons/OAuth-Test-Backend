package oauthtest.backend.domain.oauth.composite;

import oauthtest.backend.domain.oauth.utils.OAuthMemberClient;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OAuthMemberClientComposite {

    private final Map<OAuthServerType, OAuthMemberClient> mapping;

    public OAuthMemberClientComposite(Set<OAuthMemberClient> clients) {
        mapping = clients.stream()
                .collect(Collectors.toMap(
                        OAuthMemberClient::supportServer,
                        Function.identity()
                ));
    }

    public OAuthMember fetch(OAuthServerType oauthServerType, String authCode) {
        return getClient(oauthServerType).fetch(authCode);
    }

    private OAuthMemberClient getClient(OAuthServerType oauthServerType) {
        return Optional.ofNullable(mapping.get(oauthServerType))
                .orElseThrow(() -> new RuntimeException("지원하지 않는 소셜 로그인 타입입니다."));
    }
}
