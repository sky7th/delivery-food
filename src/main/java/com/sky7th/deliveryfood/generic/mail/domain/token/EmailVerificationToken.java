package com.sky7th.deliveryfood.generic.mail.domain.token;

import com.sky7th.deliveryfood.security.exception.TokenRefreshException;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash("email_verification_token")
public class EmailVerificationToken implements Serializable {

  @Id
  private String id;
  private Long userId;
  private Instant expiryDate;

  public EmailVerificationToken(String id, Long userId, Instant expiryDate) {
    this.id = id;
    this.userId = userId;
    this.expiryDate = expiryDate;
  }

  public void verifyExpiration() {
    if (this.expiryDate.compareTo(Instant.now()) < 0) {
      throw new TokenRefreshException("이메일 인증 시간이 만료되었습니다. 이메일 인증 요청을 다시 해주세요.");
    }
  }
}