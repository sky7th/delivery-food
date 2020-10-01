package com.sky7th.deliveryfood.utils.resolver;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AES256Utils {

  private AES256Utils() {
  }

  public static String encrypt(final String key, final String data) {
    try {
      byte[] keyData = key.getBytes(StandardCharsets.UTF_8);
      byte[] ivData = key.substring(0, 16).getBytes(StandardCharsets.UTF_8);
      SecretKey secretKey = new SecretKeySpec(keyData, "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivData));

      byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
      return new String(Base64.getEncoder().encode(encrypted));
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
        | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
      log.error("AES Util Encrypt Error",  e);
    }
    return null;
  }

  public static String decrypt(final String key, final String encryptedData) {
    byte[] keyData;
    try {
      keyData = key.getBytes(StandardCharsets.UTF_8);
      byte[] ivData = key.substring(0, 16).getBytes(StandardCharsets.UTF_8);
      SecretKey secretKey = new SecretKeySpec(keyData, "AES");
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivData));
      byte[] decrypted = Base64.getDecoder().decode(encryptedData.getBytes(StandardCharsets.UTF_8));
      return new String(cipher.doFinal(decrypted), StandardCharsets.UTF_8);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException
        | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
      log.error("AES Util Decrypt Error", e);
    }
    return null;
  }
}