package berry.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import berry.command.AddTaskCommand;
import berry.command.Command;
import berry.command.DeleteCommand;
import berry.command.ExitCommand;
import berry.command.FindCommand;
import berry.command.ListCommand;
import berry.command.MarkCommand;
import berry.command.UnmarkCommand;
import berry.exception.BerryException;
import berry.exception.EmptyClauseException;
import berry.exception.EmptyDescriptionException;
import berry.exception.MissingClauseException;
import berry.exception.UnknownCommandException;
import berry.task.Deadline;
import berry.task.Event;
import berry.task.Todo;

/**
 * Parses user input into {@code Commands}.
 */
public class Parser {
    private static String PREFIX_TAG = "#";
    private static String PREFIX_BY = "/by";
    private static String PREFIX_FROM = "/from";
    private static String PREFIX_TO = "/to";

    private static int INDEX_COMMAND_TYPE = 0;
    private static int INDEX_DESCRIPTION = 1;

    /**
     * Types of commands.
     */
    private enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE
    }

    /**
     * Parses user input into a {@code Command}.
     *
     * @param userInput full user input string
     * @return command based on the user input
     * @throws BerryException if the given string cannot be parsed
     */
    public static Command parseInput(String userInput) throws BerryException {
        assert userInput != null : "Input cannot be null";
        assert userInput.length() > 0 : "Input must contain at least one element";

        // Validates for present and valid arguments, else throws BerryException.
        CommandType commandType = validateCommandType(userInput);
        validateCommand(commandType, userInput);

        return parseCommands(commandType, userInput);
    }

    /**
     * Parses the valid input command and returns a corresponding {@code Command}.
     *
     * @param commandType type of command given by user
     * @param userInput the full user input
     * @return a corresponding {@code Command} based on user input
     * @throws BerryException raised when there are invalid commands given
     */
    private static Command parseCommands(CommandType commandType,
                                         String userInput) throws BerryException {
        if (isIndexCommands(commandType)) {
            return parseIndexCommands(commandType, userInput);
        }

        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case TODO:
            return parseTodoCommand(userInput);
        case DEADLINE:
            return parseDeadlineCommand(userInput);
        case EVENT:
            return parseEventCommand(userInput);
        case FIND:
            return parseFindCommand(userInput);
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses and returns a {@code FindCommand}.
     *
     * @param userInput full user input string
     * @return a corresonding {@code FindCommand}
     */
    private static FindCommand parseFindCommand(String userInput) {
        String[] keywords = userInput.split(" ").length > 2
                ? userInput.split(" ", 2)[1].split(" ") // has more than 1 keyword
                : new String[] {userInput.split(" ")[1]}; // only one keyword
        return new FindCommand(keywords);
    }

    /**
     * Parses and returns a {@code AddTaskCommand} based on the event command given by user.
     *
     * @param userInput full user input string
     * @return a corresponding {@code AddTaskCommand}
     */
    private static AddTaskCommand parseEventCommand(String userInput) throws BerryException {
        HashSet<String> tags;
        String description;
        String stringOfTags;

        description = userInput.split(" ", 2)[1].split(PREFIX_FROM)[0].trim();
        String fromDate = userInput.split(PREFIX_FROM)[1].split(PREFIX_TO)[0].trim();
        String toDate = containsTag(userInput)
                ? userInput.split(PREFIX_TO)[1].split(PREFIX_TAG)[0].trim()
                : userInput.split(PREFIX_TO)[1].trim();

        // If there is no tags. Exception thrown if description contains tags.
        if (!containsTag(userInput)) {
            return new AddTaskCommand(new Event(description, fromDate, toDate));
        }

        // Contain tags
        stringOfTags = userInput.split(toDate)[1].trim();
        tags = splitTags(stringOfTags);
        return new AddTaskCommand(new Event(description, fromDate, toDate, tags));
    }

    /**
     * Parses and returns a {@code AddTaskCommand} based on the deadline command given by user.
     *
     * @param userInput full user input string
     * @return a corresponding {@code AddTaskCommand}
     */
    private static AddTaskCommand parseDeadlineCommand(String userInput) throws BerryException {
        String stringOfTags;
        HashSet<String> tags;
        String description;

        description = userInput.split(" ", 2)[1].split(PREFIX_BY)[0].trim();
        String byDate = containsTag(userInput)
                ? userInput.split(PREFIX_BY)[1].split(PREFIX_TAG)[0].trim()
                : userInput.split(PREFIX_BY)[1].trim();
        // If there is no tags. Exception thrown if description contains tags.
        if (!containsTag(userInput)) {
            return new AddTaskCommand(new Deadline(description, byDate));
        }

        // Contain tags
        stringOfTags = userInput.split(byDate)[1];
        tags = splitTags(stringOfTags);
        return new AddTaskCommand(new Deadline(description, byDate, tags));
    }

    /**
     * Parses and returns a {@code AddTaskCommand} based on the todo command given by user.
     *
     * @param userInput full user input string
     * @return a corresponding {@code AddTaskCommand}
     */
    private static AddTaskCommand parseTodoCommand(String userInput) throws BerryException {
        String description;
        String stringOfTags;
        HashSet<String> tags;

        description = containsTag(userInput)
                ? userInput.split(" ", 2)[1].split(PREFIX_TAG)[0].trim()
                : userInput.split(" ", 2)[1].trim();

        // If there is no tags. Exception thrown if description contains "#"
        if (!containsTag(userInput)) {
            return new AddTaskCommand(new Todo(description));
        }

        // Contain tags
        stringOfTags = userInput.split("#")[1];

        tags = splitTags(stringOfTags);
        return new AddTaskCommand(new Todo(description, tags));
    }

    /**
     * Parses and returns a {@code Command} based on a {@code IndexCommand} given by user.
     * An {@code IndexCommand} represents a command which takes in an index as argument and include
     * {@code MarkCommand}, {@code UnmarkCommand}, {@code DeleteCommand}.
     *
     * @param userInput full user input string
     * @return a corresponding {@code FindCommand}
     */
    private static Command parseIndexCommands(CommandType commandType, String userInput) {
        int index = Integer.parseInt(userInput.split(" ")[INDEX_DESCRIPTION]);
        switch (commandType) {
        case MARK:
            return new MarkCommand(index);
        case UNMARK:
            return new UnmarkCommand(index);
        case DELETE:
            return new DeleteCommand(index);
        default:
            assert false : "code should not reach this line";
        }
        return null;
    }

    /**
     * Checks if the input string given contains tags, or '#'.
     *
     * @param descriptionAndMaybeTags user input without the initial command type. It will contain a description
     *                                and may contain tags
     * @return true if there are tags
     * @throws BerryException thrown when '#' appears in the description of the code. The current version does not
     *                          support description containing '#' in any way.
     */
    private static boolean containsTag(String descriptionAndMaybeTags) throws BerryException {
        if (!descriptionAndMaybeTags.contains(PREFIX_TAG)) { // there is only one word after the command
            return false;
        }

        // check if "#" is contained in the description, if yes, throw error.
        if (descriptionAndMaybeTags.split(" " + PREFIX_TAG, 2)[0].contains(PREFIX_TAG)) {
            throw new BerryException("Your description should not contain '#'!");
        }
        return true;
    }

    /**
     * Takes in a string of tags separated by '#' and returns a hash set of tags.
     *
     * @param stringOfTags given by user input to represent tags seperated by '#'
     * @return a hash set of tags
     */
    private static HashSet<String> splitTags(String stringOfTags) {
        ArrayList<String> tags = new ArrayList<String>(List.of(stringOfTags.trim().split("#")));
        return new HashSet<String>(tags);
    }

    /**
     * Validates the command type given by the user.
     *
     * @param userInput full user input string
     * @return a {@code CommandType} based on user input
     * @throws UnknownCommandException when the {@code CommandType} is not valid
     */
    private static CommandType validateCommandType(String userInput) throws UnknownCommandException {
        String command;

        if (userInput.contains(" ") && userInput.split(" ").length > 1) { // if there are more than 1 word
            command = userInput.split(" ")[INDEX_COMMAND_TYPE]; // obtain the command type from first word
        } else {
            command = userInput.trim();
        }

        try {
            return CommandType.valueOf(command.toUpperCase()); //check if its a valid command type
        } catch (IllegalArgumentException e) {
            throw new UnknownCommandException();
        }
    }

    /**
     * Checks if the given arguments are valid for its task to be created.
     * Throws {@code BerryException} if the given command is not valid.
     *
     * @param commandType is the type of the task
     * @param userInput is the full user input string
     * @throws BerryException if the given string has missing or incomplete arguments
     */
    private static void validateCommand(CommandType commandType, String userInput) throws BerryException {
        if (isCommandWithNoArgs(commandType)) {
            validateCommandWithNoArgs(userInput);
            return;
        }
        validateCommandWithArgs(commandType, userInput);
    }

    // With args include: LIST, BYE
    private static void validateCommandWithNoArgs(String userInput) throws BerryException {
        if (userInput.contains(" ") && userInput.split(" ").length > 1) {
            throw new BerryException("Too many arguments for LIST and BYE commands.");
        }
    }

    // With args include: MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND
    private static void validateCommandWithArgs(CommandType commandType,
                                                String userInput) throws BerryException {
        assert !isCommandWithNoArgs(commandType) : "commands should not be of type bye or list";

        if (isIndexCommands(commandType)) {
            validateIndexCommands(commandType, userInput);
        } else if (isTaskOrFindCommands(commandType)) {
            validateTaskFindCommands(commandType, userInput);
        } else {
            throw new UnknownCommandException(); // Not a command Berry knows how to handle
        }
    }

    private static void validateIndexCommands(CommandType commandType,
                                              String userInput) throws BerryException {
        String command = commandType.toString().toLowerCase();
        if (userInput.endsWith(command)
               || userInput.split(" ").length <= 1) {
            throw new BerryException("Too many arguments for LIST and BYE commands!");
        }

        try {
            int placeholderToCheckIfInt = Integer.parseInt(userInput.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new BerryException("your given index clause is not an integer :<");
        }
    }

    private static void validateTaskFindCommands(CommandType commandType,
                                                 String userInput) throws BerryException {
        validateDescription(commandType, userInput);

        switch (commandType) {
        case TODO:
            // This case is left empty as the only clause - description, is checked by validateDescription.
            break;
        case DEADLINE:
            checkEmptyOrMissingSingleDateClause(userInput, PREFIX_BY);
            break;
        case EVENT:
            checkEmptyOrMissingSingleDateClause(userInput, PREFIX_FROM);
            checkEmptyOrMissingSingleDateClause(userInput, PREFIX_TO);

            // check missing clause for /from when /to also exists.
            // '/to' has been checked by {@code checkEmptyOrMissingSingleClause(userInput, PREFIX_TO)}
            String fromClause = userInput.split(PREFIX_FROM)[1].split(PREFIX_TO)[0];
            if (fromClause.trim().isBlank() || fromClause.startsWith("#")) {
                throw new EmptyClauseException(PREFIX_FROM);
            }
            break;
        case FIND:
            // This case is left empty as the only clause - keyword, is checked by validateDescription.
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void checkEmptyOrMissingSingleDateClause(String userInput, String prefix) throws BerryException {
        if (!userInput.contains(prefix)) {
            throw new MissingClauseException(prefix);
        } else if (userInput.trim().endsWith(prefix) // Empty by clause
                || userInput.split(prefix)[1].trim().startsWith("#")) { // Empty by clause - followed by tags
            throw new EmptyClauseException(prefix);
        }
    }

    /**
     * Checks if description is valid. An invalid description is empty, or includes "#".
     *
     * @param commandType the type of command as interpreted by the user input.
     * @param userInput User input.
     * @throws BerryException thrown when description is invalid.
     */
    private static void validateDescription(CommandType commandType,
                                            String userInput) throws BerryException {
        assert commandType == CommandType.TODO
                || commandType == CommandType.DEADLINE
                || commandType == CommandType.EVENT : "Only todo, deadline and event has a description clause";

        String command = commandType.toString().toLowerCase();
        if (userInput.trim().endsWith(command)) {
            throw new EmptyDescriptionException(command);
        }

        String description = userInput.split(" ", 2)[1]; // remove command from userInput

        if (description.isBlank() || description.trim().startsWith("#")) {
            throw new EmptyDescriptionException(command);
        }

        if (commandType == CommandType.DEADLINE && description.contains(PREFIX_BY)) {
            description = description.split("/by")[0];
        } else if (commandType == CommandType.EVENT && description.contains(PREFIX_FROM)) {
            description = description.split("/from")[0];
        }

        if (description.isBlank()) {
            throw new EmptyDescriptionException(command);
        }
    }

    private static boolean isCommandWithNoArgs(CommandType commandType) {
        return commandType == CommandType.LIST || commandType == CommandType.BYE;
    }

    private static boolean isIndexCommands(CommandType commandType) {
        return commandType == CommandType.MARK
                || commandType == CommandType.UNMARK
                || commandType == CommandType.DELETE;
    }

    private static boolean isTaskOrFindCommands(CommandType commandType) {
        return commandType == CommandType.TODO
                || commandType == CommandType.DEADLINE
                || commandType == CommandType.EVENT
                || commandType == CommandType.FIND;
    }
}
