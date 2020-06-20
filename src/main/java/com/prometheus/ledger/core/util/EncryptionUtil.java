package com.prometheus.ledger.core.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class EncryptionUtil {

    public static String encrypt(String message, String key){
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        return encryptor.encrypt(message);
    }

    public static String decrypt(String message, String key){
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(key);
        return decryptor.decrypt(message);
    }

    public static String sha256Hash(String message){
        return DigestUtils.sha256Hex(message);
    }

}
