import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static AtomicInteger lengthThree = new AtomicInteger(0);
    public static AtomicInteger lengthFour = new AtomicInteger(0);
    public static AtomicInteger lengthFive = new AtomicInteger(0);


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));

        }
        Thread thread1 = new Thread(() -> {
            for (String name : texts) {
                if (palindrome(name)) {
                    switch (name.length()) {
                        case 3:
                            lengthThree.incrementAndGet();
                            break;
                        case 4:
                            lengthFour.incrementAndGet();
                            break;
                        case 5:
                            lengthFive.incrementAndGet();
                            break;
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (String name : texts) {
                if (oneChar(name)) {
                    switch (name.length()) {
                        case 3:
                            lengthThree.incrementAndGet();
                            break;
                        case 4:
                            lengthFour.incrementAndGet();
                            break;
                        case 5:
                            lengthFive.incrementAndGet();
                            break;

                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (String name : texts) {
                if (ascending(name)) {
                    switch (name.length()) {
                        case 3:
                            lengthThree.incrementAndGet();
                            break;
                        case 4:
                            lengthFour.incrementAndGet();
                            break;
                        case 5:
                            lengthFive.incrementAndGet();
                            break;
                    }

                }
            }
        });
        thread1.start();
        thread2.start();
        thread3.start();
        thread1.join();
        thread2.join();
        thread3.join();
        System.out.println("Красивых слов c длиной 3: " + lengthThree.get() + " шт");
        System.out.println("Красивых слов c длиной 4: " + lengthFour.get() + " шт");
        System.out.println("Красивых слов c длиной 5: " + lengthFive.get() + " шт");

    }

    public static boolean palindrome(String name) {
        return name.equalsIgnoreCase(new StringBuilder(name).reverse().toString());
    }

    public static boolean oneChar(String name) {
        for (int i = 0; i < name.length() - 1; i++) {
            if (name.charAt(i) != name.charAt(i + 1)) {
                return false;

            }

        }
        return true;
    }
    public static boolean ascending(String name) {
        for (int i = 0; i < name.length() - 1; i++) {
            if (name.charAt(i) > name.charAt(i + 1)) {
                return false;
            }

        }
        return true;
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();

    }
}