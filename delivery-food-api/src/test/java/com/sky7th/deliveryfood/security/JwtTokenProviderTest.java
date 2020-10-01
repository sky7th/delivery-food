package com.sky7th.deliveryfood.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {

  public static final String JWT_SECRET = "jwtSecret";
  public static final long JWT_ACCESS_TOKEN_EXPIRATION = 100000;
  public static final long JWT_REFRESH_TOKEN_EXPIRATION = 20000000;

  @InjectMocks
  private JwtTokenProvider jwtTokenProvider;

  @Mock
  private JwtTokenValidator jwtTokenValidator;

  @BeforeEach
  void setup() {
    ReflectionTestUtils.setField(jwtTokenProvider, "jwtSecret", JWT_SECRET);
    ReflectionTestUtils.setField(jwtTokenProvider, "jwtAccessTokenExpirationInMs", JWT_ACCESS_TOKEN_EXPIRATION);
    ReflectionTestUtils.setField(jwtTokenProvider, "jwtRefreshTokenExpirationInMs", JWT_REFRESH_TOKEN_EXPIRATION);
  }

//  @Test
//  void 유저_정보로_Access_Token을_생성하고_해당_Token에서_유저_정보를_얻는다() {
//    Long userId = 1L;
//    UserRole userRole = UserRole.ROLE_MEMBER;
//    String token = jwtTokenProvider.generateAccessToken(userId, userRole);
//    Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
//    when(jwtTokenValidator.validateToken(anyString())).thenReturn(claims);
//
//    UserContext userContext = jwtTokenProvider.getUserContextFromJwt(token);
//
//    assertThat(userContext.getId()).isEqualTo(userId);
//    assertThat(userContext.getRole()).isEqualTo(userRole);
//  }

  @Test
  void Access_Token_유효기간을_가져온다() {
    long jwtAccessTokenExpirationInMs = jwtTokenProvider.getAccessTokenExpiryDuration();

    assertThat(jwtAccessTokenExpirationInMs).isEqualTo(JWT_ACCESS_TOKEN_EXPIRATION);
  }
}