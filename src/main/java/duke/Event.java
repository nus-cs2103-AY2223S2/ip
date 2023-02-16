package duke;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import util.DukeException;

/**
 * The Event class extends the Task class and represents a task with a duration.
 *
 * @author @tricixg
 * @version 1.0
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs a new Event object with the given task and due date.
     *
     * @param description a string description of the task.
     * @param from The task's start date/time.
     * @param to The task's end date/time.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    /**
     * Constructs a new Event object with the given task and due date.
     *
     * @param description The task description.
     * @param from The task's start date/time as a LocalDate type
     * @param to The task's end date/time as a LocalDate type
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy")).toString();
    }

    /**
     * Constructs a new Event object with the given task and due date.
     *
     * @param isDone a boolean indicating whether the task is completed or not.
     * @param description a string description of the task.
     * @param from The task's start date/time.
     * @param to The task's end date/time.
     */
    public Event(Boolean isDone, String description, String from, String to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
        this.taskType = "E";
    }

    /**
     * Returns a string representation of the task
     * in the format "[E][status icon] task description (from: start to: end)".
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from + " to:" + to + ")";
    }


    /**
     * Creates a new Event task from a user's input
     *
     * @param array  a list of tasks.
     * @param splitInput an array of strings containing the user input.
     */
    public static String createEventTask(ArrayList<Task> array, String[] splitInput) {
        String combinedString = String.join(" ", splitInput);
        if (splitInput.length == 1 || splitInput[1].equals("")) {
            try {
                throw new DukeException("event");
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else {
            String event = combinedString.split(" ", 2)[1];
            String[] eventArr = event.split(" /from", 2);
            if (eventArr.length == 2) {
                String desc = eventArr[0];
                String[] periodArr = eventArr[1].split(" /to");
                if (periodArr.length == 2) {
                    String from = periodArr[0].strip();
                    String to = periodArr[1].strip();
                    if (isDate(from) && isDate(to)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate ldFrom = LocalDate.parse(from, formatter);
                        LocalDate ldTo = LocalDate.parse(to, formatter);
                        Event e = new Event(desc, ldFrom, ldTo);
                        array.add(e);
                        return Ui.addTask(array, e);
                    } else {
                        Event e = new Event(desc, from, to);
                        array.add(e);
                        return Ui.addTask(array, e);
                    }
                }
            }
        }
        return "error";
    }

    /**
     * Checks whether a string is an instance of LocalDate
     * in the format "yyyy-MM-dd"
     *
     * @param date String to be checked
     * @return True if "yyyy-MM-dd"
     */
    public static boolean isDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = null;
        try {
            ld = LocalDate.parse(date, formatter);
            System.out.println(ld);
        } catch (DateTimeParseException e) {
            System.out.println("Date " + date + " is not a date.");
            return false;
        }
        return true;
    }

    /**
     * Joins elements of an array into a string, using a separator and a specified range.
     *
     * @param array The array of elements to join.
     * @param separator The separator to use between elements.
     * @param startIndex The start index of the range.
     * @param endIndex The end index of the range.
     * @return A string that consists of the elements of the array
     *          joined with the separator, or null if the array is null.
     */
    public static String join(Object[] array, String separator, int startIndex, int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        int bufSize = (endIndex - startIndex);
        if (bufSize <= 0) {
            return "";
        }
        bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length())
                        + separator.length());
        StringBuffer buf = new StringBuffer(bufSize);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }


    /**
     * Returns a string representation of the task
     * in the format "E | completion status | task description | from | to".
     *
     * @return String of deadline task in save format
     */
    @Override
    public String saveFormat() {
        String d = " | ";
        int marked = this.getIsDone() ? 1 : 0;
        String timePeriod = from + d + to;
        return "E" + d + marked + d + description + d + timePeriod;
    }
}

