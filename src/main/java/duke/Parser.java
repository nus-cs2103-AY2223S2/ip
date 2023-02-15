package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InsufficientArguments;
import duke.exception.UnknownCommand;
import duke.task.TaskList;

/**
 * Implements the parsing of command line arguments.
 * <p>
 * Then create and put the corresponding command with the parsed input as arguments.
 */
public class Parser {
    private static Command command;

    /**
     * Parses the user's input and create the corresponding command with the user input.
     * @param input The CLI input from the user.
     * @throws DukeException
     */
    public static void createCommand (String input) throws DukeException {
        switch (input) {
        default: {
            parseLongCommand(input);
            break;
        }
        case "list": {
            Parser.command = new List(input);
            break;
        }
        case "bye": {
            Parser.command = new Bye();
            break;
        }
        }
    }

    /**
     * Parses the long user input that contains arguments.
     * @param input User input that contains arguments.
     * @throws DukeException
     */
    private static void parseLongCommand(String input) throws DukeException {
        if (input.matches("mark+ [0-9]+")) {
            Parser.command = new Mark(input);
        } else if (input.matches("unmark+ [0-9]+")) {
            Parser.command = new Unmark(input);
        } else if (input.matches("delete+ [0-9]+")) {
            Parser.command = new Delete(input);
        } else if (input.matches("find\\s.*$")) {
            Parser.command = new Find(input);
        } else if (input.matches("update\\s.*$")) {
            if (input.split(" ").length < 3) {
                throw new InsufficientArguments("OOPS!!! The format of update must follow " +
                        "update [task_index] [d/MM/yyyy HHmm] [d/MM/yyyy HHmm]");
            }
            Parser.command = new Update(input);
        } else if (input.matches("^deadline\\s.*$") || input.matches("^event\\s.*$") ||
                input.matches("^todo\\s.*$")) {
            if (input.split(" ").length < 2) {
                throw new InsufficientArguments("OOPS!!! The description of a " +
                        input.split(" ")[0] + " cannot be empty.");
            } else {
                Parser.command = new TaskCreationCommands(input);
            }
        } else {
            throw new UnknownCommand("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /***
     * Executes all commands waiting in the queue.
     * @param list The list of commands to execute.
     */
    public static String execute (TaskList list){
        return Parser.command.execute(list);
    }
}
