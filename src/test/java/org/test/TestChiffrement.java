package org.test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.security.NoSuchAlgorithmException;

public class TestChiffrement {

    public static void main(String[] args) throws Exception{
        String originalContent = "foobar1234";
        SecretKey secretKey = generateKey();

        FileEncrypterDecrypter fileEncrypterDecrypter
                = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        fileEncrypterDecrypter.encrypt(originalContent, "baz.enc");

        String decryptedContent = fileEncrypterDecrypter.decrypt("baz.enc");
        //assertThat(decryptedContent, is(originalContent));
           System.out.println(decryptedContent);
        new File("baz.enc").delete(); // cleanup
    }
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(128);
        return keygenerator.generateKey();
    }

}
