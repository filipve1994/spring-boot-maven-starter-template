package org.fve.springbootprojects.springbootstarterapp.modules.encryption.service;

import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.modules.encryption.utils.AESCipher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AESCipherService {

    private static final Logger log = LoggerFactory.getLogger(AESCipherService.class);

    public String encrypt(String plainText, String secretKey) throws Exception {
        return AESCipher.encrypt(plainText, secretKey);
    }

    public String decrypt(String encryptedText, String secretKey) throws Exception {
        return AESCipher.decrypt(encryptedText, secretKey);
    }
}