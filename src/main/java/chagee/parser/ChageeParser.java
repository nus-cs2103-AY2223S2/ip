package chagee.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import chagee.command.Chagee;
import chagee.date.ChageeDate;
import chagee.exception.EmptyDescriptionException;
import chagee.exception.InvalidArgumentException;
import chagee.exception.InvalidCommandException;
import chagee.exception.InvalidDateFormatException;
import chagee.exception.InvalidEventException;

/**
 * A class for parsing the user's input.
 */
public class ChageeParser {


    /**
     * Breaks the input string into commands and its corresponding arguments, or throw an error if
     * the input string is not a valid command.
     *
     * @param inputString
     * @return
     */
    public static Chagee parseCommand(String inputString) {
        // First split by space to get each individual word of the inputString
        String[] splittedString = inputString.split(" ");

        // The first(0th) word has to be a command.
        String command = splittedString[0];

        // Check whether the command is valid.
        return getCommandOrError(command);
    }

    private static Chagee getCommandOrError(String command) {
        for (Chagee chageeCmd : Chagee.values()) {
            if (chageeCmd.text.equals(command)) {
                return chageeCmd;
            }
        }
        // This command is invalid if it's not any of the ChageeCommand.
        throw new InvalidCommandException();
    }

    /**
     * Parses {@code inputString} to get the command arguments by using {@code command} to check for
     * the type of arguments it should get.
     *
     * @param command
     * @param inputString
     * @return
     */
    public static String[] parseCommandArgs(Chagee command, String inputString) {
        List<String> commandArgs = new ArrayList<>();
        switch (command) {
        case LIST:
        case BYE:
            parseListOrBye(inputString, commandArgs);
            break;

        case DEADLINE: {
            parseDeadline(inputString, commandArgs);
            break;
        }
        case EVENT: {
            parseEvent(inputString, commandArgs);
            break;
        }
        case TODO: {
            parseToDo(inputString, commandArgs);
            break;
        }

        case FIND: {
            parseFind(inputString, commandArgs);
            break;
        }

        case VIEW_SCHEDULE: {
            parseViewSchedule(inputString, commandArgs);
            break;
        }

        case DELETE: {
            parseIndexCommand(Chagee.DELETE, inputString, commandArgs);
            break;
        }
        case MARK: {
            parseIndexCommand(Chagee.MARK, inputString, commandArgs);
            break;
        }
        case UNMARK: {
            parseIndexCommand(Chagee.UNMARK, inputString, commandArgs);
            break;
        }
        }
        return commandArgs.toArray(new String[] {});
    }

    private static void parseListOrBye(String inputString, List<String> commandArgs) {
        boolean isInputJustList = inputString.strip().length() == Chagee.LIST.text.length();
        boolean isInputJustBye = inputString.strip().length() == Chagee.BYE.text.length();

        boolean hasUserProvidedArgs = !isInputJustList && !isInputJustBye;
        if (hasUserProvidedArgs) {
            throw new InvalidCommandException();
        }
    }

    private static void parseFind(String inputString, List<String> commandArgs) {
        String keyword = inputString.substring(Chagee.FIND.text.length());
        String cleanedKeyword = keyword.strip();
        commandArgs.add(cleanedKeyword);
    }

    private static void parseViewSchedule(String inputString, List<String> commandArgs)
            throws Error {
        int offset = Chagee.VIEW_SCHEDULE.text.length();
        String dateString = inputString.substring(offset).strip();
        try {
            ChageeDate.parseDateString(dateString);
            commandArgs.add(dateString);
        } catch (DateTimeParseException e) {
            throw new Error("☹ OOPS!!! The time format is invalid, please use yyyy-MM-dd");
        }
    }

    private static void parseToDo(String inputString, List<String> commandArgs) {
        // Shift the offset as follows to capture the description using substring;
        // {todo}[offset] {description}
        int offset = getCommandLength(Chagee.TODO);
        String description = inputString.substring(offset).strip();
        checkHasDescription(description);
        commandArgs.add(description);
    }

    private static void checkHasDescription(String description) {
        boolean isDescriptionEmpty = description.length() == 0;
        if (isDescriptionEmpty) {
            throw new EmptyDescriptionException();
        }
    }

    private static void parseEvent(String inputString, List<String> commandArgs) {
        int offset = getCommandLength(Chagee.EVENT);
        int fromIndex = inputString.indexOf("/from", offset);
        int toIndex = inputString.indexOf("/to", offset);

        boolean isKeywordExist = fromIndex != -1 && toIndex != -1;
        if (!isKeywordExist) {
            throw new InvalidArgumentException();
        }

        String description = inputString.substring(offset, fromIndex).strip();
        checkHasDescription(description);

        String from = inputString.substring(fromIndex + ("/from".length()), toIndex).strip();
        String to = inputString.substring(toIndex + ("/to".length())).strip();
        LocalDate fromDate = checkValidDate(from);
        LocalDate toDate = checkValidDate(to);
        checkValidEventPeriod(fromDate, toDate);
        commandArgs.add(description);
        commandArgs.add(from);
        commandArgs.add(to);

    }

    private static void checkValidEventPeriod(LocalDate fromDate, LocalDate toDate) {
        boolean isValidFromDate = fromDate.isBefore(toDate) || fromDate.isEqual(toDate);

        if (!isValidFromDate) {
            throw new InvalidEventException();
        }
    }

    private static LocalDate checkValidDate(String dateString) {
        try {
            LocalDate date = ChageeDate.parseDateString(dateString);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(dateString);
        }
    }

    private static void parseDeadline(String inputString, List<String> commandArgs) {
        int offset = getCommandLength(Chagee.DEADLINE);
        int byIndex = inputString.indexOf("/by", offset);
        boolean isByKeywordExist = byIndex != -1;
        if (!isByKeywordExist) {
            throw new InvalidArgumentException();
        }
        String description = inputString.substring(offset, byIndex).strip();
        String deadline = checkValidDeadline(inputString, byIndex);
        checkHasDescription(description);

        commandArgs.add(description);
        commandArgs.add(deadline);
    }

    private static String checkValidDeadline(String inputString, int byIndex) {
        String deadline = inputString.substring(byIndex + ("/by".length())).strip();
        checkValidDate(deadline);
        return deadline;
    }

    private static void parseIndexCommand(Chagee command, String inputString,
            List<String> commandArgs) {
        int offset = getCommandLength(command);
        String taskIndex = inputString.substring(offset);
        String cleanedTaskIndex = taskIndex.strip();
        try {
            Integer.parseInt(cleanedTaskIndex);
            commandArgs.add(cleanedTaskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }
    }

    private static int getCommandLength(Chagee command) {
        return command.text.length();
    }

}
