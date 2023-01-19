import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printGreeting();

        while (true) {
            String userInput = getUserInput();
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else {
                printEcho(userInput);
            }
        }
    }

    private static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    private static void printEcho(String userInput) {
        System.out.println();
        System.out.println(userInput);
        printLine();
    }

    private static String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printGreeting() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }


}
