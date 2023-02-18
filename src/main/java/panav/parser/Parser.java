package panav.parser;

import panav.command.*;
import panav.exception.InvalidInputException;

/**
 * Class to represent the parser which takes in the users command and makes sense of the command.
 */
public class Parser {


    /**
     * Method takes in the full user input string and decodes it to the respective command.
     * @param fullCommand the full user input.
     * @return the decoded command from among the list of available commands.
     */
    public static Command parse(String fullCommand) {
        String[] command = fullCommand.split(" ");
        String firstWord = command[0];
        try {
            switch (firstWord) {
            case "list":
                return new ListCommand();
            case "todo":
                String todoMessage = parseToDo(fullCommand);
                return new TodoCommand(todoMessage);
            case "deadline":
                String deadlineMessage = parseDeadline(fullCommand)[1];
                String by = parseDeadline(fullCommand)[0];
                return new DeadlineCommand(deadlineMessage, by);
            case "event":
                String eventMessage = parseEvent(fullCommand, command)[0];
                String from = parseEvent(fullCommand, command)[1];
                String to = parseEvent(fullCommand, command)[2];
                return new EventCommand(eventMessage, from, to);
            case "mark":
                return new MarkCommand(fullCommand);
            case "unmark":
                return new UnmarkCommand(fullCommand);
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(fullCommand);
            case "find":
                String keyword = parseFind(fullCommand);
                return new FindCommand(keyword);
            default:
                throw new InvalidInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    /**
     * Helper method to parse deadline command.
     * @param fullCommand the full command.
     * @return array of strings.
     */
    public static String[] parseDeadline(String fullCommand) {
        int indexBy = fullCommand.indexOf("/by");
        String by = fullCommand.substring(indexBy + 4);
        String deadlineMessage = fullCommand.substring(9, indexBy - 1);
        String[] res = {by, deadlineMessage};
        return res;
    }

    /**
     * Helper method to parse event command.
     * @param fullCommand the full command.
     * @param command array of strings.
     * @return array of strings.
     */
    public static String[] parseEvent(String fullCommand, String[] command) {
        int fromIndex = 0;
        int toIndex = 0;
        String eventMessage = "";
        String from = "";
        String to = "";
        for (int j = 0; j < command.length; j++) {
            if (command[j].compareTo("/from") == 0) {
                fromIndex = j;
            }
            if (command[j].compareTo("/to") == 0) {
                toIndex = j;
            }
        }
        for (int j = 0; j < command.length; j++) {
            if (j < fromIndex && j > 0) {
                eventMessage += command[j] + " ";
            }
            if (j > fromIndex && j < toIndex) {
                from += command[j] + " ";
            }
            if (j > toIndex) {
                to += command[j];
            }
        }
        String[] res = {eventMessage, from, to};
        return res;
    }

    /**
     * Helper method to parse todo command.
     * @param fullCommand the full command.
     * @return array of strings.
     */
    public static String parseToDo(String fullCommand) {
        return fullCommand.replace("todo", "").trim();
    }
    /**
     * Helper method to parse find command.
     * @param fullCommand the full command.
     * @return array of strings.
     */
    public static String parseFind(String fullCommand) {
        return fullCommand.replace("find", "").trim();
    }

}
