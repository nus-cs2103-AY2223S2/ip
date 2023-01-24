package parsers;

import commands.*;
import exceptions.DukeException;
import exceptions.IncompleteCommandException;
import exceptions.UnknownCommandException;

/**
 * Represents a parser which takes a command and outputs a Command object for Duke to execute.
 */
public class CommandParser extends Parser {

    final static String BANNER = "____________________________________________________________";

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
            Commands command = Commands.valueOf(commands[0].toUpperCase());
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
            case TODO:
            case DEADLINE:
            case EVENT:
                return new AddTaskCommand(response);
            default:
                throw new UnknownCommandException("Fall to the Dark Side, "
                        + "you must not. Invalid Commands.Commands.Command!", null);
            }
        } catch (DukeException e) {
            throw e;
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException("Fall to the Dark Side, you must not. Invalid Commands.Commands.Command!", null);
        }
    }

    /**
     * Allows Duke to recognise if the particular command requires an index to retrieve a task.
     * @param command command from stdin or command line
     * @return true if the command requires an index, false otherwise
     */
    public static boolean isIndexRequiredCommand(String command) {
        return command.equals("mark")
                || command.equals("unmark")
                || command.equals("delete")
                || command.equals("find");
    }
}
