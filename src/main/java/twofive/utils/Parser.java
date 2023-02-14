package twofive.utils;

import twofive.command.ByeCommand;
import twofive.command.Command;
import twofive.command.DeadlineCommand;
import twofive.command.DeleteCommand;
import twofive.command.DueDateCommand;
import twofive.command.EventCommand;
import twofive.command.FindCommand;
import twofive.command.ListCommand;
import twofive.command.MarkCommand;
import twofive.command.TagAddCommand;
import twofive.command.TagListCommand;
import twofive.command.ToDoCommand;
import twofive.command.UnmarkCommand;
import twofive.exception.EmptyDateException;
import twofive.exception.EmptyDeadlineException;
import twofive.exception.EmptyDescriptionException;
import twofive.exception.EmptyEndTimeException;
import twofive.exception.EmptyKeywordException;
import twofive.exception.EmptyStartTimeException;
import twofive.exception.EmptyTagException;
import twofive.exception.EmptyTasknumException;
import twofive.exception.InvalidCommandException;
import twofive.exception.MissingArgumentException;

/**
 * Parses the contents of a given command to obtain the action
 * intended to be performed by the user.
 */
public class Parser {
    /**
     * Ensures a valid task number is provided when performing an action on a specific task.
     *
     * @param input The full command entered by the user.
     * @return int representing the task number.
     * @throws EmptyTasknumException If no task number is provided by the user.
     * @throws NumberFormatException If the user enters something other than an integer.
     */
    private static int validateTaskNum(String input) throws EmptyTasknumException, NumberFormatException {
        String[] taskNumSplit = input.split(" ");
        if (taskNumSplit.length <= 1 || taskNumSplit[1].trim().equals("")) {
            throw new EmptyTasknumException();
        } else {
            int taskNum = Integer.parseInt(taskNumSplit[1]) - 1;
            return taskNum;
        }
    }

    /**
     * Ensures a valid description is provided when adding a new task.
     *
     * @param input The full command entered by the user.
     * @param commandWord The command keyword.
     * @return String containing the description of the task.
     * @throws EmptyDescriptionException If no description is provided by the user.
     */
    private static String validateDescription(String input, String commandWord) throws EmptyDescriptionException {
        String[] descriptionSplit = input.split(commandWord);
        if (descriptionSplit.length <= 1 || descriptionSplit[1].trim().equals("")) {
            //If task description is empty
            throw new EmptyDescriptionException(commandWord);
        }
        return descriptionSplit[1].trim();
    }

    /**
     * Parses input from user into different types of command depending
     * on the first word of the input and other arguments present.
     *
     * @return Command according to user input.
     * @throws EmptyTasknumException If a task number is not provided in relevant commands.
     * @throws EmptyDescriptionException If the description is absent in the user's input.
     * @throws MissingArgumentException If one or more arguments are missing in the user's input.
     * @throws EmptyStartTimeException If the start time is absent in an event command.
     * @throws EmptyEndTimeException If the end time is absent in an event command.
     * @throws EmptyDeadlineException If the deadline is absent in a deadline command.
     * @throws EmptyDateException If the date is missing in a due command.
     * @throws InvalidCommandException If the command entered cannot be recognized properly.
     */
    public static Command parse(String command)
            throws EmptyTasknumException, EmptyDescriptionException,
            MissingArgumentException, EmptyStartTimeException, EmptyEndTimeException, EmptyDeadlineException,
            EmptyDateException, InvalidCommandException, EmptyKeywordException, EmptyTagException {
        String commandWord = command.split(" ")[0].trim();

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(validateTaskNum(command));
        case "unmark":
            return new UnmarkCommand(validateTaskNum(command));
        case "mark":
            return new MarkCommand(validateTaskNum(command));
        case "todo":
            return new ToDoCommand(validateDescription(command, commandWord));
        case "deadline":
            return parseDeadlineCommand(validateDescription(command, commandWord));
        case "event":
            return parseEventCommand(validateDescription(command, commandWord));
        case "due":
            return parseDueDateCommand(command, commandWord);
        case "find":
            return parseFindCommand(command, commandWord);
        case "addtag":
            return parseTagAddCommand(command, commandWord);
        case "listtag":
            return parseTagListCommand(command, commandWord);
        case "bye":
            return new ByeCommand();
        default:
            throw new InvalidCommandException();
        }
    }

    private static DeadlineCommand parseDeadlineCommand(String descriptionSplit)
            throws MissingArgumentException, EmptyDeadlineException {
        if (!descriptionSplit.contains(("/by"))) {
            // If /by argument not used
            throw new MissingArgumentException("/by");
        }
        String[] deadlineSplit = descriptionSplit.split("/by");
        if (deadlineSplit.length <= 1 || deadlineSplit[1].trim().equals("")) {
            // If deadline is not given
            throw new EmptyDeadlineException();
        }
        String taskDescription = deadlineSplit[0].trim();
        String deadlineString = deadlineSplit[1].trim();
        return new DeadlineCommand(taskDescription, deadlineString);
    }

    private static EventCommand parseEventCommand(String descriptionSplit)
            throws MissingArgumentException, EmptyStartTimeException, EmptyEndTimeException {
        if (!descriptionSplit.contains(("/from"))) {
            // If /from argument not used
            throw new MissingArgumentException("/from");
        } else if (!descriptionSplit.contains(("/to"))) {
            // If /to argument not used
            throw new MissingArgumentException("/to");
        }
        String[] startTimeSplit = descriptionSplit.split("/from");
        if (startTimeSplit.length <= 1 || startTimeSplit[1].trim().equals("")) {
            // If start time is not given
            throw new EmptyStartTimeException();
        }
        String[] endTimeSplit = startTimeSplit[1].split("/to");
        if (endTimeSplit[0].trim().equals("")) {
            throw new EmptyStartTimeException();
        } else if (endTimeSplit.length <= 1 || endTimeSplit[1].trim().equals("")) {
            // If end time is not given
            throw new EmptyEndTimeException();
        }
        String taskDescription = startTimeSplit[0].trim();
        String startTimeString = endTimeSplit[0].trim();
        String endTimeString = endTimeSplit[1].trim();
        return new EventCommand(taskDescription, startTimeString, endTimeString);
    }

    private static DueDateCommand parseDueDateCommand(String command, String commandWord) throws EmptyDateException {
        String[] dueSplit = command.split(commandWord);
        if (dueSplit.length <= 1 || dueSplit[1].trim().equals("")) {
            // If task description is empty
            throw new EmptyDateException();
        }
        String dueDateString = dueSplit[1].trim();
        return new DueDateCommand(dueDateString);
    }

    private static FindCommand parseFindCommand(String command, String commandWord) throws EmptyKeywordException {
        String[] keywordSplit = command.split(commandWord);
        if (keywordSplit.length <= 1 || keywordSplit[1].trim().equals("")) {
            // if no keyword provided
            throw new EmptyKeywordException();
        }
        String keyword = keywordSplit[1].trim();
        return new FindCommand(keyword);
    }

    private static TagAddCommand parseTagAddCommand(String command, String commandWord)
            throws EmptyTagException, EmptyTasknumException, MissingArgumentException {
        int taskNum = validateTaskNum(command);
        if (!command.contains(("/tag"))) {
            // If /tag argument not used
            throw new MissingArgumentException("/tag");
        }
        String[] tagSplit = command.split("/tag");
        if (tagSplit.length <= 1) {
            // if no keyword provided
            throw new EmptyTagException();
        }
        String tag = tagSplit[1].trim();
        return new TagAddCommand(taskNum, tag);
    }

    private static TagListCommand parseTagListCommand(String command, String commandWord) throws EmptyTagException {
        String[] tagSplit = command.split(commandWord);
        if (tagSplit.length <= 1 || tagSplit[1].trim().equals("")) {
            // if no tag provided
            throw new EmptyTagException();
        }
        String tag = tagSplit[1].trim();
        return new TagListCommand(tag);
    }
}
