package xyz.libris.api.facebook;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacebookAuthTokenRepository extends JpaRepository<FacebookAuthToken, Long> {

    FacebookAuthToken findByFacebookId(String facebookId);
}
