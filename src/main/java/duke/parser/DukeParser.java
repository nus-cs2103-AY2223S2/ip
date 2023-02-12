package duke.parser;

import duke.command.DukeCommand;
import duke.date.DukeDate;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidCommandException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for parsing the user's input.
 */
public class DukeParser {


    /**
     * Breaks the input string into commands and its corresponding arguments, or throw an error if
     * the input string is not a valid command.
     * 
     * @param inputString
     * @return
     */
    public static DukeCommand parseCommand(String inputString) {
        // First split by space to get each individual word of the inputString
        String[] splittedString = inputString.split(" ");

        // The first(0th) word has to be a command.
        String command = splittedString[0];

        // Check whether the command is valid.
        return getCommandOrError(command);
    }

    private static DukeCommand getCommandOrError(String command) {
        for (DukeCommand dukeCmd : DukeCommand.values()) {
            if (dukeCmd.text.equals(command)) {
                return dukeCmd;
            }
        }
        // This command is invalid if it's not any of the DukeCommand.
        throw new InvalidCommandException();
    }

    public static String[] parseCommandArgs(DukeCommand command, String inputString) {
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

        case DELETE: {
            parseDelete(inputString, commandArgs);
            break;
        }
        case MARK: {
            parseMark(inputString, commandArgs);
            break;
        }
        case UNMARK: {
            parseUnmark(inputString, commandArgs);
            break;
        }
        }
        return commandArgs.toArray(new String[] {});
    }

    private static void parseListOrBye(String inputString, List<String> commandArgs) {
        boolean isInputJustList = inputString.strip().length() == DukeCommand.LIST.text.length();
        boolean isInputJustBye = inputString.strip().length() == DukeCommand.BYE.text.length();

        boolean hasUserProvidedArgs = !isInputJustList && !isInputJustBye;
        if (hasUserProvidedArgs)
            throw new InvalidCommandException();
    }


    private static void parseFind(String inputString, List<String> commandArgs) {
        String keyword = inputString.substring(DukeCommand.FIND.text.length());
        String cleanedKeyword = keyword.strip();
        commandArgs.add(cleanedKeyword);
    }

    private static void parseDeadline(String inputString, List<String> commandArgs) {
        int offset = DukeCommand.DEADLINE.text.length();
        int byIndex = inputString.indexOf("/by", offset);
        boolean isByKeywordExist = byIndex != -1;
        if (!isByKeywordExist)
            throw new Error("Invalid argument!");
        String description = inputString.substring(offset, byIndex).strip();
        String deadline = inputString.substring(byIndex + ("/by".length())).strip();

        try {
            DukeDate.parseDateString(deadline);
            commandArgs.add(description);
            commandArgs.add(deadline);
        } catch (DateTimeParseException e) {
            throw new Error("☹ OOPS!!! The time format is invalid, please use yyyy-MM-dd");
        }
    }

    private static void parseEvent(String inputString, List<String> commandArgs) {
        int offset = DukeCommand.EVENT.text.length();
        int fromIndex = inputString.indexOf("/from", offset);
        int toIndex = inputString.indexOf("/to", offset);

        boolean isKeywordExist = fromIndex != -1 && toIndex != -1;
        if (!isKeywordExist)
            throw new InvalidArgumentException();

        String description = inputString.substring(offset, fromIndex).strip();

        try {
            String from = inputString.substring(fromIndex + ("/from".length()), toIndex).strip();
            String to = inputString.substring(toIndex + ("/to".length())).strip();
            LocalDate fromDate = DukeDate.parseDateString(from);
            LocalDate toDate = DukeDate.parseDateString(to);

            boolean isValidFromDate = fromDate.isBefore(toDate) || fromDate.isEqual(toDate);

            if (!isValidFromDate)
                throw new Error("☹ OOPS!!! from date should be before to date!");
            commandArgs.add(description);
            commandArgs.add(from);
            commandArgs.add(to);

        } catch (DateTimeParseException e) {
            throw new Error("☹ OOPS!!! The time format is invalid, please use yyyy-MM-dd");
        }
    }

    private static void parseToDo(String inputString, List<String> commandArgs) {
        // Shift the offset as follows to capture the description using substring;
        // {todo}[offset] {description}
        int offset = DukeCommand.TODO.text.length();
        String description = inputString.substring(offset).strip();
        boolean isDescriptionEmpty = description.length() == 0;
        if (isDescriptionEmpty) {
            throw new Error("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        commandArgs.add(description);
    }

    private static void parseMark(String inputString, List<String> commandArgs) {
        String taskIndex = inputString.substring(DukeCommand.MARK.text.length());
        String cleanedTaskIndex = taskIndex.strip();
        try {
            Integer.parseInt(cleanedTaskIndex);
            commandArgs.add(cleanedTaskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }
    }

    private static void parseUnmark(String inputString, List<String> commandArgs) {
        String taskIndex = inputString.substring(DukeCommand.UNMARK.text.length());
        String cleanedTaskIndex = taskIndex.strip();
        try {
            Integer.parseInt(cleanedTaskIndex);
            commandArgs.add(cleanedTaskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }
    }

    private static void parseDelete(String inputString, List<String> commandArgs) {
        String taskIndex = inputString.substring(DukeCommand.DELETE.text.length());
        String cleanedTaskIndex = taskIndex.strip();
        try {
            Integer.parseInt(cleanedTaskIndex);
            commandArgs.add(cleanedTaskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException();
        }
    }

}
