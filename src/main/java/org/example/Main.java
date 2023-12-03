package org.example;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.Security;
import java.security.Provider;

public class Main {
    public static void main(String[] args) {

        //exmpleCryptageMDP();
        Provider[] providers = Security.getProviders();
        for (Provider provider:providers) {
            System.out.println("Provider :"+provider.getName()+" - "+provider.getVersionStr());
        }

    }

    private static void exmpleCryptageMDP() {
        // creates an object of Scanner
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");

        // takes input from the keyboard
        String password = input.nextLine();

        input.close();
        System.out.println(password);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            //convertir le tableau de bits en une format hexadécimal - méthode 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("En format hexa : " + sb.toString());

            //convertir le tableau de bits en une format hexadécimal - méthode 2
            final StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("En format hexa : " + hexString.toString());
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println("Ereur : "+e.getMessage());
        }
    }
}