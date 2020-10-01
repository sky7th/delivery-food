package com.sky7th.deliveryfood.generic.mail.domain.token;

import org.springframework.data.repository.CrudRepository;

public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken, String> {
}
