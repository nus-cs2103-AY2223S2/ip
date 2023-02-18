package duke.components;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Arrays;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.DukeException;

/***
 * This is the Parser class for Duke, the CLI task manager.
 * This class handles parsing of user commands from the CLI
 * and handles Command creation.
 */

public class Parser {

    /**
     * Returns an ArrayList of processed user input for parsing.
     *
     * @param input text input from CLI from user.
     * @return ArrayList of the user input with whitespace removed.
     */
    private static ArrayList<String> tokenize(String input) {
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(input.split(" ")));
        tokens.removeIf(s -> s.equals(" ") || s.equals(""));
        tokens.forEach(s -> s = s.trim());
        return tokens;
    }

    /**
     * Returns a Command depending on the first word in
     * user's text input.
     *
     * @param userIn user input of type String.
     * @return Command depending on keyword.
     * @throws DateTimeException if DateTime parsing unsuccessful.
     * @throws DukeException if userIn does not follow specified command syntax.
     */
    public static Command parse(String userIn) throws DateTimeException, DukeException {
        ArrayList<String> tokens = tokenize(userIn);
        String key;
        try {
            key = tokens.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Looks like you entered an empty command, try again!");
        }
        Command ret = null;

        switch (key) {

        case "bye":
            ret = new ExitCommand(tokens);
            break;
        case "list":
            ret = new ListCommand(tokens);
            break;
        case "mark":
            ret = new MarkCommand(tokens);
            break;
        case "unmark":
            ret = new UnmarkCommand(tokens);
            break;
        case "delete":
            ret = new DeleteCommand(tokens);
            break;
        case "todo":
            ret = new AddToDoCommand(tokens);
            break;
        case "deadline":
            ret = new AddDeadlineCommand(tokens);
            break;
        case "event":
            ret = new AddEventCommand(tokens);
            break;
        case "find":
            ret = new FindCommand(tokens);
            break;
        default:
            throw new DukeException("I'm sorry, I could not understand that command.");
        }

        return ret;
    }

}
