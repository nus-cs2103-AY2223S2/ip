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
        String td = input.substring(5);
        try {
            if (td.equals("")) {
                throw new DukeExceptions("Description cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println("Description cannot be empty!");
        }
        return new ToDo(td);
    }

    /**
     * Parses user input to a Deadline object
     * @param input the user input given
     * @return a Deadline object
     */
    public static Deadline parseDeadline(String input) {
        try { //catching no desc and no deadline
            if (input.substring(9).equals("")) {
                throw new DukeExceptions("Input cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println("Input cannot be empty!");
        }
        String[] inp = input.split("/");
        try { //catching no deadline
            String deadline = inp[1].substring(3);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please input a deadline!");
        }
        try { //catching no description
            String undesc = inp[0];
            String desc = undesc.substring(9);
            if (desc.equals("")) {
                throw new DukeExceptions("Input cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println("Description cannot be empty!");
        }
        String deadline = inp[1].substring(3);
        LocalDate date = LocalDate.parse(deadline);
        String undesc = inp[0];
        String desc = undesc.substring(9);
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
            System.out.println("Input cannot be empty!");
        }
        String[] inp = input.split("/");
        try { //Catching for input format error
            if (inp.length != 3) {
                throw new DukeExceptions("Format is task, start, end!");
            }
        } catch (DukeExceptions de) {
            System.out.println("Format is task, /start, /end!");
        }
        String start = inp[1].substring(5);
        String[] dnt = start.split(" ");
        String startDate = dnt[0];
        String startTime = dnt[1];
        LocalDateTime startDnT = LocalDateTime.parse(String.join("T", startDate, startTime));
        String end = inp[2].substring(3);
        String[] dateAndTime = end.split(" ");
        String endDate = dateAndTime[0];
        String endTime = dateAndTime[1];
        LocalDateTime endDnT = LocalDateTime.parse(String.join("T", endDate, endTime));
        String undesc = inp[0];
        String desc = undesc.substring(6);
        try { //catching for empty description
            if (desc.equals("")) {
                throw new DukeExceptions("Description cannot be empty!");
            }
        } catch (DukeExceptions e) {
            System.out.println("Description cannot be empty!");
        }
        Event ev = new Event(desc, startDnT, endDnT);
        return ev;
    }
}
