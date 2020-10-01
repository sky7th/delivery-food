package com.sky7th.deliveryfood.security.refreshtoken;

import com.sky7th.deliveryfood.security.exception.TokenRefreshException;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("refresh_token")
public class RefreshToken implements Serializable {

  @Id
  private String id;
  private Instant expiryDate;

  public RefreshToken(String id, Instant expiryDate) {
    this.id = id;
    this.expiryDate = expiryDate;
  }

  public void verifyExpiration() {
    if (this.expiryDate.compareTo(Instant.now()) < 0) {
      throw new TokenRefreshException("토큰이 만료되었습니다. 다시 로그인 해주세요.");
    }
  }
}