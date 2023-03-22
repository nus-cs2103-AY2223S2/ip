package cluck.parser;

import java.util.Objects;

import cluck.commands.Command;
import cluck.commands.DeadlineCommand;
import cluck.commands.DeleteTaskCommand;
import cluck.commands.EventCommand;
import cluck.commands.ExitCommand;
import cluck.commands.FindTaskCommand;
import cluck.commands.InvalidCommand;
import cluck.commands.ListCommand;
import cluck.commands.MarkTaskCommand;
import cluck.commands.NoCommand;
import cluck.commands.ToDoCommand;
import cluck.commands.UnmarkTaskCommand;
import cluck.exceptions.IncorrectArgumentException;
import cluck.exceptions.MissingArgumentException;
import cluck.messages.Messages;

/**
 * Parser class takes user input and parses it into commands
 * that can be executed by Cluck.
 */
public class Parser {
    private static final String MAKE_DEADLINE = "deadline";
    private static final String MAKE_TODO = "todo";
    private static final String MAKE_EVENT = "event";
    private static final String DUE_DATE_FLAG = "/by ";
    private static final String EVENT_START_FLAG = "/from ";
    private static final String EVENT_END_FLAG = "/to ";
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_TASK_COMMAND = "mark";
    private static final String UNMARK_TASK_COMMAND = "unmark";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String FIND_TASK_COMMAND = "find";

    /**
     * Returns true if given string is not a number in String format, false otherwise.
     *
     * @param strNum String of interest.
     * @return Boolean value.
     */
    public static boolean isNotNumeric(String strNum) {
        if (strNum == null) {
            return true;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    /**
     * Takes the string input of the user and converts it into an executable command.
     *
     * @param userInput takes userInput from Ui class and parses it into a command.
     * @return Subtype of Command interface.
     * @throws MissingArgumentException   If either flags, descriptions, or task indexes are missing.
     * @throws IncorrectArgumentException If index given is not a number or out of the task list range.
     */
    public static Command commandFactory(String userInput)
            throws MissingArgumentException, IncorrectArgumentException {
        String[] words = userInput.split(" ");

        switch (words[0]) {
        case EXIT_COMMAND:
            return new ExitCommand();

        case LIST_COMMAND:
            return new ListCommand();

        case MARK_TASK_COMMAND:
            return buildMarkTaskCommand(words);

        case UNMARK_TASK_COMMAND:
            return buildUnmarkTaskCommand(words);

        case DELETE_TASK_COMMAND:
            return buildDeleteTaskCommand(words);

        case MAKE_TODO:
            return buildToDoCommand(words, userInput);

        case MAKE_DEADLINE:
            return buildDeadlineCommand(userInput);

        case MAKE_EVENT:
            return buildEventCommand(userInput);

        case FIND_TASK_COMMAND:
            return buildFindTaskCommand(words, userInput);

        case "":
            return new NoCommand();

        default:
            return new InvalidCommand(userInput);
        }
    }

    private static MarkTaskCommand buildMarkTaskCommand(String[] words)
            throws MissingArgumentException, IncorrectArgumentException {

        assert Objects.nonNull(words) : "Error, empty user input resulted in creation of Mark Task Command.";
        if (words.length < 2) {
            throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
        }

        if (isNotNumeric(words[1])) {
            throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
        }

        int taskIndex = Integer.parseInt(words[1]);
        return new MarkTaskCommand(taskIndex - 1);
    }

    private static UnmarkTaskCommand buildUnmarkTaskCommand(String[] words)
            throws MissingArgumentException, IncorrectArgumentException {

        assert Objects.nonNull(words) : "Error, empty user input resulted in creation of Un-mark Task Command.";
        if (words.length == 1) {
            throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
        }

        if (isNotNumeric(words[1])) {
            throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
        }

        int taskIndex = Integer.parseInt(words[1]);
        return new UnmarkTaskCommand(taskIndex - 1);
    }

    private static DeleteTaskCommand buildDeleteTaskCommand(String[] words)
            throws MissingArgumentException, IncorrectArgumentException {
        assert Objects.nonNull(words) : "Error, empty user input resulted in creation of Un-mark Task Command.";
        if (words.length == 1) {
            throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
        }

        if (isNotNumeric(words[1])) {
            throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
        }

        int taskIndex = Integer.parseInt(words[1]);
        return new DeleteTaskCommand(taskIndex - 1);
    }
    private static ToDoCommand buildToDoCommand(String[] words, String userInput)
            throws MissingArgumentException {
        if (words.length < 2) {
            throw new MissingArgumentException(Messages.MESSAGE_DESCRIPTION_MISSING);
        }

        return new ToDoCommand(userInput.substring(5));
    }

    private static DeadlineCommand buildDeadlineCommand(String userInput)
            throws MissingArgumentException {

        String substring = userInput.substring(9);
        if (!substring.contains(DUE_DATE_FLAG)) {
            throw new MissingArgumentException(Messages.MESSAGE_DUEDATE_FLAG_MISSING);
        }
        if (substring.indexOf(DUE_DATE_FLAG) == 0) {
            throw new MissingArgumentException(Messages.MESSAGE_DESCRIPTION_MISSING);
        }

        String[] fields = substring.split(" " + DUE_DATE_FLAG);
        if (fields.length < 2) {
            throw new MissingArgumentException(Messages.MESSAGE_DATE_MISSING);
        }

        return new DeadlineCommand(fields[0], fields[1]);
    }

    private static EventCommand buildEventCommand(String userInput)
            throws MissingArgumentException {
        String substring = userInput.substring(6);

        if (!substring.contains(EVENT_START_FLAG)) {
            throw new MissingArgumentException(Messages.MESSAGE_START_FLAG_MISSING);
        }

        if (substring.indexOf(EVENT_START_FLAG) == 0) {
            throw new MissingArgumentException(Messages.MESSAGE_DESCRIPTION_MISSING);
        }

        if (!substring.contains(EVENT_END_FLAG)) {
            throw new MissingArgumentException(Messages.MESSAGE_END_FLAG_MISSING);
        }

        String[] eventFields = substring.split("\\s/\\w{2,4}\\s");

        if (eventFields.length < 3) {
            throw new MissingArgumentException(Messages.MESSAGE_DATE_MISSING);
        }
        return new EventCommand(eventFields[0], eventFields[1], eventFields[2]);
    }

    private static FindTaskCommand buildFindTaskCommand(String[] words, String userInput)
            throws MissingArgumentException {
        if (words.length == 1) {
            throw new MissingArgumentException(Messages.MESSAGE_KEYWORD_MISSING);
        }
        String keyword = userInput.substring(5);
        return new FindTaskCommand(keyword);
    }
}
