package rick;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import rick.command.Command;
import rick.command.DateFilterCommand;
import rick.command.DeadlineCommand;
import rick.command.DeleteCommand;
import rick.command.ErrorCommand;
import rick.command.EventCommand;
import rick.command.ExitCommand;
import rick.command.FindCommand;
import rick.command.ListCommand;
import rick.command.MarkCommand;
import rick.command.MultiManipulateCommand;
import rick.command.TodoCommand;
import rick.command.UnmarkCommand;
import rick.exceptions.RickException;
import rick.exceptions.RickInvalidCommandException;
import rick.exceptions.RickTaskIndexMissingException;
import rick.task.RickTask;

/**
 * Represents a utility that parses commands given to the rick.Rick app.
 *
 * @author SeeuSim
 *         AY2223-S2 CS2103T
 */
public class Parser {
    private static TaskList ts;

    /**
     * Sets the TaskList instance for this class.
     * @param ts The TaskList instance.
     */
    public static void setTaskList(TaskList ts) {
        Parser.ts = ts;
    }

    /**
     * Parses commands from the given user input, and returns the Command
     * instances that can be executed.
     *
     * @param line The user input.
     * @return The parsed command.
     */
    public static Command parse(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length == 1) {
            return simpleCommand(tokens[0]);
        }

        return twoArgCommand(tokens[0], tokens[1]);
    }

    /**
     * Parses commands with one word, and returns the Command instance to
     * execute, given the command slug.
     *
     * @param cmd The command given.
     * @return The analysed command.
     */
    private static Command simpleCommand(String cmd) {
        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand();
        case "deadline":
            return new DeadlineCommand();
        case "event":
            return new EventCommand();
        case "find":
            return new ErrorCommand(
                    "An empty search was attempted. Valid Usage: find {search term}"
            );
        default:
            if (cmd.matches("(mark|unmark|delete)")) {
                return new ErrorCommand(new RickTaskIndexMissingException(cmd));
            } else {
                return new ErrorCommand(new RickInvalidCommandException());
            }
        }
    }

    /**
     * Parses commands with the format "{command} {param}", and returns the
     * Command instance to execute, given the command and the parameter.
     *
     * @param cmd The command given.
     * @param param The parameter provided to the command.
     * @return The analysed command.
     */
    public static Command twoArgCommand(String cmd, String param) {
        switch (cmd) {
        case "todo":
            return new TodoCommand(param);
        case "deadline":
            return new DeadlineCommand(param);
        case "event":
            return new EventCommand(param);
        case "tasks":
            return new DateFilterCommand(param);
        case "find":
            return new FindCommand(param);
        default:
            if (cmd.matches("(mark|unmark|delete)")) {
                return manipulateCommand(cmd, param);
            }
            return new ErrorCommand(new RickInvalidCommandException());
        }
    }

    /**
     * Parses commands that manipulate tasks in the Storage, and returns the
     * Command instance to execute, given a command and an access index string.
     *
     * @param cmd The manipulation command.
     * @param param The access index.
     * @return The generated Command Instance.
     */
    private static Command manipulateCommand(String cmd, String param) {
        String singleIndex = "\\d+";
        String multipleIndex = "^\\d+(?:\\s+\\d+)*$";
        String rangeIndex = "^\\d+(\\s+)*(-|to)(\\s+)*\\d+$";
        String dateOnFilter = "^(/on|-o|--o)?(\\s+)(.)*";
        String containsFilter = "^(/contains|-c|--c)?(\\s+)(.)*";
        String allFilter = "(/all|-a|--a)(.)*";
        if (param.matches(singleIndex)) {
            return simpleManipulateCommand(cmd, param);
        }
        if (param.matches(multipleIndex)) {
            return manipulateIndexes(cmd, param.split("\\s+"));
        }
        if (param.matches(rangeIndex)) {
            return manipulateRange(cmd, param);
        }
        if (param.matches(dateOnFilter)) {
            return manipulateDate(cmd, param);
        }
        if (param.matches(containsFilter)) {
            return manipulateContainsString(cmd, param);
        }
        if (param.matches(allFilter)) {
            return manipulateAll(cmd);
        }
        return new ErrorCommand(String.format(
                "An invalid option was used for the %s command, or invalid "
                + "indexes were provided.\n"
                + "Please try again.",
                cmd
        ));
    }

    /**
     * Generates and returns a single command that manipulates individual
     * tasks in the database, given a command type and a task index.
     *
     * @param cmd The command type to return.
     * @param param The index of the task in the database.
     * @return The executable Command instance.
     */
    private static Command simpleManipulateCommand(String cmd, String param) {
        int idx = Integer.parseInt(param);
        switch (cmd) {
        case "mark":
            return new MarkCommand(idx);
        case "unmark":
            return new UnmarkCommand(idx);
        case "delete":
            return new DeleteCommand(idx);
        default:
            break;
        }
        //Execution should not reach this point - this is only
        //called for "mark", "unmark", and "delete"
        assert false;
        return new ErrorCommand("An internal server error occurred");
    }

    /**
     * Generates and returns a Command that manipulates all tasks in the
     * Storage with the provided indexes, given a command type and a sequence
     * of indexes.
     *
     * @param cmd The command type.
     * @param indexes The indexes to manipulate. Guaranteed to all be valid
     *                Integers, due to Regex Matching.
     * @return The Command to execute.
     */
    private static Command manipulateIndexes(String cmd, String... indexes) {
        List<Integer> distinctIndices = Arrays
                .stream(indexes)
                .distinct()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        ArrayList<Integer> finalIndices = new ArrayList<>();
        int counter = 0;
        for (Integer distinctIndex : distinctIndices) { //1-indexed
            finalIndices.add(
                    distinctIndex - (cmd.equals("delete") ? counter : 0)
            );
            counter++;
        }
        return new MultiManipulateCommand(finalIndices, cmd);
    }

    /**
     * Generates and returns a Command that manipulates tasks across a range
     * of task indexes, given a command type and a range of indexes.
     *
     * @param cmd The command type.
     * @param param The index range.
     * @return The resultant command that executes all Commands over the range
     *         of indexes.
     */
    private static Command manipulateRange(String cmd, String param) {
        String[] rangeIndexes = param.split("(\\s+)*(-|to)(\\s+)*");
        try {
            int startIndex = Integer.parseInt(rangeIndexes[0]);
            int endIndex = Integer.parseInt(rangeIndexes[1]);
            ArrayList<Integer> finalIndices = new ArrayList<>();

            if (startIndex <= endIndex) {
                int counter = 0;
                for (int i = startIndex; i <= endIndex; i++) { //1-indexed
                    finalIndices.add(
                            i - (cmd.equals("delete") ? counter : 0)
                    );
                    counter++;
                }
                return new MultiManipulateCommand(finalIndices, cmd);
            }
            //Invalid Index Range
            return new ErrorCommand(
                    String.format(
                            "An invalid range of indexes was entered for the %s command.\n",
                            cmd)
                    + "Please ensure the start index is lesser than or equal "
                    + "to the end index."
            );
        } catch (NumberFormatException e) {
            return new ErrorCommand(new RickException(
                    String.format("An invalid index was provided for the %s command."
                            + " Ensure it is a number!", cmd)
            ));
        }
    }

    /**
     * Generates and returns a Command that manipulates all Commands on the
     * given date, with the given command type.
     *
     * @param cmd The command type.
     * @param param The date to filter for.
     * @return The list of commands filtered by date, to execute.
     */
    private static Command manipulateDate(String cmd, String param) {
        String date = param.replaceFirst("(/on|-o|--o)(\\s+)*", "");
        try {
            LocalDate dateTime = RickUtils.parseDate(date);
            List<RickTask> tasks = Parser.ts
                    .filter(rickTask -> true)
                    .collect(Collectors.toList());
            ArrayList<Integer> finalIndices = new ArrayList<>();

            int counter = 0;
            for (int i = 0; i < tasks.size(); i++) { //1-indexed
                if (tasks.get(i).isOnDate(dateTime)) {
                    finalIndices.add(
                            i + 1 - (cmd.equals("delete") ? counter : 0)
                    );
                    counter++;
                }
            }
            if (finalIndices.size() == 0) {
                return new ErrorCommand(
                        "Hooray! No tasks occur on that date."
                );
            }
            return new MultiManipulateCommand(finalIndices, cmd);
        } catch (DateTimeParseException e) {
            return new ErrorCommand("An invalid date was supplied.\n"
                + "Ensure it is of the format d/m/yy.\n"
                + "Example: 2/2/23 OR 02/02/23"
            );
        }
    }

    /**
     * Generates and returns an aggregated Command that manipulates all tasks
     * that contain the specified String, given a command type.
     *
     * @param cmd The command type.
     * @param param The description to filter for.
     * @return The aggregated Command.
     */
    private static Command manipulateContainsString(String cmd, String param) {
        String searchParam = param.replaceFirst("(/contains|-c|--c)?(\\s+)", "");
        List<RickTask> tasks = Parser.ts
                .filter(rickTask -> true)
                .collect(Collectors.toList());
        ArrayList<Integer> finalIndices = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) { //1-indexed
            if (tasks.get(i).containsTerm(searchParam)) {
                finalIndices.add(
                        i + 1 - (cmd.equals("delete") ? counter : 0)
                );
                counter++;
            }
        }
        if (finalIndices.size() == 0) {
            return new ErrorCommand(
                    "No tasks contain that search term. Please try again."
            );
        }
        return new MultiManipulateCommand(finalIndices, cmd);
    }

    /**
     * Generates and returns a Command that manipulates all tasks in the
     * Storage with the given command.
     *
     * @param cmd The command type.
     * @return The Command to execute.
     */
    private static Command manipulateAll(String cmd) {
        long size = Parser.ts.filter(rickTask -> true).count();
        if (size == 0) {
            return new ErrorCommand(
                    "The storage is empty. Please try adding some tasks."
            );
        }
        ArrayList<Integer> finalIndices = new ArrayList<>();
        int amount = Integer.parseInt(String.valueOf(size));
        int counter = 0;
        for (int i = 1; i <= amount; i++) { //1-indexed
            finalIndices.add(
                    i - (cmd.equals("delete") ? counter : 0)
            );
            counter++;
        }
        return new MultiManipulateCommand(finalIndices, cmd);
    }
}
