package org.test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OtherExemple {

    private IvParameterSpec ivParameterSpec;
    private Cipher cipher;
    private SecretKey secretKey;

    public OtherExemple() throws Exception {
        this.ivParameterSpec = generateIv();
        this.cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        this.secretKey = generateKey();
    }
    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(128);
        return keygenerator.generateKey();
    }
    public IvParameterSpec generateIv() {
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return new IvParameterSpec(initializationVector);
    }

    public byte[] encrypt(String input, SecretKey key, IvParameterSpec iv)
            throws Exception {
        this.cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));
    }
    public String decrypt(byte[] cipherText, SecretKey key, IvParameterSpec iv) throws Exception {
        this.cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(cipherText);
        return new String(plainText);
    }

    public IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
