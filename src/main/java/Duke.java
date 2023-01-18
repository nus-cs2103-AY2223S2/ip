import java.util.Scanner;
public class Duke {

    /**
    Helper method to format response to user input
     */
    private static String respond(String input) {
        return "added: " + input;
    }
    public static void main(String[] args) {
        String welcomeMsg = "Hello i'm Duke\nWhat can I do for you?";
        String goodbyeMsg = "Bye. Hope to see you again soon!";
        System.out.println(welcomeMsg);

        Scanner scanner = new Scanner(System.in); // creates a scanner object
        while (true) {
            String echo = scanner.nextLine(); // get user input
            if (echo.equals("bye")) {
                System.out.println(goodbyeMsg);
                return;
            }
            System.out.println(respond(echo));
        }
    }
}
