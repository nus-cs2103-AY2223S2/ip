import java.util.Scanner;
import java.util.ArrayList;

/**
 * A chatbot with functionality to add or remove different
 * types of Tasks.
 */
public class Duke {

    // Enums for the different commands
    private enum Command {
        TODO, DEADLINE, EVENT, LIST,
        MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    /**
     * Adds user input to a list.
     * Supports several tasks, such as todo,
     * deadline and event.
     * Operations: todo, deadline, event, list,
     * mark, unmark, delete.
     */
    public static void greet() {
        Scanner userInput = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            // Stores the user input.
            String input = userInput.nextLine();
            Command inputType;

            if (input.equals("bye")) {
                inputType = Command.BYE;
            } else if (input.matches("todo(.*)")) {
                inputType = Command.TODO;
            } else if (input.matches("deadline(.*)")) {
                inputType = Command.DEADLINE;
            } else if (input.matches("event(.*)")) {
                inputType = Command.EVENT;
            } else if (input.equals("list")) {
                inputType = Command.LIST;
            } else if (input.matches("mark(.*)")) {
                inputType = Command.MARK;
            } else if (input.matches("unmark(.*)")) {
                inputType = Command.UNMARK;
            } else if (input.matches("delete(.*)")) {
                inputType = Command.DELETE;
            } else {
                inputType = Command.UNKNOWN;
            }

            System.out.println("____________________________________________________________");

            try {
                if (inputType == Command.BYE) {
                    // User input: bye
                    System.out.println("Bye. Hope to see you soon!");
                } else if (inputType == Command.TODO) {
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
                } else if (inputType == Command.DEADLINE) {
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
                } else if (inputType == Command.EVENT) {
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
                } else if (inputType == Command.LIST) {
                    // User input: list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i).toString());
                    }
                } else if (inputType == Command.MARK) {
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
                } else if (inputType == Command.UNMARK) {
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
                } else if (inputType == Command.DELETE) {
                    // User input: delete x
                    int index = Integer.parseInt(input.replaceAll("[^0-9]", "")) - 1;
                    if ((index < 0) | (index > (list.size() - 1)) ) {
                        // Checks if provided index is in range.
                        throw new DukeException("index");
                    } else {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("    " + list.get(index).toString());
                        list.remove(index);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else if (inputType == Command.UNKNOWN){
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
