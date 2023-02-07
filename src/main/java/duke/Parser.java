package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TerminateCommand;
import duke.command.ToDoCommand;
import duke.command.UnknownCommand;
import duke.command.UnmarkCommand;


/**
 * Parser handles making sense of user command.
 */
public class Parser {
    /**
     * Parses command that user inputs.
     * @param userInput full user input string
     * @param tasklist current tasklist in Duke
     * @return Command
     */
    public static Command parseCommand(String userInput, TaskList tasklist) {
        String[] arrOfStr = userInput.split(" ");
        String commandWord = arrOfStr[0];
        int taskIndex;
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            taskIndex = getTaskIndex(userInput);
            return new MarkCommand(taskIndex);
        case UnmarkCommand.COMMAND_WORD:
            taskIndex = getTaskIndex(userInput);
            return new UnmarkCommand(taskIndex);
        case DeleteCommand.COMMAND_WORD:
            taskIndex = getTaskIndex(userInput);
            return new DeleteCommand(taskIndex);
        case TerminateCommand.COMMAND_WORD:
            return new TerminateCommand();
        case ToDoCommand.COMMAND_WORD:
            try {
                String desc = getDescToDo(userInput);
                return new ToDoCommand(desc);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
                break;
            }
        case DeadlineCommand.COMMAND_WORD:
            try {
                String desc = getDescDeadline(userInput);
                LocalDateTime byWhen = getDeadline(userInput);
                return new DeadlineCommand(desc, byWhen);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
                break;
            }
        case EventCommand.COMMAND_WORD:
            try {
                String desc = getDescEvent(userInput);
                LocalDateTime from = getFrom(userInput);
                LocalDateTime to = getTo(userInput);
                return new EventCommand(desc, from, to);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
                break;
            }
        case FindCommand.COMMAND_WORD:
            try {
                String keyword = getKeyword(userInput);
                return new FindCommand(keyword);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
                break;
            }
        default:
            return new UnknownCommand();
        }
        return new UnknownCommand();
    }

    /**
     * Gets the index of the task specified.
     * @param userInput full user input string
     * @return index of task specified
     */
    public static int getTaskIndex(String userInput) {
        String taskIndex = "";
        int toMinus = 1;
        char fromBack = userInput.charAt(userInput.length() - toMinus);
        while (fromBack != (' ')) {
            taskIndex = fromBack + taskIndex;
            toMinus++;
            fromBack = userInput.charAt(userInput.length() - toMinus);
        }
        int intTaskIndex = Integer.parseInt(taskIndex) - 1;
        return intTaskIndex;
    }

    /**
     * Gets the description of the todo task.
     * @param userInput full user input string
     * @return description of the todo task
     * @throws DukeException
     */
    public static String getDescToDo(String userInput) throws DukeException {
        try {
            String subString = userInput.substring(5);
            String desc = "";
            int index = 0;
            char front = subString.charAt(index);
            while (index < subString.length() - 1) {
                desc = desc + front;
                index++;
                front = subString.charAt(index);
            }
            desc = desc + front;
            return desc;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    /**
     * Gets the description of the deadline task.
     * @param userInput full user input string
     * @return description of the deadline task
     * @throws DukeException
     */
    public static String getDescDeadline(String userInput) throws DukeException {
        try {
            String subString = userInput.substring(9);
            String desc = "";
            int index = 0;
            char front = subString.charAt(index);
            while (front != ('/')) {
                desc = desc + front;
                index++;
                front = subString.charAt(index);
            }
            return desc;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
    }
    /**
     * Gets the description of the event task.
     * @param userInput full user input string
     * @return description of the event task
     * @throws DukeException
     */
    public static String getDescEvent(String userInput) throws DukeException {
        try {
            String subString = userInput.substring(6);
            String desc = "";
            int index = 0;
            char front = subString.charAt(index);
            while (front != ('/')) {
                desc = desc + front;
                index++;
                front = subString.charAt(index);
            }
            return desc;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
        }
    }

    /**
     * Gets the deadline of deadline task.
     * @param userInput full user input string
     * @return deadline of task
     * @throws DukeException
     */
    public static LocalDateTime getDeadline(String userInput) throws DukeException {
        String[] arrOfStr = userInput.split("/by");
        String strDate = arrOfStr[1].substring(1);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID DATE!!! Please enter date in YYYY-MM-DD HHMM format");
        }
    }

    /**
     * Gets the start date of event task.
     * @param userInput full user input string
     * @return start date of task
     * @throws DukeException
     */
    public static LocalDateTime getFrom(String userInput) throws DukeException {
        String[] arrOfStr = userInput.split("/from ")[1].split(" /to");
        String strFrom = arrOfStr[0];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(strFrom, formatter);
            return from;
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID 'From' DATE!!! Please enter date in YYYY-MM-DD HHMM format");
        }
    }

    /**
     * Gets end date of event task.
     * @param userInput full user input string
     * @return end date of task
     * @throws DateTimeParseException
     */
    public static LocalDateTime getTo(String userInput) throws DukeException {
        String[] arrOfStr = userInput.split("/to ");
        String strTo = arrOfStr[1];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime to = LocalDateTime.parse(strTo, formatter);
            return to;
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID 'To' DATE!!! Please enter date in YYYY-MM-DD HHMM format");
        }
    }

    /**
     * Gets keyword from full user input.
     * @param userInput full user input string.
     * @return
     */
    public static String getKeyword(String userInput) throws DukeException {
        try {
            String keyword = userInput.substring(5);
            return keyword;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
