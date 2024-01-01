package org.test;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.SecureRandom;

public class FileEncrypterDecrypter {
    private Cipher cipher;
    private SecretKey secretKey;
    private byte[] fileIv;

    public FileEncrypterDecrypter(SecretKey secretKey, String transformation) throws Exception{
        this.secretKey = secretKey;
        this.cipher = Cipher.getInstance(transformation);

    }
    public void encrypt(String content, String fileName) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
       // byte[] iv = cipher.getIV();

        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)) {
            byte[] iv = cipher.getIV();
            fileOut.write(iv);
            cipherOut.write(content.getBytes());
        }
    }
    public IvParameterSpec generateIv(FileInputStream fileIn,byte[] fileIv) throws IOException {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(fileIv);
        //fileIn.read(fileIv);
        fileIn.read(fileIv);
        return new IvParameterSpec(fileIv);
    }

    public String decrypt(String fileName) throws Exception {
        String content;

        try (FileInputStream fileIn = new FileInputStream(fileName)) {
            byte[] fileIv = new byte[16];

            cipher.init(Cipher.DECRYPT_MODE, secretKey,generateIv(fileIn,fileIv));

            try (
                    CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
                    InputStreamReader inputReader = new InputStreamReader(cipherIn);
                    BufferedReader reader = new BufferedReader(inputReader)
            ) {

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                content = sb.toString();
            }

        }
        return content;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public void setCipher(Cipher cipher) {
        this.cipher = cipher;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
}
