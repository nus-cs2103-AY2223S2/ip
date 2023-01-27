package Week2.src.main;

/**
 * A subclass of Task
 * It contains the task content and deadline of each task
 */
public class Deadline extends Task {
    String date;

    /**
     * Deadline constructor.
     * It contains content information and deadline date (e.g. Monday)
     * @param content content of the task
     * @param date date of deadline
     */
    Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    /**
     * It overrides toString() method to change information of task to a string format.
     * @return A string format of given task data
     */
    @Override
    public String toString() {
        return "[D][" + this.getDone() + "] " + this.content + "(by:" + this.date + ")";
    }
}
