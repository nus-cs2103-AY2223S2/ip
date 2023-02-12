package rick;

import java.time.LocalDate;
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
 * The main class for parsing commands given to the rick.Rick app via the
 * command line.
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
     * The main access function for creating commands given user input.
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
     * The main function for parsing commands with one word.
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
     * The main function for parsing commands of the following format: `"{command} {param}"`
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
     * The main access point for commands that manipulate
     * tasks in the store.
     *
     * @param cmd The manipulation command.
     * @param param The access index.
     * @return The analysed command.
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
                "An invalid option was used for the %s command, or invalid"
                + "indexes were provided.\n"
                + "Please try again.",
                cmd
        ));
    }

    /**
     * Returns a single command that manipulates individual tasks in the database.
     *
     * @param cmd The command type to return.
     * @param param The index of the task in the database.
     * @return The specific Command instance.
     */
    private static Command simpleManipulateCommand(String cmd, String param) {
        try {
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
        } catch (NumberFormatException e) {
            return new ErrorCommand(new RickException(
                    String.format("An invalid index was provided for the %s command."
                            + " Ensure it is a number!", cmd)
            ));
        }
        assert false; //Execution should not reach this point - this is only
        //called for "mark", "unmark", and "delete"
        return new ErrorCommand("An internal server error occurred");
    }

    /**
     * Returns a Command that manipulates all tasks in the Storage with the
     * provided indexes.
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
     * Returns a Command that manipulates tasks across a range of task indexes.
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
     * Returns a Command that manipulates all Commands on the given date.
     * @param cmd The command type.
     * @param param The date to filter for.
     * @return The list of commands filtered by date, to execute.
     */
    private static Command manipulateDate(String cmd, String param) {
        String date = param.replaceFirst("(-on|-o|--o)(\\s+)*", "");
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
    }

    /**
     * Returns an aggregated Command that manipulates all tasks that contain
     * the specified String.
     *
     * @param cmd The command type.
     * @param param The description to filter for.
     * @return The aggregated Command.
     */
    private static Command manipulateContainsString(String cmd, String param) {
        String searchParam = param.replaceFirst("(-c|--contains|--c)?(\\s+)", "");
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
     * Returns a Command that manipulates all tasks in the Storage with the
     * given command.
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
