package oauthtest.backend.domain.oauth.utils;


import oauthtest.backend.domain.oauth.utils.OAuthServerType;
import org.springframework.core.convert.converter.Converter;

public class OAuthProviderConverter implements Converter<String, OAuthServerType> {

    @Override
    public OAuthServerType convert(String source) {
        return OAuthServerType.fromName(source);
    }
}
