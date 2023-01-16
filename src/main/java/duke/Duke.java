package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandResponse;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the chatbot
 */
public class Duke {
    private final static AddCommand addCommand = new AddCommand();
    private final static ByeCommand byeCommand = new ByeCommand();
    private final static ListCommand listCommand = new ListCommand();
    private final static MarkCommand markCommand = new MarkCommand();

    private static List<Task> tasks = new ArrayList<Task>();

    /**
     * Main method for the chatbot
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        printGreeting();
        runInputLoop();
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage(String.format("Hello from\n%s\nWhat can I do for you?", logo));
    }

    private static void printMessage(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("     %s\n", message.replace("\n", "\n     "));
        System.out.print("    ____________________________________________________________\n\n");
    }

    private static void runInputLoop() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            CommandResponse res = getCommand(input).run(input, tasks);

            tasks = res.getTasks();
            printMessage(res.getMessage());

            if (input.equals("bye")) {
                break;
            }
        }
    }

    private static Command getCommand(String input) {
        String op = input.split(" ")[0];

        switch (op) {
            case "bye":
                return byeCommand;
            case "list":
                return listCommand;
            case "mark":
                return markCommand;
            default:
                return addCommand;
        }
    }
}
