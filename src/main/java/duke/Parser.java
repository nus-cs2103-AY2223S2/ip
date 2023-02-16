package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.ErrorCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
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
     * @return Command
     */
    public static Command parseCommand(String userInput) {
        String commandWord = getCommandWord(userInput);
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            return parseList();
        case MarkCommand.COMMAND_WORD:
            return parseMark(userInput);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmark(userInput);
        case DeleteCommand.COMMAND_WORD:
            return parseDelete(userInput);
        case TerminateCommand.COMMAND_WORD:
            return parseTerminate();
        case ToDoCommand.COMMAND_WORD:
            return parseTodo(userInput);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(userInput);
        case EventCommand.COMMAND_WORD:
            return parseEvent(userInput);
        case FindCommand.COMMAND_WORD:
            return parseFind(userInput);
        case SortCommand.COMMAND_WORD:
            return parseSort();
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Gets the command word of the user input
     * @param userInput full user input
     * @return command word.
     */
    public static String getCommandWord(String userInput) {
        String[] arrOfStr = userInput.split(" ");
        String commandWord = arrOfStr[0];
        return commandWord;
    }

    /**
     * Gets the index of the task specified.
     * @param userInput full user input string
     * @return index of task specified
     */
    public static int getTaskIndex(String userInput) {
        String[] arrOfStr = userInput.split(" ");
        String strIndex = arrOfStr[1];
        int intIndex = Integer.parseInt(strIndex) - 1;
        return intIndex;
    }

    /**
     * Gets the description of the todo task.
     * @param userInput full user input string
     * @return description of the todo task
     * @throws DukeException
     */
    public static String getDescToDo(String userInput) throws DukeException {
        try {
            String[] arrOfStr = userInput.split("todo ");
            String desc = arrOfStr[1];
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
            String[] arrOfStr = userInput.split("deadline ");
            String descDateTime = arrOfStr[1];
            String[] arrOfDescDateTime = descDateTime.split("/by");
            String desc = arrOfDescDateTime[0];
            if (desc.length() == 0) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
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
            String[] arrOfStr = userInput.split("event ");
            String descDateTime = arrOfStr[1];
            String[] arrOfDescDateTime = descDateTime.split("/from");
            String desc = arrOfDescDateTime[0];
            if (desc.length() == 0) {
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }
            return desc;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Gets the deadline of deadline task.
     * @param userInput full user input string
     * @return deadline of task
     * @throws DukeException
     */
    public static LocalDateTime getDeadline(String userInput) throws DukeException {
        try {
            String[] arrOfStr = userInput.split("/by ");
            String strDate = arrOfStr[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
            return dateTime;
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeException("'/by DATETIME' is missing!");
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
        try {
            String[] arrOfStr = userInput.split("/from ")[1].split(" /to");
            String strFrom = arrOfStr[0];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(strFrom, formatter);
            return from;
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeException("'/from' DATETIME' is missing!");
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
        try {
            String[] arrOfStr = userInput.split("/to ");
            String strTo = arrOfStr[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime to = LocalDateTime.parse(strTo, formatter);
            return to;
        } catch (ArrayIndexOutOfBoundsException ae) {
            throw new DukeException("'/to' DATETIME' is missing!");
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID 'To' DATE!!! Please enter date in YYYY-MM-DD HHMM format");
        }
    }

    /**
     * Gets keyword from full user input.
     * @param userInput full user input string
     * @return
     */
    public static String getKeyword(String userInput) throws DukeException {
        try {
            String keyword = userInput.substring(5);
            return keyword;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Find failed because no keyword(s) mentioned :(");
        } catch (Exception e) {
            throw new DukeException("Find failed because " + e.getMessage());
        }
    }

    /**
     * Parse list command.
     * @return new ListCommand
     */
    private static Command parseList() {
        return new ListCommand();
    }

    /**
     * Parse mark command.
     * @param userInput full user input
     * @return new MarkCommand
     */
    private static Command parseMark(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        return new MarkCommand(taskIndex);
    }

    /**
     * Parse unmark command.
     * @param userInput full user input
     * @return new UnmarkCommand
     */
    private static Command parseUnmark(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        return new UnmarkCommand(taskIndex);
    }

    /**
     * Parse delete command.
     * @param userInput
     * @return new DeleteCommand
     */
    private static Command parseDelete(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        return new DeleteCommand(taskIndex);
    }

    /**
     * Parse terminate command.
     * @return new TerminateCommand
     */
    private static Command parseTerminate() {
        return new TerminateCommand();
    }

    /**
     * Parse todo command.
     * @param userInput full user input
     * @return new ToDoCommand or new ErrorCommand if an error is caught
     */
    private static Command parseTodo(String userInput) {
        try {
            String desc = getDescToDo(userInput);
            assert desc.length() != 0 : "Todo task description cannot be empty!";
            return new ToDoCommand(desc);
        } catch (DukeException e) {
            Ui.showResponse(e.getMessage());
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parse deadline command.
     * @param userInput full user input
     * @return new DeadlineCommand or new ErrorCommand if an error is caught
     */
    private static Command parseDeadline(String userInput) {
        try {
            String desc = getDescDeadline(userInput);
            assert desc.length() != 0 : "Deadline task description cannot be empty!";
            LocalDateTime byWhen = getDeadline(userInput);
            assert byWhen != null : "Deadline should not be null!";
            return new DeadlineCommand(desc, byWhen);
        } catch (DukeException e) {
            Ui.showResponse(e.getMessage());
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parse event command.
     * @param userInput full user input
     * @return new EventCommand or new ErrorCommand if an error is caught
     */
    private static Command parseEvent(String userInput) {
        try {
            String desc = getDescEvent(userInput);
            assert desc.length() != 0 : "Event task description cannot be empty!";
            LocalDateTime from = getFrom(userInput);
            assert from != null : "Start date/time should not be null!";
            LocalDateTime to = getTo(userInput);
            assert to != null : "End date/time should not be null!";
            if (from.isAfter(to)) {
                return new ErrorCommand("Error!! FROM datetime cannot be after TO datetime!!!");
            }
            return new EventCommand(desc, from, to);
        } catch (DukeException e) {
            Ui.showResponse(e.getMessage());
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parse find command.
     * @param userInput full user input
     * @return new FindCommand or new ErrorCommand if an error is caught
     */
    private static Command parseFind(String userInput) {
        try {
            String keyword = getKeyword(userInput);
            return new FindCommand(keyword);
        } catch (DukeException e) {
            Ui.showResponse(e.getMessage());
            return new ErrorCommand(e.getMessage());
        }
    }

    private static Command parseSort() {
        return new SortCommand();
    }
}
