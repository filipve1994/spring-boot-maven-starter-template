package org.fve.springbootprojects.springbootstarterapp.modules.encryption.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.encryption.exceptions.CipherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CipherService {

    private static final Logger log = LoggerFactory.getLogger(CipherService.class);


    @Value("${app.secret}")
    private String appSecret;

    private final AESCipherService aesCipherService;

    public CipherService(@Value("${app.secret}") String appSecret, AESCipherService aesCipherService) {
        this.appSecret = appSecret;
        this.aesCipherService = aesCipherService;
    }

    /**
     * Encrypt plain text with secret key.
     *
     * @param plainText String
     * @param secretKey String (256 bit)
     * @return String
     * @throws RuntimeException Encrypting exception
     */
    public String encrypt(String plainText, String secretKey) {
        try {
            return aesCipherService.encrypt(plainText, secretKey);
        } catch (Exception e) {
            log.error("Encrypting error", e);
            throw new CipherException(e);
        }
    }

    /**
     * Encrypt plain text with app secret.
     *
     * @param plainText String
     * @return String
     * @throws RuntimeException Encrypting exception
     */
    public String encrypt(String plainText) {
        return encrypt(plainText, appSecret);
    }

    /**
     * Decrypt cipher text with secret key.
     *
     * @param encryptedText String
     * @param secretKey     String (256 bit)
     * @return String
     * @throws RuntimeException Decrypting exception
     */
    public String decrypt(String encryptedText, String secretKey) {
        try {
            return aesCipherService.decrypt(encryptedText, secretKey);
        } catch (Exception e) {
            log.error("Decrypting error", e);
            throw new CipherException(e);
        }
    }

    /**
     * Decrypt cipher text with app secret.
     *
     * @param encryptedText String
     * @return String
     * @throws RuntimeException Decrypting exception
     */
    public String decrypt(String encryptedText) {
        return decrypt(encryptedText, appSecret);
    }
}