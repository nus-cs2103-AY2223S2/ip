package utils;

import java.util.Scanner;
public class DukeIO {
    static final String GREET = "Hello, I'm Ekud! What can I do for you?";
    static final String FAREWELL = "Bye. Always at your service.";
    static final String terminateString = "bye";
    private static Scanner scanner;
    public static void initialiseIO() {
        scanner = new Scanner(System.in);
        System.out.println(wrapContent(GREET));
    }

    public static void waitForInput() {
        while(true) {
            String input = scanner.nextLine();
            if (input.equals(terminateString)) {
                System.out.println(wrapContent(FAREWELL));
                return;
            }
            System.out.println(wrapContent(input));
        }
    }

    private static String wrapContent(String content) {
        return "\t~~~\n\t" + content + "\n\t~~~";
    }
}
