import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Adds user input to a list.
     * Supports several tasks, such as todo,
     * deadline and event.
     */
    public static void greet() {
        Scanner userInput = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            // Stores the user input.
            String input = userInput.nextLine();

            System.out.println("____________________________________________________________");

            try {
                if (input.equals("bye")) {
                    // User input: bye
                    System.out.println("Bye. Hope to see you soon!");
                } else if (input.matches("todo(.*)")) {
                    // User input: todo x
                    input = input.replace("todo ", "");
                    if (input.equals("todo")) {
                        // Checks if todo is empty.
                        throw new DukeException("todo");
                    } else {
                        System.out.println("Got it. I've added this task:");
                        list.add(new Todo(input));
                        System.out.println("    " + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else if (input.matches("deadline(.*)")) {
                    // User input: deadline x
                    input = input.replace("deadline ", "");
                    if (input.equals("deadline")) {
                        // Checks if deadline is empty.
                        throw new DukeException("deadline");
                    } else {
                        String[] inputs = input.split(" /by ", 2);
                        System.out.println("Got it. I've added this task:");
                        list.add(new Deadline(inputs[0], inputs[1]));
                        System.out.println("    " + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else if (input.matches("event(.*)")) {
                    // User input: event x
                    input = input.replace("event ", "");
                    if (input.equals("event")) {
                        // Checks if event is empty.
                        throw new DukeException("event");
                    } else {
                        String[] inputs = input.split(" /", 3);
                        inputs[1] = inputs[1].replace("from ", "");
                        inputs[2] = inputs[2].replace("to ", "");
                        System.out.println("Got it. I've added this task:");
                        list.add(new Event(inputs[0], inputs[1], inputs[2]));
                        System.out.println("    " + list.get(list.size() - 1).toString());
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else if (input.equals("list")) {
                    // User input: list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i).toString());
                    }
                } else if (input.matches("mark(.*)")) {
                    // User input: mark x
                    // Extracts the numbered item from the user input string
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    if ((index < 0) | (index > (list.size() - 1)) ) {
                        // Checks if provided index is in range.
                        throw new DukeException("index");
                    } else {
                        list.get(index).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + list.get(index).toString());
                    }
                } else if (input.matches("unmark(.*)")) {
                    // User input: unmark x
                    // Extracts the numbered item from the user input string
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    if ((index < 0) | (index > (list.size() - 1)) ) {
                        // Checks if provided index is in range.
                        throw new DukeException("index");
                    } else {
                        list.get(index).markAsUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("    " + list.get(index).toString());
                    }
                } else {
                    // Unknown command.
                    throw new DukeException("unknown");
                }
            } catch (DukeException e) {
                if (e.getMessage().equals("index")) {
                    System.out.println("☹ OOPS!!! Index out of range.");
                } else if (e.getMessage().equals("unknown")) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else {
                    System.out.println("☹ OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                }
            }
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                break;
            }
        }

        userInput.close();
    }

    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        greet();
    }
}
