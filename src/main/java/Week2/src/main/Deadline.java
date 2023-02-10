package Week2.src.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * A subclass of Task
 * It contains the task content and deadline of each task
 * A subclass that extends Task.
 * Deadline contains a deadline which is written following "/by".
 */
public class Deadline extends Task {
    private String date;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime ldt;

    /**
     * Deadline constructor.
     * It contains content information and deadline date (e.g. Monday)
     * @param content content of the task
     * @param date date of deadline
     * Deadline constructor
     * @param content Task content
     * @param date Deadline date (e.g. Monday)
     */
    Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    /**
     * It overrides toString() method to change information of task to a string format.
     * @return A string format of given task data
     * Overriden toString() method to stringfy the data
     * @return String format of deadline task
     */
    @Override
    public String toString() {
        return "[D][" + this.getDone() + "] " + this.content + "(by:" + this.date + ")";
    }
}
