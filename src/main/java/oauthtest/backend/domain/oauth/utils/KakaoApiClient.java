package oauthtest.backend.domain.oauth.utils;

import oauthtest.backend.domain.oauth.dto.KakaoMemberResponse;
import oauthtest.backend.domain.oauth.dto.KakaoToken;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class KakaoApiClient {

    private final String KAKAO_BASE_URL = "https://kauth.kakao.com/oauth/token";
    private final String KAKAO_USER_URL = "https://kapi.kakao.com/v2/user/me";

    public KakaoToken fetchToken(MultiValueMap<String, String> params) {
        WebClient kakaoOAuthWebClient = getKakaoClient(KAKAO_BASE_URL);
        String uri = parseMapToUri(params);

        KakaoToken kakaoToken = kakaoOAuthWebClient.post()
                .uri(uri)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoToken.class)
                .block();

        if (kakaoToken == null) {
            throw new RuntimeException("카카오 access token을 받아오지 못함.");
        }

        return kakaoToken;
    }

    private String parseMapToUri(MultiValueMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for (Map.Entry<String, List<String>> entry : params.entrySet()) {
            String key = entry.getKey();
            for (String value : entry.getValue()) {
                sb.append(key)
                        .append("=")
                        .append(value)
                        .append("&");
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public KakaoMemberResponse fetchMember(String bearerToken) {
        WebClient kakaoUserDetailClient = getKakaoClient(KAKAO_USER_URL);

        KakaoMemberResponse kakaoMemberResponse = kakaoUserDetailClient.get()
                .header("Authorization", "Bearer " + bearerToken)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoMemberResponse.class)
                .block();

        if (kakaoMemberResponse == null) {
            throw new RuntimeException("카카오 사용자 정보를 받아오지 못함");
        }

        return kakaoMemberResponse;
    }

    private WebClient getKakaoClient(String kakaoUrl) {
        return WebClient.builder()
                .baseUrl(kakaoUrl)
                .build();
    }
}
