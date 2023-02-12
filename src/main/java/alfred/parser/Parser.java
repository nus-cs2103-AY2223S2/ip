package alfred.parser;
import java.time.LocalDate;

import alfred.exception.AlfredException;

/**
 * Takes in user input and parse it to return useful readable data.
 */
public class Parser {

    /**
     * Different types of command by user
     */
    public enum Action {
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        BYE,
        STATS,
        UNKNOWN
    };

    /**
     * Process userInput and returns relevant action
     * @param userInput
     * @return
     */
    public Action getCommand(String userInput) {
        String s = userInput.split(" ", 2)[0];
        if (userInput.equals("list")) {
            return Action.LIST;
        } else if (s.equals("todo")) {
            return Action.TODO;
        } else if (s.equals("deadline")) {
            return Action.DEADLINE;
        } else if (s.equals("event")) {
            return Action.EVENT;
        } else if (s.equals("mark")) {
            return Action.MARK;
        } else if (s.equals("unmark")) {
            return Action.UNMARK;
        } else if (s.equals("delete")) {
            return Action.DELETE;
        } else if (s.equals("find")) {
            return Action.FIND;
        } else if (s.equals("bye")) {
            return Action.BYE;
        } else if (s.equals("stats")) {
            return Action.STATS;
        }
        return Action.UNKNOWN;
    }

    /**
     * Obtain to do description from user input
     * @param userInput
     * @return String or throws exception
     * @throws AlfredException
     */
    public String getTodoDescription(String userInput) throws AlfredException {
        String todoDescription;
        try {
            todoDescription = userInput.split(" ", 2)[1];
        } catch (Exception e) {
            throw new AlfredException("The description of a todo cannot be empty.");
        }
        return todoDescription;
    }

    /**
     * Obtain deadline description from user input
     * @param userInput
     * @return String or throws exception
     * @throws AlfredException
     */
    public String getDeadlineDescription(String userInput) throws AlfredException {
        String deadlineDescription;
        try {
            deadlineDescription = userInput.split(" ", 2)[1].split(" /by ", 2)[0];
        } catch (Exception e) {
            throw new AlfredException("The description of a deadline cannot be empty.");
        }
        return deadlineDescription;
    }

    /**
     * Obtain deadline date from user input
     * @param userInput
     * @return LocalDate or throws exception
     * @throws AlfredException
     */
    public LocalDate getDeadlineDate(String userInput) throws AlfredException {
        LocalDate deadlineDate;
        try {
            deadlineDate = LocalDate.parse(userInput.split(" ", 2)[1].split(" /by ", 2)[1]);
        } catch (Exception e) {
            throw new AlfredException("The date of a deadline cannot be empty. "
                    + "Make sure dates are in this format yyyy-mm-dd");
        }
        return deadlineDate;
    }

    /**
     * Obtain event description from user input
     * @param userInput
     * @return String or exception
     * @throws AlfredException
     */
    public String getEventDescription(String userInput) throws AlfredException {
        String eventDescription;
        try {
            eventDescription = userInput.split(" ", 2)[1].split(" /", 3)[0];
        } catch (Exception e) {
            throw new AlfredException("The description of an event cannot be empty.");
        }
        return eventDescription;
    }

    /**
     * Obtain event date details (start, end) from user input
     * @param userInput
     * @return Start and end date in type Local Date or exception
     * @throws AlfredException
     */
    public LocalDate[] getEventDateDetails(String userInput) throws AlfredException {
        LocalDate eventStartDate;
        LocalDate eventEndDate;
        String[] input = userInput.split(" ", 2)[1].split(" /", 3);
        try {
            eventStartDate = LocalDate.parse(input[1].split("from ", 2)[1]);
            eventEndDate = LocalDate.parse(input[2].split("to ", 2)[1]);
        } catch (Exception e) {
            throw new AlfredException("The start date and end date of a event cannot be empty. \" +\n"
                    + "\"Make sure dates are in this format yyyy-mm-dd.");
        }
        return new LocalDate[]{eventStartDate, eventEndDate};
    }

    /**
     * Obtain task index from user input
     * @param userInput
     * @return
     */
    public Integer getTaskIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
    }
    public String getKeyword(String userInput) {
        return userInput.split(" ", 2)[1].trim();
    }
}
