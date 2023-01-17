import java.util.Scanner;

import exception.DukeException;
import parser.InputParser;

import response.Response;
import storage.ToDoList;

public class Duke {
    /**
     * Prints formatted response to the console.
     *
     * @param s string that will be printed
     */
    public static void print(String s) {
        String p = String.format("\t____________________________________________________________\n" +
                "\t %s\n" +
                "\t____________________________________________________________\n", s);
        System.out.println(p);
    }

    public static void main(String[] args) {
        final String intro = "Hello! I'm Duke\n\t What can I do for you?";
        final String extStr = "Bye! Hope to see you again soon!";

        Duke.print(intro);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        ToDoList toDoList = new ToDoList();

        while (true) {
            String req = scanner.nextLine();  // Read user req

            // Check if user wants to exit
            if (req.equalsIgnoreCase("bye")) {
                break;
            }

            // Parse the user input
            InputParser input = new InputParser(req);
            try {
                // Parsing the input returns a Response
                Response res = input.parse();

                // Execute the Response to do what needs to be done and get an output message
                String out = res.exec(toDoList);

                // Print the output message
                Duke.print(out);
            } catch (DukeException err) {
                Duke.print(err.toString());
            }
        }
        Duke.print(extStr);
    }
}
