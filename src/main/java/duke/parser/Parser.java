package duke.parser;

/**
 * Class to represent a parser to make sense of user command.
 * Keeps track of the original input (String), the input split
 * by spaces (String[]) and the command keyword (String).
 */
public class Parser {
    private String input;
    private String[] splitInput;
    private String command;

    /**
     * Initializes a new parser with input.
     * Also splits the input on spaces and parses out the command
     * as the first member of the split input array.
     *
     * @param input User input.
     */
    public Parser(String input) {
        this.input = input;
        this.splitInput = input.split(" ");
        command = splitInput[0].toLowerCase();
    }

    /**
     * Updates input (and splitInput and command) of parser.
     *
     * @param input New user input.
     */
    public void updateInput(String input) {
        this.input = input;
        this.splitInput = input.split(" ");
        command = splitInput[0].toLowerCase();
    }

    /**
     * Returns command keyword of input.
     *
     * @return Command keyword.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns task number (1-indexed) from parsed input.
     *
     * @return 1-indexed position of task in list.
     */
    public int getTaskNumber() {
        return Integer.parseInt(splitInput[1]);
    }

    /**
     * Tests if input has a description to instantiate a ToDo object
     * by checking if input length is greater than 5.
     *
     * @return True if input has a description and false otherwise.
     */
    public boolean isValidToDo() {
        return input.length() > 5;
    }

    /**
     * Returns ToDo description by considering the input after "todo ".
     *
     * @return Description of ToDo.
     */
    public String parseToDoDescription() {
        return input.substring(5);
    }

    /**
     * Returns Deadline description by considering the input until "/by".
     *
     * @return Description of Deadline.
     */
    public String parseDeadlineDescription() {
        int byIndex = input.indexOf("/by");
        return input.substring(9, byIndex - 1);
    }

    /**
     * Returns Deadline date by considering the input after "/by ".
     *
     * @return Date of Deadline.
     */
    public String parseDeadlineDate() {
        int byIndex = input.indexOf("/by");
        return input.substring(byIndex + 4);
    }

    /**
     * Returns Event description by considering the input until "/from".
     *
     * @return Description of Event.
     */
    public String parseEventDescription() {
        int fromIndex = input.indexOf("/from");
        return input.substring(6, fromIndex - 1);
    }

    /**
     * Returns Event begin date by considering the input after "/from " until "/to".
     *
     * @return 'From' date of Event.
     */
    public String parseEventFrom() {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        return input.substring(fromIndex + 6, toIndex - 1);
    }

    /**
     * Returns Event end date by considering the input after "/to ".
     *
     * @return 'To' date of Event.
     */
    public String parseEventTo() {
        int toIndex = input.indexOf("/to");
        return input.substring(toIndex + 4);
    }
}
