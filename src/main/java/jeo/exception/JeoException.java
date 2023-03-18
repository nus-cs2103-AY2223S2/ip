package jeo.exception;

import jeo.command.CommandType;

/**
 * Represents the custom exception that is thrown when an error is encountered while parsing user input.
 * @author Goh Jun How
 * @version 0.3
 */
public class JeoException extends Exception {
    private final String commandType;

    /**
     * Creates a new custom exception with the specified error description.
     * @param e String describing the error message
     * @param commandType Type of Command
     */
    public JeoException(String e, String commandType) {
        super(e);
        this.commandType = commandType;
    }

    /**
     * Returns a message string denoting the correct format for each command, if required.
     * @return message string
     */
    public String formatMessage() {
        StringBuilder sb = new StringBuilder("\nCommand format: ");
        switch (CommandType.valueOf(commandType.toUpperCase())) {
        case MARK:
            sb.append("mark <task number>");
            break;
        case UNMARK:
            sb.append("unmark <task number>");
            break;
        case DELETE:
            sb.append("delete <task number>");
            break;
        case TODO:
            sb.append("todo <description> [/tag <tag>...]");
            break;
        case DEADLINE:
            sb.append("deadline <description> </by yyyy-MM-dd HH:mm> [/tag <tag>...]");
            break;
        case EVENT:
            sb.append("event <description> </from yyyy-MM-dd HH:mm> </to yyyy-MM-dd HH:mm> [/tag <tag>...]");
            break;
        case DUE:
            sb.append("due <yyyy-MM-dd HH:mm>");
            break;
        case FIND:
            sb.append("find <keyword>");
            break;
        default:
            // Non command-related error
            return "";
        }
        return sb.toString();
    }

    /**
     * Gets the error message.
     * @return String representing the error message.
     */
    @Override
    public String getMessage() {
        return "[Error] " + super.getMessage() + formatMessage();
    }
}
