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
     * @param input the user input given
     * @return a ToDo object
     */
    public static ToDo parseToDo(String input) {
        String desc = toDoDesc(input);
        try {
            if (desc.equals("")) {
                throw new DukeExceptions("Description cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        return new ToDo(desc);
    }

    /**
     * Retrieves description of ToDo input
     * @param input user's input
     * @return description of ToDo
     */
    public static String toDoDesc(String input) {
        return input.substring(5);
    }

    /**
     * Retrieves description of Deadline input
     * @param input user's input
     * @return description of Deadline
     */
    public static String deadlineDesc(String input) {
        String[] inp = input.split("/");
        return inp[0].substring(9);
    }

    /**
     * Retrieves date of Deadline input
     * @param input user's input
     * @return Date of Deadline
     */
    public static String deadlineDate(String input) {
        String[] inp = input.split("/");
        return inp[1].substring(3);
    }
    /**
     * Parses String to LocalDateTime
     * @param input String in the form of yyyy-dd-mm HH:MM
     * @return LocalDateTime of input
     */
    public static LocalDateTime parseLocalDateTime(String input) {
        String[] dnt = input.split(" ");
        String date = dnt[0];
        String time = dnt[1];
        LocalDateTime dnT = LocalDateTime.parse(String.join("T", date, time));
        return dnT;
    }
    /**
     * Parses user input to a Deadline object
     * @param input the user input given
     * @return a Deadline object
     */
    public static Deadline parseDeadline(String input) {
        String inp = input.substring(9);
        try { //catching no desc and no deadline
            if (inp.equals("")) {
                throw new DukeExceptions("Input cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        try { //catching no deadline
            String deadline = deadlineDate(input);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please input a deadline!");
        }
        try { //catching no description
            String desc = deadlineDesc(input);
            if (desc.equals("")) {
                throw new DukeExceptions("Description cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        String desc = deadlineDesc(input);
        String deadline = deadlineDate(input);
        LocalDate date = LocalDate.parse(deadline);
        return new Deadline(desc, date);
    }

    /**
     * Parses user input for creating event
     * @param input user's input
     * @return an Event object
     */
    public static Event parseEvent(String input) {
        try { //catching no desc and no deadline
            if (input.substring(6).equals("")) {
                throw new DukeExceptions("Input cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        //event haha /from 2000-20-20 13:00 /to 2000-20-02 13:15
        String[] inp = input.split("/");
        try { //Catching for input format error
            if (inp.length != 3) {
                throw new DukeExceptions("Format is task, start, end!");
            }
        } catch (DukeExceptions de) {
            System.out.println(de.getMessage());
        }
        String start = inp[1].substring(5);
        String end = inp[2].substring(3);
        LocalDateTime startDnT = parseLocalDateTime(start);
        LocalDateTime endDnT = parseLocalDateTime(end);
        String undesc = inp[0];
        String desc = undesc.substring(6);
        try { //catching for empty description
            if (desc.equals("")) {
                throw new DukeExceptions("Description cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println(e.getMessage());
        }
        Event ev = new Event(desc, startDnT, endDnT);
        return ev;
    }
}
