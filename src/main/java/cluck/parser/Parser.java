package cluck.parser;

import cluck.commands.Command;
import cluck.commands.DeadlineCommand;
import cluck.commands.DeleteTaskCommand;
import cluck.commands.EventCommand;
import cluck.commands.ExitCommand;
import cluck.commands.InvalidCommand;
import cluck.commands.ListCommand;
import cluck.commands.MarkTaskCommand;
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

    /**
     * Returns true if input is a number in string format, false otherwise.
     *
     * @param strNum String of interest.
     * @return boolean value
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
     * @return subtype of Command interface.
     * @throws MissingArgumentException if either flags, descriptions, or task indexes are missing
     * @throws IncorrectArgumentException if index given is not a number or out of the task list range
     */
    public static Command commandFactory(String userInput) throws MissingArgumentException, IncorrectArgumentException {
        String[] words = userInput.split(" ");
        int taskIndex;

        switch (words[0]) {
            case EXIT_COMMAND:
                return new ExitCommand();

            case LIST_COMMAND:
                return new ListCommand();

            case MARK_TASK_COMMAND:
                if (words.length == 1) {
                    throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
                }
                if (isNotNumeric(words[1])) {
                    throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
                }
                taskIndex = Integer.parseInt(words[1]);
                return new MarkTaskCommand(taskIndex - 1);

            case UNMARK_TASK_COMMAND:
                if (words.length == 1) {
                    throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
                }
                if (isNotNumeric(words[1])) {
                    throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
                }
                taskIndex = Integer.parseInt(words[1]);
                return new UnmarkTaskCommand(taskIndex - 1);

            case DELETE_TASK_COMMAND:
                if (words.length == 1) {
                    throw new MissingArgumentException(Messages.MESSAGE_INDEX_MISSING);
                }
                if (isNotNumeric(words[1])) {
                    throw new IncorrectArgumentException(Messages.MESSAGE_INDEX_INVALID);
                }
                taskIndex = Integer.parseInt(words[1]);
                return new DeleteTaskCommand(taskIndex - 1);

            case MAKE_TODO:
                if (words.length < 2) {
                    throw new MissingArgumentException(Messages.MESSAGE_DESCRIPTION_MISSING);
                }
                return new ToDoCommand(userInput.substring(5));

            case MAKE_DEADLINE:
                String body = userInput.substring(9);
                if (!body.contains(DUE_DATE_FLAG)) {
                    throw new MissingArgumentException(Messages.MESSAGE_DUEDATE_FLAG_MISSING);
                }
                String[] fields = body.split(" " + DUE_DATE_FLAG);
                if (fields.length < 2) {
                    throw new MissingArgumentException(Messages.MESSAGE_DATE_MISSING);
                }
                return new DeadlineCommand(fields[0], fields[1]);

            case MAKE_EVENT:
                String substring = userInput.substring(6);
                if (!substring.contains(EVENT_START_FLAG)) {
                    throw new MissingArgumentException(Messages.MESSAGE_START_FLAG_MISSING);
                }
                if (!substring.contains(EVENT_END_FLAG)) {
                    throw new MissingArgumentException(Messages.MESSAGE_END_FLAG_MISSING);
                }
                fields = substring.split("\\s/\\w{2,4}\\s");
                if (fields.length < 3) {
                    throw new MissingArgumentException(Messages.MESSAGE_DATE_MISSING);
                }
                return new EventCommand(fields[0], fields[1], fields[2]);


            default:
                return new InvalidCommand(userInput);
        }
    }
}
