package oauthtest.backend.domain.oauth.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
class KakaoAccount {
    private boolean profileNeedsAgreement;
    private boolean profileNicknameNeedsAgreement;
    private boolean profileImageNeedsAgreement;
    private Profile profile;
    private boolean nameNeedsAgreement;
    private String name;
    private boolean emailNeedsAgreement;
    private boolean isEmailValid;
    private boolean isEmailVerified;
    private String email;
    private boolean ageRangeNeedsAgreement;
    private String ageRange;
    private boolean birthyearNeedsAgreement;
    private String birthyear;
    private boolean birthdayNeedsAgreement;
    private String birthday;
    private String birthdayType;
    private boolean genderNeedsAgreement;
    private String gender;
    private boolean phoneNumberNeedsAgreement;
    private String phoneNumber;
    private boolean ciNeedsAgreement;
    private String ci;
    private LocalDateTime ciAuthenticatedAt;
}

@Getter
class Profile {
    private String nickname;
    private String thumbnailImageUrl;
    private String profileImageUrl;
    private boolean isDefaultImage;
}