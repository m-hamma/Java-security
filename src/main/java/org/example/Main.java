package org.example;
import java.util.*;
import java.security.MessageDigest;
import java.security.Security;
import java.security.Provider;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {

        //exmpleCryptageMDP();
        Provider[] providers = Security.getProviders();
        for (Provider provider:providers) {
            System.out.println("Provider :"+provider.getName()+" - "+provider.getVersionStr());
        }
        listExemples();
    }

    private static void listExemples() {
        Queue<String> namesQueue = new LinkedList<>();
        Deque<Integer> numbersDeque = new ArrayDeque<>();
        BlockingQueue<String> waitingCustomers = new ArrayBlockingQueue<>(100);

        for (int i=0;i<105;i++) {
            waitingCustomers.offer("valeur"+i);
        }
        System.out.println(waitingCustomers.remove());
        System.out.println(waitingCustomers.remove());
        System.out.println(waitingCustomers.peek());
        System.out.println(waitingCustomers.remove());
        List<String> listNames = Arrays.asList("Alice", "Bob", "Cole", "Dale", "Eric", "Frank");
        Queue<String> queueNames = new LinkedList<>(listNames);
        System.out.println(""+waitingCustomers.size()+" - " +queueNames);

//        Queue<Integer> queueNumbers = new ArrayBlockingQueue<>(3);
//        queueNumbers.add(1);
//        queueNumbers.add(2);
//        queueNumbers.add(3);
//        queueNumbers.add(4);

        BlockingQueue<Integer> queueNumbers = new ArrayBlockingQueue<>(100);

        try {
            queueNumbers.put(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        Queue<String> queueCust = new LinkedList<>();
        queueCust.offer("Jack");


        Deque<String> queueCustomers = new ArrayDeque<>();

        queueCustomers.offer("Bill");
        queueCustomers.offer("Kim");
        queueCustomers.offer("Lee");
        queueCustomers.offer("Peter");
        queueCustomers.offer("Sam");

        System.out.println("Queue before: " + queueCustomers);
        queueCustomers.forEach(name -> System.out.println(name));
        System.out.println("First comes: " + queueCustomers.pollFirst());
        System.out.println("Last comes: " + queueCustomers.pollLast());
        System.out.println("Queue after: " + queueCustomers);


        String next = queueCust.remove();
        System.out.println("Next customer is: "+ next);

        next = queueCust.remove(); // throws exception
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