package duke;

import java.util.Arrays;
import java.util.HashSet;
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
                int[] taskNumbers = subtractInt(getNumbers(input), 1);
                return new MarkCommand(taskNumbers);
            } else if (input.startsWith(Commands.UNMARK.cmd())) {
                int[] taskNumbers = subtractInt(getNumbers(input), 1);
                return new UnmarkCommand(taskNumbers);
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
                int[] taskNumbers = subtractInt(getNumbers(input), 1);
                return new DeleteCommand(taskNumbers);
            } else if (input.startsWith(Commands.FIND.cmd())) {
                String query = input.substring(Commands.FIND.len());
                return new FindCommand(query);
            } else {
                throw new DukeException(Views.UNKNOWN_CMD_ERR_STRING.eng());
            }
        }
    }

    /**
     * Centralised parser to parse Tasks input and sort out the specific args for
     * the 3 types of task: Todo Deadline Event
     *
     * @param input
     * @return String[] to be added todo deadline and event objects/command
     * @throws DukeException
     */
    public static String[] handleTask(String input) throws DukeException {
        String[] returnString = new String[3];
        String title;
        if (input.startsWith(Commands.TODO.cmd())) {
            title = input.substring(Commands.TODO.len());
        } else if (input.startsWith(Commands.DEADLINE.cmd())) {
            int indexOfBy = input.indexOf(Commands.BY.cmd());
            assert indexOfBy != -1 : Views.MISSING_ARGS_ERR_STRING.eng();
            title = input.substring(Commands.DEADLINE.len(), indexOfBy);
            String deadline = input.substring(indexOfBy);
            returnString[1] = deadline;
        } else if (input.startsWith(Commands.EVENT.cmd())) {
            int indexOfFrom = input.indexOf(Commands.FROM.cmd());
            int indexOfTo = input.indexOf(Commands.TO.cmd());
            assert indexOfFrom != -1 && indexOfFrom != -1 : Views.MISSING_ARGS_ERR_STRING.eng();
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
    private static int getNumber(String input) throws DukeException {
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

    /**
     * Gathers the numbers found from a string
     *
     * @param input string from the user
     * @return int number found from regex
     * @throws DukeException when no number is found
     */
    private static int[] getNumbers(String input) throws DukeException {
        String[] inputArray = TaskList.removeEmptyStrings(input.split(" "));

        // Use hash set to ensure no duplicated numbers
        HashSet<Integer> results = new HashSet<Integer>();

        for (String inputString : inputArray) {
            try {
                results.add(getNumber(inputString));
            } catch (DukeException e) {
                // Particular string is not int, moving on to next
            }
        }
        if (results.size() != 0) {
            // Convert it back to int array
            int[] converted = results.stream().mapToInt(Integer::intValue).toArray();
            Arrays.sort(converted);
            return converted;
        } else {
            throw new DukeException(Views.NO_INT_ERR_STRING.eng());
        }
    }

    private static int[] subtractInt(int[] input, int subtractAmt) {
        int[] results = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int subtracted = input[i] - subtractAmt;
            assert subtracted >= 0 : Views.OUT_RANGE_ERR_STRING.eng();
            results[i] = subtracted;
        }
        return results;
    }
}
