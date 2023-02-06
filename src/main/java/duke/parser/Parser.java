package duke.parser;
import java.time.LocalDate;

import duke.exception.DukeException;

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
        }
        return Action.UNKNOWN;
    }

    /**
     * Obtain to do description from user input
     * @param userInput
     * @return String or throws exception
     * @throws DukeException
     */
    public String getTodoDescription(String userInput) throws DukeException {
        String todoDescription;
        try {
            todoDescription = userInput.split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return todoDescription;
    }

    /**
     * Obtain deadline description from user input
     * @param userInput
     * @return String or throws exception
     * @throws DukeException
     */
    public String getDeadlineDescription(String userInput) throws DukeException {
        String deadlineDescription;
        try {
            deadlineDescription = userInput.split(" ", 2)[1].split(" /by ", 2)[0];
        } catch (Exception e) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        return deadlineDescription;
    }

    /**
     * Obtain deadline date from user input
     * @param userInput
     * @return LocalDate or throws exception
     * @throws DukeException
     */
    public LocalDate getDeadlineDate(String userInput) throws DukeException {
        LocalDate deadlineDate;
        try {
            deadlineDate = LocalDate.parse(userInput.split(" ", 2)[1].split(" /by ", 2)[1]);
        } catch (Exception e) {
            throw new DukeException("The date of a deadline cannot be empty. "
                    + "Make sure dates are in this format yyyy-mm-dd");
        }
        return deadlineDate;
    }

    /**
     * Obtain event description from user input
     * @param userInput
     * @return String or exception
     * @throws DukeException
     */
    public String getEventDescription(String userInput) throws DukeException {
        String eventDescription;
        try {
            eventDescription = userInput.split(" ", 2)[1].split(" /", 3)[0];
        } catch (Exception e) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        return eventDescription;
    }

    /**
     * Obtain event date details (start, end) from user input
     * @param userInput
     * @return Start and end date in type Local Date or exception
     * @throws DukeException
     */
    public LocalDate[] getEventDateDetails(String userInput) throws DukeException {
        LocalDate eventStartDate;
        LocalDate eventEndDate;
        String[] input = userInput.split(" ", 2)[1].split(" /", 3);
        try {
            eventStartDate = LocalDate.parse(input[1].split("from ", 2)[1]);
            eventEndDate = LocalDate.parse(input[2].split("to ", 2)[1]);
        } catch (Exception e) {
            throw new DukeException("The start date and end date of a event cannot be empty. \" +\n"
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
