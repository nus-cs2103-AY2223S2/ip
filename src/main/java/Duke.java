import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import parser.InputParser;

import response.Response;
import storage.Storage;
import storage.TaskList;

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
        Storage storage = new Storage(); // Create a Storage object
        TaskList taskList = storage.loadTaskList();  // Create a TaskList object based on saved list


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
                String out = res.exec(taskList);

                // If the response is anything other than list, save the to do list
                if (!input.getInputType().equals("LIST")) {
                    storage.saveTaskList(taskList);
                }

                // Print the output message
                Duke.print(out);
            } catch (DukeException err) {
                Duke.print(err.toString());
            }
        }
        Duke.print(extStr);
    }
}
