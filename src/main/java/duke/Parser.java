package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InsufficientArguments;
import duke.exception.UnknownCommand;
import duke.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Implements the parsing of command line arguments, and create and put the corresponding command with the parsed input
 * arguments into the command queue.
 */
public class Parser {
    private static Command command;

    /**
     * Creates a command according to the user input, parse it,
     * and pass the relevant input into the command created,
     * and store the command in the command queue.
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
        } else if (input.matches("find by date\\s.*$")) {
            String[] substrings = input.split(" date ");
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            format.withLocale(Locale.ENGLISH);
            LocalDateTime time = LocalDateTime.parse(substrings[1], format);
            Parser.command = new SearchByDate(time);
        } else if (input.matches("find\\s.*$")) {
            Parser.command = new Find(input);
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
     * @param list the list of commands to execute.
     */
    public static String execute (TaskList list){
        return Parser.command.execute(list);
    }
}
