package botanic.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import botanic.Formatter;
import botanic.command.AddCommand;
import botanic.command.ByeCommand;
import botanic.command.Command;
import botanic.command.DeleteCommand;
import botanic.command.FindAllMatchCommand;
import botanic.command.FindDateCommand;
import botanic.command.FindFlexCommand;
import botanic.command.ListCommand;
import botanic.command.MarkCommand;
import botanic.command.UnmarkCommand;
import botanic.exception.IncompleteDescException;
import botanic.exception.InvalidInputException;
import botanic.gui.Gui;
import botanic.task.Deadline;
import botanic.task.Event;
import botanic.task.Task;
import botanic.task.ToDo;

/**
 * Encapsulates the related fields and behavior of a parser that parses the inputs given.
 */
public class Parser {
    private enum CommandEnum {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FINDALLMATCH, FINDFLEX, FINDDATE
    }

    private Gui gui = new Gui();

    /**
     * Checks if the required fields for a given command is given
     * by checking the length of String array of the split input
     * and checking that the portion of the given input behind the command name is not empty.
     *
     * @param splitInputs The given user input that was split into 2 sections using " ".
     * @param fieldType The type of the field we are checking for.
     * @throws IncompleteDescException If the expected field is missing.
     */
    private void checkFieldExists(String[] splitInputs, String fieldType)
            throws IncompleteDescException {
        if (splitInputs.length <= 1 || splitInputs[1].isBlank()) {
            throw new IncompleteDescException(
                    gui.getMissingFieldErrorMsg(fieldType));
        }
    }

    /**
     * Instantiates a new ToDo task and returns it.
     *
     * @param splitInputs An array containing the input by the user.
     * @return The new ToDo task.
     * @throws IncompleteDescException If name of the task is not given.
     */
    private ToDo getTodo(String[] splitInputs) throws IncompleteDescException {
        checkFieldExists(splitInputs, "name of todo");
        return new ToDo(splitInputs[1]);
    }

    /**
     * Parses the given string into a LocalDate object.
     *
     * @param date The given string representation of the date to be parsed.
     * @return The parsed LocalDate.
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return LocalDate.parse(date, dtf);
    }

    /**
     * Finds the index of the given keyword in a section of a given command string.
     *
     * @param input The section of the user input to find the keyword in.
     * @param keyword The keyword to be found.
     * @param keywordType The type of keyword given (what the keyword represents).
     * @return The index of the given keyword in the given string.
     * @throws IncompleteDescException If keyword is not found.
     */
    private int getKeywordIndex(String input, String keyword, String keywordType)
            throws IncompleteDescException {
        int index = input.indexOf(keyword);
        if (index < 0) {
            throw new IncompleteDescException(
                    gui.getMissingFieldErrorMsg(keywordType));
        }
        return index;
    }

    /**
     * Extracts the name of the task from the given input
     * using the indexes given.
     *
     * @param input The string to extract the name from.
     * @param startIndex The starting index of the name in input string.
     * @param endIndex The last index of the name in input string.
     * @return The extracted name.
     * @throws IncompleteDescException If no name can be found using the given indexes.
     */
    private String getName(String input, int startIndex, int endIndex)
            throws IncompleteDescException {
        String name = input.substring(startIndex, endIndex).strip();
        if (name.isBlank()) {
            throw new IncompleteDescException(
                    gui.getMissingFieldErrorMsg("name of task"));
        }
        return name;
    }

    /**
     * Extracts date from the input string given
     * and parses it into a LocalDate object.
     *
     * @param input The input string to extract the date from.
     * @param startIndex The starting index of the date in input string.
     * @param endIndex The last index of the date in input string.
     * @param dateType The type of date extracted (what the date represents).
     * @return A LocalDate representation of the extracted date.
     * @throws IncompleteDescException If no date can be found using the given indexes.
     * @throws InvalidInputException If given date is not in the yyyy/mm/dd format
     *                               or if the given date is not a valid date in the calendar.
     */
    private LocalDate getLocalDate(String input, int startIndex, int endIndex, String dateType)
            throws IncompleteDescException, InvalidInputException {
        String date = input.substring(startIndex, endIndex).strip();
        if (date.isBlank()) {
            throw new IncompleteDescException(
                    gui.getMissingFieldErrorMsg(dateType));
        }
        try {
            LocalDate localDate = Parser.parseDate(date);
            return localDate;
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(
                    gui.getInvalidDateErrorMsg());
        }
    }

    /**
     * Checks that the given dateInput has not passed (i.e., given dateInput is after today's date).
     *
     * @param dateInput The date we want to check.
     * @param dateType The type of dateInput given (what the dateInput represents).
     * @throws InvalidInputException If dateInput has passed.
     */
    private void checkDateHasNotPassed(LocalDate dateInput, String dateType)
            throws InvalidInputException {
        if (dateInput.isBefore(LocalDate.now())) {
            String dateFormatted = Formatter.formatDateForPrint(dateInput);
            throw new InvalidInputException(
                    gui.getDatePassedErrorMsg(dateType, dateFormatted));
        }
    }

    /**
     * Checks whether the given endDate is after or the same as the startDate.
     *
     * @param startDate The starting date.
     * @param endDate The end date.
     * @throws InvalidInputException If endDate given is before startDate given.
     */
    private void checkEndIsAfterStart(LocalDate startDate, LocalDate endDate)
            throws InvalidInputException {
        if (endDate.isBefore(startDate)) {
            String startDateFormatted = Formatter.formatDateForPrint(startDate);
            throw new InvalidInputException(
                    gui.getStartAfterEndErrorMsg(startDateFormatted));
        }
        assert endDate.isAfter(startDate) : "End date should be after start date.";
    }

    /**
     * Instantiates a new Deadline object and returns it.
     *
     * @param splitInputs An array containing the input by the user.
     * @return A new Deadline object.
     * @throws IncompleteDescException If name or due date or both is/are not provided.
     * @throws InvalidInputException If due date is given in the wrong format.
     */
    private Deadline getDeadline(String[] splitInputs)
            throws IncompleteDescException, InvalidInputException {
        //get name
        checkFieldExists(splitInputs, "name of deadline");
        int endIndex = getKeywordIndex(splitInputs[1], "/by", "due date/time");
        String name = getName(splitInputs[1], 0, endIndex);

        //get end date and check validity
        LocalDate endLocalDate = getLocalDate(splitInputs[1], endIndex + 3,
                splitInputs[1].length(), "due date/time");
        checkDateHasNotPassed(endLocalDate, "deadline");

        //create new deadline object and return it
        return new Deadline(name, endLocalDate);
    }

    /**
     * Instantiates a new Event object and returns it.
     *
     * @param splitInputs An array containing the input by the user.
     * @return A new Event.
     * @throws IncompleteDescException If the name, start date or due date are not given.
     * @throws InvalidInputException If any of the date are given
     *                               in a format different from "yyyy/MM/dd".
     */
    private Event getEvent(String[] splitInputs)
            throws IncompleteDescException, InvalidInputException {
        //get name
        checkFieldExists(splitInputs, "name of event");
        int startIndex = getKeywordIndex(splitInputs[1], "/from", "start date/time");
        String name = getName(splitInputs[1], 0, startIndex);

        //get start date and end date of event
        int endIndex = getKeywordIndex(splitInputs[1], "/to", "end date/time");
        LocalDate startLocalDate = getLocalDate(splitInputs[1], startIndex + 5,
                endIndex, "start date/time");
        LocalDate endLocalDate = getLocalDate(splitInputs[1], endIndex + 3,
                splitInputs[1].length(), "end date/time");

        //check validity of start and end date
        checkEndIsAfterStart(startLocalDate, endLocalDate);
        checkDateHasNotPassed(endLocalDate, "end date");

        //create new event object and return it
        return new Event(name, startLocalDate, endLocalDate);
    }

    /**
     * Checks that user has given the index of the task to be acted on,
     * by checking the length of String array of the split input
     * and checking that the portion of the given input behind the command name is not empty.
     *
     * @param splitInputs An array containing the input by the user.
     * @param cmdType The type of command associated with the given index.
     * @throws IncompleteDescException If no index has been give.
     */
    private void checkIndexExists(String[] splitInputs, String cmdType)
            throws IncompleteDescException {
        if (splitInputs.length <= 1 || splitInputs[1].isBlank()) {
            throw new IncompleteDescException(
                    gui.getMissingFieldErrorMsg("index of the item to " + cmdType));
        }
    }

    /**
     * Parses a string into an integer and minus one from the integer.
     *
     * @param input The string representation of the index.
     * @param cmdType The type of command associated with the given index.
     * @return An integer value of item index.
     * @throws InvalidInputException If given string does not contain a parsable integer.
     */
    private int getParsedIndex(String input, String cmdType) throws InvalidInputException {
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException n) {
            throw new InvalidInputException(
                    gui.getNonIntIndexErrorMsg(cmdType)
            );
        }
    }

    /**
     * Parses the given string into the correct command.
     *
     * @param input The string input given by the user.
     * @return A Command representing the string command given.
     * @throws IncompleteDescException If given string command is incomplete.
     * @throws InvalidInputException If given string command is invalid.
     */
    public Command parseCommand(String input)
            throws IncompleteDescException, InvalidInputException {
        String[] splitInputs = input.split(" ", 2);
        try {
            CommandEnum commandType = CommandEnum.valueOf(splitInputs[0].toUpperCase());
            switch (commandType) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                checkIndexExists(splitInputs, "mark");
                int markIndex = getParsedIndex(splitInputs[1], "mark");
                return new MarkCommand(markIndex);
            case UNMARK:
                checkIndexExists(splitInputs, "unmark");
                int unmarkIndex = getParsedIndex(splitInputs[1], "unmark");
                return new UnmarkCommand(unmarkIndex);
            case DELETE:
                checkIndexExists(splitInputs, "delete");
                int deleteIndex = getParsedIndex(splitInputs[1], "delete");
                return new DeleteCommand(deleteIndex);
            case TODO:
                Task task = getTodo(splitInputs);
                return new AddCommand(task);
            case DEADLINE:
                Deadline deadline = getDeadline(splitInputs);
                return new AddCommand(deadline);
            case EVENT:
                Event event = getEvent(splitInputs);
                return new AddCommand(event);
            case FINDALLMATCH:
                checkFieldExists(splitInputs, "keyword to find");
                return new FindAllMatchCommand(splitInputs[1]);
            case FINDFLEX:
                checkFieldExists(splitInputs, "keyword to find");
                return new FindFlexCommand(splitInputs[1]);
            case FINDDATE:
                checkFieldExists(splitInputs, "date to find");
                LocalDate localDateToFind = getLocalDate(splitInputs[1],
                        0, splitInputs[1].length(), "date to find");
                return new FindDateCommand(localDateToFind);
            default:
                throw new InvalidInputException(
                        gui.getInvalidCommandErrorMsg());
            }
        } catch (IllegalArgumentException i) {
            throw new InvalidInputException(
                    gui.getInvalidCommandErrorMsg());
        }
    }
}
