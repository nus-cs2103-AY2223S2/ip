import java.util.Scanner;

public class Duke {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        greetings();

        while (true) {
            String input = getUserInput();
            if (input.equals("bye"))
                break;

            echo(input);
        }

        bye();
    }

    public static void greetings() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String input) {
        System.out.println(input);
    }

    public static String getUserInput() {
            String userInput = in.nextLine();
            return userInput.toLowerCase();
    }
}
