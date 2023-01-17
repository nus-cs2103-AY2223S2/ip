package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandResponse;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ToDoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the chatbot
 */
public class Duke {
    private final static ByeCommand byeCommand = new ByeCommand();
    private final static DeadlineCommand deadlineCommand = new DeadlineCommand();
    private final static DeleteCommand deleteCommand = new DeleteCommand();
    private final static EventCommand eventCommand = new EventCommand();
    private final static ListCommand listCommand = new ListCommand();
    private final static MarkCommand markCommand = new MarkCommand();
    private final static ToDoCommand toDoCommand = new ToDoCommand();
    private final static UnmarkCommand unmarkCommand = new UnmarkCommand();

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

            CommandResponse res;
            try {
                res = getCommand(input).run(input, tasks);
            } catch (DukeException e) {
                printMessage(e.getMessage());
                continue;
            }

            tasks = res.getTasks();
            printMessage(res.getMessage());

            if (shouldExit(input)) {
                break;
            }
        }
    }

    private static Command getCommand(String input) throws DukeException {
        String op = input.split(" ")[0];

        switch (op) {
            case "bye":
                return byeCommand;
            case "deadline":
                return deadlineCommand;
            case "delete":
                return deleteCommand;
            case "event":
                return eventCommand;
            case "list":
                return listCommand;
            case "mark":
                return markCommand;
            case "todo":
                return toDoCommand;
            case "unmark":
                return unmarkCommand;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static boolean shouldExit(String input) {
        return input.split(" ")[0].equals("bye");
    }
}
