package Week2.src.main;

/**
 * A subclass that extends Task.
 * Deadline contains a deadline which is written following "/by".
 */
public class Deadline extends Task {
    String date;

    /**
     * Deadline constructor
     * @param content Task content
     * @param date Deadline date (e.g. Monday)
     */
    Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    /**
     * Overriden toString() method to stringfy the data
     * @return String format of deadline task
     */
    @Override
    public String toString() {
        return "[D][" +this.getDone()+ "] " +this.content+ "(by:" +this.date+ ")";
    }
}
