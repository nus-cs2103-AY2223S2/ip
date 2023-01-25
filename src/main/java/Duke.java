import duke.command.TaskList;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDo;

import java.util.Scanner;

/**
 * The type Duke.
 */
public class Duke {

    /**
     * The Scanner Class.
     */
    static Scanner sc = new Scanner(System.in);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        String greeting = "Hello! I'm Alpha Beast What can I do for you?";
        greeting(greeting);
        TaskList manager = new TaskList("/Users/s.f/ip/src/Data/duke.txt");
        loop:
        while (true) {
            String input = sc.nextLine();
            String[] tokens = input.split(" ");
            String without_key = input.replace(tokens[0], "");

            switch (tokens[0]) {
                case "bye":
                    echo(input);
                    manager.file_writeAll();
                    break loop;

                case "list":
                    manager.displayAll();
                    break;

                case "mark":
                    manager.mark(Integer.parseInt(tokens[1]) - 1);
                    break;

                case "unmark":
                    manager.unmark(Integer.parseInt(tokens[1]) - 1);
                    break;


                case "todo":
                    ToDo todo = new ToDo(without_key, false);
                    manager.add(todo);
                    break;

                case "deadline":
                    Deadlines deadlines = new Deadlines(without_key, false);
                    manager.add(deadlines);
                    break;

                case "event":
                    Events events = new Events(without_key, false);
                    manager.add(events);
                    break;

                case "delete":
                    manager.delete(Integer.parseInt(tokens[1]) - 1);
                    break;

                case "find":
                    manager.find(without_key);
                    break;

                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

            }


        }
    }

    /**
     * Greeting.
     *
     * @param message the message
     */
    static void greeting(String message) {
        System.out.println(message);
    }

    /**
     * Echo.
     *
     * @param input the input
     */
    static void echo(String input) {
        if (input.equals("bye"))
            System.out.println("Bye. Hope to see you again soon!\n");
        else
            System.out.println(input);
    }
}

