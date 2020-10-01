package com.sky7th.deliveryfood.security;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sky7th.deliveryfood.security.exception.InvalidTokenRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class JwtTokenValidatorTest {

  public static final String JWT_SECRET = "jwtSecret";

  private JwtTokenValidator jwtTokenValidator = new JwtTokenValidator(JWT_SECRET);

  @Test
  void 다른_jwt_비밀키를_사용했을_경우_exception_발생() {
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwicm9sZSI6IlJPTEVfTUVNQkVSIiwiaWF0IjoxNTk4NzkwMjcxLCJleHAiOjE1OTg3OTExNzF9.yT_yGE9gOS7iYZV9LMabaHU4hJ792vYXGZF3hkiizN2nBiwaiCucMdQWv0fL1rceiXT_lWtGySyGlKoBu75rfQ";

    jwtTokenValidator = new JwtTokenValidator("fakeSecret");

    assertThrows(InvalidTokenRequestException.class, () -> {
      jwtTokenValidator.validateToken(token);
    });
  }

  @Test
  void 토큰의_payload_내용이_바뀌면_검증에_실패하고_exception_발생() {
    String changedPayloadToken = "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxIiwicm9sZSI6IlJPTEVfTUVNQkVSIiwiaWF0IjoxNTk4NzkwMjcxLCJleHAiOjE1OTg3OTExNzF1.yT_yGE9gOS7iYZV9LMabaHU4hJ792vYXGZF3hkiizN2nBiwaiCucMdQWv0fL1rceiXT_lWtGySyGlKoBu75rfQ";
    System.out.println(ReflectionTestUtils.getField(jwtTokenValidator, "jwtSecret"));
    assertThrows(InvalidTokenRequestException.class, () -> {
      jwtTokenValidator.validateToken(changedPayloadToken);
    });
  }
}