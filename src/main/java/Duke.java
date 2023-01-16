import java.util.Scanner;
public class Duke {

    /**
     * Echos user input. If user input is "bye", then exit.
     */
    public static void greet() {
        Scanner userInput = new Scanner(System.in);

        while (true) {
            String input = userInput.nextLine();

            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
            } else {
                System.out.println(input);
            }

            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }
        }

        userInput.close();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        greet();
    }
}
