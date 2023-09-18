package oauthtest.backend.domain.oauth.repository;

import oauthtest.backend.domain.oauth.entity.OAuthId;
import oauthtest.backend.domain.oauth.entity.OAuthMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthMemberRepository extends JpaRepository<OAuthMember, Long> {

    Optional<OAuthMember> findByOauthId(OAuthId oauthId);
}