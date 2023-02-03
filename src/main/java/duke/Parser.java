package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.enums.Commands;
import duke.enums.Views;

/**
 * Parses the input given from the user and returns the appropriate commands
 */
public class Parser {
    /**
     * Given the user input, sort it out and returns the command associated with the
     * input
     *
     * @param input string from the user
     * @return Command object that matches the user input
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        switch (input) {
        case "list":
            return new ListCommand();
        case "clear":
            return new ClearCommand();
        case "bye":
        case "exit":
            return new ExitCommand();
        default:
            // Have to do it at the starts with because what if "todo mark this as done"
            if (input.startsWith(Commands.MARK.cmd())) {
                int taskNum = getNumbers(input) - 1;
                return new MarkCommand(taskNum);
            } else if (input.startsWith(Commands.UNMARK.cmd())) {
                int taskNum = getNumbers(input) - 1;
                return new UnmarkCommand(taskNum);
            } else if (input.startsWith(Commands.TODO.cmd())) {
                String[] parsed = handleTask(input);
                String title = parsed[0];
                return new TodoCommand(title);
            } else if (input.startsWith(Commands.DEADLINE.cmd())) {
                String[] parsed = handleTask(input);
                String title = parsed[0];
                String deadline = parsed[1];
                return new DeadlineCommand(title, deadline);
            } else if (input.startsWith(Commands.EVENT.cmd())) {
                String[] parsed = handleTask(input);
                String title = parsed[0];
                String from = parsed[1];
                String to = parsed[2];
                return new EventCommand(title, from, to);
            } else if (input.startsWith(Commands.DEL.cmd())) {
                int taskNum = getNumbers(input) - 1;
                return new DeleteCommand(taskNum);
            } else if (input.startsWith(Commands.FIND.cmd())) {
                String query = input.substring(Commands.FIND.len());
                return new FindCommand(query);
            } else {
                throw new DukeException(Views.UNKNOWN_CMD_ERR_STRING.eng());
            }
        }
    }

    public static String[] handleTask(String input) throws DukeException {
        String[] returnString = new String[3];
        String title;
        if (input.startsWith(Commands.TODO.cmd())) {
            title = input.substring(Commands.TODO.len());
        } else if (input.startsWith(Commands.DEADLINE.cmd())) {
            int indexOfBy = input.indexOf(Commands.BY.cmd());
            if (indexOfBy == -1) {
                throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
            }
            title = input.substring(Commands.DEADLINE.len(), indexOfBy);
            String deadline = input.substring(indexOfBy);
            returnString[1] = deadline;
        } else if (input.startsWith(Commands.EVENT.cmd())) {
            int indexOfFrom = input.indexOf(Commands.FROM.cmd());
            int indexOfTo = input.indexOf(Commands.TO.cmd());

            if (indexOfFrom == -1 || indexOfTo == -1) {
                throw new DukeException(Views.MISSING_ARGS_ERR_STRING.eng());
            }
            title = input.substring(Commands.EVENT.len(), indexOfFrom);
            String from = input.substring(indexOfFrom, indexOfTo);
            String to = input.substring(indexOfTo);
            returnString[1] = from;
            returnString[2] = to;
        } else {
            throw new DukeException(Views.END_STRING.eng());
        }
        returnString[0] = title;
        return returnString;

    }

    /**
     * Gathers the numbers found from a string
     *
     * @param input string from the user
     * @return int number found from regex
     * @throws DukeException when no number is found
     */
    private static int getNumbers(String input) throws DukeException {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String numberString = matcher.group();
            int number = Integer.parseInt(numberString);
            return number;
        } else {
            throw new DukeException(Views.NO_INT_ERR_STRING.eng());
        }
    }
}
