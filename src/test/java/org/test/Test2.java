package org.test;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args)  throws Exception{
        final OtherExemple otherExemple = new OtherExemple();
        final SecretKey symmetricKey = otherExemple.getSecretKey();
        final IvParameterSpec iv = otherExemple.getIvParameterSpec();

        // Takes input from the keyboard
        Scanner input = new Scanner(System.in);
        System.out.print("Veuillez saisir le message à crypté : ");
        final String plainText = input.nextLine();
        input.close();

        // Encrypt the message using the symmetric key
        byte[] cipherText = otherExemple.encrypt(plainText, symmetricKey, iv);

        System.out.println("The encrypted message is: " + cipherText);

        // Decrypt the encrypted message
        String decryptedText = otherExemple.decrypt(cipherText, symmetricKey, iv);

        System.out.println("Your original message is: " + decryptedText);
        try {
            int variable = 1;
            //System.out.println(variable / 0);
        } finally {
            System.out.println("the other part of the program...");
        }
        Cat cat = new Cat();
        cat.talk();
        Chien chien = new Chien();
        chien.talk();
        Animal.talk();
    }
}
