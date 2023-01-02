import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Duke's greeting
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);

        // Take user input
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        // If input != bye, echo input
        while (!command.equals("bye")) {
            System.out.println(command);
            command = scanner.nextLine();
        }

        if (command.equals("bye")) {
            String goodbye = "Bye. Hope to see you again soon!";
            System.out.println(goodbye);
        }
    }
}
