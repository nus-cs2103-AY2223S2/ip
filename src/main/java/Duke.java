import command.Command;
import dukeexeption.DukeException;
import parser.Request;
import storage.TaskList;

import java.util.Scanner;

public class Duke {
    /**
     * Prints the formatted response in the console.
     * @param response  the string to be printed
     */
    public static void returnFormattedResponse(String response) {
        final int INDENTS = 4;

        String lineAddedResponse = "____________________________________________________________\n"
                + " " + response.replace("\n", "\n" + " ")
                + "\n____________________________________________________________\n";
        String indentedResponse = " ".repeat(INDENTS)
                + lineAddedResponse.replace("\n", "\n" + " ".repeat(INDENTS));

        System.out.println(indentedResponse);;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        final String INTRODUCTION_MESSAGE = "Hello! I'm Duke\n"
                + "What can I do for you?";
        final String EXITING_MESSAGE = "Bye. Hope to see you again soon!";

        System.out.println(logo);
        returnFormattedResponse(INTRODUCTION_MESSAGE);

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while(true) {
            String request = scanner.nextLine();
            if (request.equals("bye")) {
                break;
            }
            try {
                Command command = new Request(request).parse();
                String reply = command.run(taskList);
                returnFormattedResponse(reply);
            } catch(DukeException error) {
                returnFormattedResponse(error.toString());
            }
        }

        returnFormattedResponse(EXITING_MESSAGE);
    }
}
