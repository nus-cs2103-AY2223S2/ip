package parser;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EndCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.MarkCommand;
import commands.ReadCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import exceptions.InvalidInputException;
import exceptions.NoDateException;
import exceptions.NoDescriptionException;

/**
 * This class is used to parse the user input into the correct command.
 */
public class Parser {

    /**
     * The enum for all the commands available.
     */
    protected static enum CommandList {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND;

        /**
         * Find the command based on the user input.
         * @param input The user input.
         * @return The command to be initialised.
         */
        public static CommandList findCommand(String input) {
            for (CommandList command : CommandList.values()) {
                if (command.name().equalsIgnoreCase(input)) {
                    return command;
                }
            }
            return null;
        }
    }

    /**
     * Convert the string into the correct command.
     * @param userInput The user input.
     * @return The command.
     */
    public static Command parseCommand(String userInput) {
        CommandList command = CommandList.findCommand(userInput.split(" ")[0]);
        if (command == null) {
            throw new InvalidInputException(null);
        }
        switch (command) {
        case LIST:
            return new ReadCommand();
        case BYE:
            return new EndCommand();
        case MARK:
            checkForDescription(userInput);
            return new MarkCommand(userInput);
        case UNMARK:
            checkForDescription(userInput);
            return new UnmarkCommand(userInput);
        case TODO:
            checkForDescription(userInput);
            return new TodoCommand(userInput);
        case DEADLINE:
            checkForDescription(userInput);
            checkForDate(userInput);
            return new DeadlineCommand(userInput);
        case EVENT:
            checkForDescription(userInput);
            checkForDate(userInput);
            return new EventCommand(userInput);
        case DELETE:
            checkForDescription(userInput);
            return new DeleteCommand(userInput);
        case FIND:
            checkForDescription(userInput);
            return new FindCommand(userInput);
        default:
            throw new InvalidInputException(null);
        }
    }

    /**
     * Checks to ensure that there is a description.
     * @param message The message to be checked.
     * @throws NoDescriptionException Throws if no description is found.
     */
    private static void checkForDescription(String message) throws NoDescriptionException {
        String[] temp = message.split(" ");
        if (temp.length == 1) {
            throw new NoDescriptionException(temp[0], null);
        }
    }

    /**
     * Checks to ensure that there is/are date(s).
     * @param message The message to be checked.
     * @throws NoDateException Throws if no date(s) is/are found.
     */
    private static void checkForDate(String message) throws NoDateException {
        if (!message.contains("/by") && !(message.contains("/to") && message.contains("/from"))) {
            throw new NoDateException(message.split(" ")[0], null);
        }
    }
}
