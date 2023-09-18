package oauthtest.backend.domain.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import oauthtest.backend.domain.oauth.entity.OAuthId;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import oauthtest.backend.domain.oauth.utils.OAuthServerType;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Getter
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoMemberResponse {

    private Long id;
    private boolean hasSignedUp;
    private LocalDateTime connectedAt;
    private KakaoAccount kakaoAccount;

    public OAuthMember toDomain() {
        return OAuthMember.builder()
                .oauthId(new OAuthId(String.valueOf(id), OAuthServerType.KAKAO))
                .nickname(kakaoAccount.getProfile().getNickname())
                .profileImageUrl(kakaoAccount.getProfile().getProfileImageUrl())
                .build();
    }
}
