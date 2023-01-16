import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    /**
     * Adds user input to a list.
     * If user input is "list", display all items back, numbered.
     * If user input is "bye", then exit.
     */
    public static void greet() {
        Scanner userInput = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        while (true) {
            String input = userInput.nextLine();

            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
            } else {
                System.out.println("added: " + input);
                list.add(input);
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
