package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Class for parsing all user inputs
 */
public class Parser {
    /**
     * Parses user input to a ToDo object
     *
     * @param input the user input given
     * @return a ToDo object
     * @throws Exception there is error in user's input
     */
    public static ToDo parseToDo(String input) throws Exception {
        String desc = toDoDesc(input);
        return new ToDo(desc);
    }

    /**
     * Retrieves description of ToDo input
     *
     * @param input user's input
     * @return description of ToDo
     * @throws Exception if description is empty
     */
    public static String toDoDesc(String input) throws Exception {
        String desc = input.substring(5);
        if (desc.equals("")) {
            throw new DukeExceptions("Description cannot be empty!");
        }
        return desc;
    }

    /**
     * @param input user's input
     * @return user's input without 'deadline '
     * @throws Exception if description and deadline is empty
     */
    public static String checkDeadlineInput(String input) throws Exception {
        String inp = input.substring(9);
        if (inp.equals("")) {
            throw new DukeExceptions("Input cannot be empty!");
        }
        return inp;
    }

    /**
     * Retrieves description of Deadline input
     *
     * @param input user's input
     * @return description of Deadline
     * @throws Exception if description is empty
     */
    public static String deadlineDesc(String input) throws Exception {
        String[] inp = input.split("/");
        String desc = inp[0].substring(9);
        if (desc.equals("")) {
            throw new Exception("Description cannot be empty!");
        }
        return desc;
    }

    /**
     * Retrieves date of Deadline input
     *
     * @param input user's input
     * @return Date of Deadline
     * @throws Exception if date is empty or in wrong format
     */
    public static String deadlineDate(String input) throws Exception {
        String[] inp = input.split("/");
        String date = inp[1].substring(3);
        if (date.length() == 0) {
            throw new Exception("deadline is empty!");
        }
        if (date.length() != 10) {
            throw new Exception("deadline wrong format!");
        }
        return date;
    }

    /**
     * Parses String to LocalDateTime
     *
     * @param input String in the form of yyyy-dd-mm HH:MM
     * @return LocalDateTime of input
     */
    public static LocalDateTime parseLocalDateTime(String input) throws Exception {
        String[] dnt = input.split(" ");
        if (dnt.length != 2) {
            throw new Exception("Please input date and time!");
        }
        String date = dnt[0];
        String time = dnt[1];
        LocalDateTime dnT = LocalDateTime.parse(String.join("T", date, time));
        return dnT;
    }

    /**
     * Parses user input to a Deadline object
     *
     * @param input the user input given
     * @return a Deadline object
     * @throws Exception if there is error with user's input
     */
    public static Deadline parseDeadline(String input) throws Exception {
        String inp = checkDeadlineInput(input);
        String desc = deadlineDesc(input);
        String deadline = deadlineDate(input);
        LocalDate date = LocalDate.parse(deadline);
        return new Deadline(desc, date);
    }

    /**
     * @param input user's input
     * @return user's input without 'event '
     * @throws Exception if description and start and end are empty
     */
    public static String checkEventInput(String input) throws Exception {
        String inp = input.substring(6);
        if (inp.equals("")) {
            throw new DukeExceptions("Input cannot be empty!");
        }
        return inp;
    }

    /**
     * Splits Event input by "/"
     *
     * @param input user's input
     * @return Array of split input
     * @throws Exception if input format is wrong
     */
    public static String[] splitInput(String input) throws Exception {
        String[] inp = input.split("/");
        if (inp.length != 3) {
            throw new Exception("Format is task, start, end!");
        }
        if (inp[0].length() <= 6 || inp[1].length() <= 15 || inp[2].length() <= 13) {
            throw new Exception("Format is wrong!");
        }
        return inp;
    }

    /**
     * Retrieves description of Event input
     *
     * @param input user's input
     * @return description of Event
     * @throws Exception if description is empty
     */
    public static String eventDesc(String input) throws Exception {
        String desc = input.substring(6);
        if (desc.equals("")) {
            throw new Exception("Description cannot be empty!");
        }
        return desc;
    }

    /**
     * Parses user input for creating event
     *
     * @param input user's input
     * @return an Event object
     * @throws Exception if there is error with user's input
     */
    public static Event parseEvent(String input) throws Exception {
        String eventInput = checkEventInput(input);
        String[] inp = splitInput(input);
        String start = inp[1].substring(5);
        String end = inp[2].substring(3);
        LocalDateTime startDnT = parseLocalDateTime(start);
        LocalDateTime endDnT = parseLocalDateTime(end);
        String desc = eventDesc(inp[0]);
        Event ev = new Event(desc, startDnT, endDnT);
        return ev;
    }
}
