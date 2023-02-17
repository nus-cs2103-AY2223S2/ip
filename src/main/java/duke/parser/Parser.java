package duke.parser;

/**
 * Class to represent a parser to make sense of user command.
 * Keeps track of the original input (String), the input split
 * by spaces (String[]) and the command keyword (String).
 */
public class Parser {
    private static final int TODO_DESCRIPTION_INDEX = 5;
    private static final int DEADLINE_DESCRIPTION_INDEX = 9;
    private static final int DEADLINE_DATE_OFFSET = 4;
    private static final int EVENT_DESCRIPTION_INDEX = 6;
    private static final int EVENT_FROM_OFFSET = 6;
    private static final int EVENT_TO_OFFSET = 4;
    private static final int FIND_KEYWORD_INDEX = 5;

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
     * Returns tag from parsed input.
     *
     * @return Tag to add to task.
     */
    public String getTag() {
        return splitInput[2];
    }

    /**
     * Tests if input has a description to instantiate a ToDo object
     * by checking if input length is greater than 5.
     *
     * @return True if input has a description and false otherwise.
     */
    public boolean hasDescription() {
        return input.length() > TODO_DESCRIPTION_INDEX;
    }

    /**
     * Returns ToDo description by considering the input after "todo ".
     *
     * @return Description of ToDo.
     */
    public String parseToDoDescription() {
        return input.substring(TODO_DESCRIPTION_INDEX);
    }

    /**
     * Returns Deadline description by considering the input until "/by".
     *
     * @return Description of Deadline.
     */
    public String parseDeadlineDescription() {
        int byIndex = input.indexOf("/by");
        return input.substring(DEADLINE_DESCRIPTION_INDEX, byIndex - 1);
    }

    /**
     * Returns Deadline date by considering the input after "/by ".
     *
     * @return Date of Deadline.
     */
    public String parseDeadlineDate() {
        int byIndex = input.indexOf("/by");
        return input.substring(byIndex + DEADLINE_DATE_OFFSET);
    }

    /**
     * Returns Event description by considering the input until "/from".
     *
     * @return Description of Event.
     */
    public String parseEventDescription() {
        int fromIndex = input.indexOf("/from");
        return input.substring(EVENT_DESCRIPTION_INDEX, fromIndex - 1);
    }

    /**
     * Returns Event begin date by considering the input after "/from " until "/to".
     *
     * @return 'From' date of Event.
     */
    public String parseEventFrom() {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        return input.substring(fromIndex + EVENT_FROM_OFFSET, toIndex - 1);
    }

    /**
     * Returns Event end date by considering the input after "/to ".
     *
     * @return 'To' date of Event.
     */
    public String parseEventTo() {
        int toIndex = input.indexOf("/to");
        return input.substring(toIndex + EVENT_TO_OFFSET);
    }

    public String parseFindKeyword() {
        return input.substring(FIND_KEYWORD_INDEX);
    }
}
