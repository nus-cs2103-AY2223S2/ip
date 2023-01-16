import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    /**
     * Adds user input to a list.
     * If user input is "list", display all items back, numbered.
     * If user input is "mark", followed by a number,
     * mark the item specified by the number as done.
     * If user input is "unmark", followed by a number,
     * mark the item specified by the number as undone.
     * If user input is "bye", then exit.
     */
    public static void greet() {
        Scanner userInput = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            // Stores the user input.
            String input = userInput.nextLine();

            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you soon!");
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". "
                            + "[" + list.get(i).getStatusIcon() + "] " + list.get(i).description);
                }
            } else if (input.matches("mark(.*)")) {
                // Extracts the numbered item from the user input string
                int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                list.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("    [" + list.get(index).getStatusIcon() + "] "
                        + list.get(index).description);
            } else if (input.matches("unmark(.*)")) {
                // Extracts the numbered item from the user input string
                int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                list.get(index).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("    [" + list.get(index).getStatusIcon() + "] "
                        + list.get(index).description);
            } else {
                System.out.println("added: " + input);
                list.add(new Task(input));
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
