package connor.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Task object that is not to be instantiated.
 */
public abstract class Task {

    /** String that represents the name of the task. */
    protected String taskName;
    /** Boolean that is true if a task is marked. */
    protected boolean isDone;

    /**
     * Constructor for Task that assumes that a task is not marked.
     *
     * @param taskName name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor for Task that marks a task.
     * Meant for reading from memory.
     *
     * @param taskName name of the task.
     * @param isDone indicates if the task is done.
     */
    public Task(String taskName, Boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Returns String representing if a task is marked.
     *
     * @return String [X] if marked.
     */
    public String getMark() {
        if (this.isDone) {
            return "[X]";
        }
        return "[ ]";
    }

    /**
     * Sets isDone to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Set isDone to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the taskName of this instance.
     *
     * @return String of the taskName.
     */
    public String getTaskName() {
        assert (!this.taskName.equals(""));
        return this.taskName;
    }

    /**
     * Returns a String by converting LocalDateTime to print.
     *
     * @param input LocalDateTime representation of a dateTime.
     * @return String from LocalDateTime.
     */
    public String formatDateTime(LocalDateTime input) {
        String month = input.getMonth().toString().substring(0, 3);
        int day = input.getDayOfMonth();
        int year = input.getYear();
        String hour = String.format("%02d", input.getHour());
        String minute = String.format("%02d", input.getMinute());
        return month + " " + day + " " + year + " " + hour + minute;
    }

    /**
     * Returns a String in a parseable format into LocalDateTime.
     *
     * @param input substring from user input containing date and time.
     * @return String that is parseable into LocalDateTime.
     */
    public String dateTimeFormat(String input) {
        String[] dateTimePair = input.split(" ");
        String date = dateTimePair[0];
        String time = dateTimePair[1];
        String hrStr = time.substring(0, 2);
        String minStr = time.substring(2, 4);
        return date + "T" + hrStr + ":" + minStr + ":00";
    }

    /**
     * Returns a LocalDateTime object that has the date and time of the input.
     *
     * @param input substring from user input containing date and time.
     * @return LocalDateTime with the date and time of the input.
     * @throws DateTimeException when the input is an invalid format that cannot be parsed.
     */
    public LocalDateTime parseDateTime(String input) throws DateTimeException {
        String formattedDateTime = dateTimeFormat(input);
        return LocalDateTime.parse(formattedDateTime);
    }

    /**
     * Returns a String in a format that is meant to be stored in the memory.
     *
     * @return String that represents the Task instance in the memory.
     */
    public String dataFormat() {
        assert (!this.taskName.equals(""));
        return this.isDone + "|" + this.taskName;
    }

    /**
     * Returns a String which is a concatenation of if the task is done and the taskName.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return getMark() + " " + this.taskName;
    }
}
