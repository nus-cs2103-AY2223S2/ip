package parsers;

import commands.AddTaskCommand;
import commands.Command;
import commands.Commands;
import commands.DeleteTaskCommand;
import commands.ExitCommand;
import commands.FindTaskCommand;
import commands.ListCommand;
import commands.MarkTaskCommand;
import commands.SortDeadlinesCommand;
import commands.UnmarkTaskCommand;
import exceptions.DukeException;
import exceptions.IncompleteCommandException;
import exceptions.UnknownCommandException;
import formatters.StringUtils;

/**
 * Represents a parser which takes a command and outputs a Command object for Duke to execute.
 */
public class CommandParser extends Parser {

    static final String BANNER = "____________________________________________________________";

    /**
     * Returns a Command object which can allow Duke to execute.
     * @param response string input from stdin or command line
     * @return Command for which Duke can execute
     * @throws UnknownCommandException if command is not recognised by Duke
     */
    public Command parse(String response) throws UnknownCommandException {
        String[] commands = response.split(" ");
        System.out.println(BANNER);
        try {
            if (commands.length < 2 && isIndexRequiredCommand(commands[0])) {
                throw new IncompleteCommandException(String.format("Hrrmmm. Not enough arguments, "
                        + "%s has. Hmm", commands[0]), null);
            }
            Commands command;
            if (commands[0].equalsIgnoreCase("sort")
                    && commands[1].equalsIgnoreCase("deadlines")) {
                command = Commands.SORTDEADLINES;
            } else {
                command = Commands.valueOf(commands[0].toUpperCase());
            }
            switch (command) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkTaskCommand(commands[1]);
            case UNMARK:
                return new UnmarkTaskCommand(commands[1]);
            case DELETE:
                return new DeleteTaskCommand(commands[1]);
            case BYE:
                return new ExitCommand();
            // Fall through allowed as all the commands pertain to task creation.
            case TODO:
            case DEADLINE:
            case EVENT:
                return new AddTaskCommand(response);
            case FIND:
                String keywords = StringUtils.joinString(commands, 1, commands.length - 1);
                return new FindTaskCommand(keywords.trim());
            case SORTDEADLINES:
                return new SortDeadlinesCommand();
            default:
                throw new UnknownCommandException("Fall to the Dark Side, you must not. "
                        + "Invalid Command!", null);
            }
        } catch (DukeException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Fall to the Dark Side, you must not. "
                    + "Invalid Command!", null);
        }
    }

    /**
     * Allows Duke to recognise if the particular command requires an index to retrieve a task.
     * @param command command from stdin or command line
     * @return true if the command requires an index, false otherwise
     */
    public static boolean isIndexRequiredCommand(String command) {
        assert !command.isEmpty();
        return command.equals("mark")
                || command.equals("unmark")
                || command.equals("delete")
                || command.equals("find");
    }
}
