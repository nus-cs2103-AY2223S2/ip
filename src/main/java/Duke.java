
import duke.command.TaskList;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDo;

import java.util.Scanner;

public class Duke {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String greeting = "Hello! I'm Alpha Beast What can I do for you?";
        greeting(greeting);
        //memory store = new memory();
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

                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");

            }


        }
    }

    static void greeting(String message) {
        System.out.println(message);
    }

    static void echo(String input) {
        if (input.equals("bye"))
            System.out.println("Bye. Hope to see you again soon!\n");
        else
            System.out.println(input);
    }
}

