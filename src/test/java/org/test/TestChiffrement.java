package org.test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;

public class TestChiffrement {

    public static void main(String[] args) throws Exception{
        String originalContent = "foobar";
        SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();

        FileEncrypterDecrypter fileEncrypterDecrypter
                = new FileEncrypterDecrypter(secretKey, "AES/CBC/PKCS5Padding");
        fileEncrypterDecrypter.encrypt(originalContent, "baz.enc");

        String decryptedContent = fileEncrypterDecrypter.decrypt("baz.enc");
        //assertThat(decryptedContent, is(originalContent));
           System.out.println(decryptedContent);
        new File("baz.enc").delete(); // cleanup
    }
}
