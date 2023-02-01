package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
                LocalDate byWhen = getDeadline(userInput);
                return new DeadlineCommand(desc, byWhen);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
            }
        case EventCommand.COMMAND_WORD:
            try {
                String desc = getDescEvent(userInput);
                LocalDate from = getFrom(userInput);
                LocalDate to = getTo(userInput);
                return new EventCommand(desc,from,to);
            } catch (DukeException e) {
                Ui.showError(e.getMessage());
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
    public static LocalDate getDeadline(String userInput) throws DukeException {
        String[] arrOfStr = userInput.split("/by")[1].split(" ");
        String strDate = arrOfStr[1];
        try {
            LocalDate date = LocalDate.parse(strDate);
            return date;
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID DATE!!! Please enter date in YYYY/MM/DD format");
        }
    }

    /**
     * Gets the start date of event task.
     * @param userInput full user input string
     * @return start date of task
     * @throws DukeException
     */
    public static LocalDate getFrom(String userInput) throws DukeException {
        String[] arrOfStr = userInput.split("/from")[1].split(" ");
        try {
            LocalDate from = LocalDate.parse(arrOfStr[1]);
            return from;
        } catch (DateTimeParseException e) {
            throw new DukeException("INVALID 'From' DATE!!! Please enter date in YYYY/MM/DD format");
        }
    }

    /**
     * Gets end date of event task.
     * @param userInput full user input string
     * @return end date of task
     * @throws DateTimeParseException
     */
    public static LocalDate getTo(String userInput) throws DateTimeParseException {
        String[] arrOfStr = userInput.split("/from")[1].split(" ");
        try {
            LocalDate to = LocalDate.parse((arrOfStr[3]));
            return to;
        } catch (DateTimeParseException e) {
            System.out.println("INVALID 'To' DATE!!! Please enter date in YYYY/MM/DD format");
            throw e;
        }
    }
}
