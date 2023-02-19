package Commands;

import Week2.src.main.Bada;
import Week2.src.main.TaskList;
import java.io.IOException;

/**
 * A subclass of Task
 * It contains the task content and deadline of each task
 * A subclass that extends Task.
 * Deadline contains a deadline which is written following "/by".
 */
public class Deadline extends Task {
    String date;

    /**
     * Deadline constructor.
     * It contains content information and deadline date (e.g. Monday)
     * @param content content of the task
     * @param date date of deadline
     */
    public Deadline(String content, String date) {
        super(content);
        this.date = date;
    }

    public static String execute(String doit, TaskList tasklist) throws IOException {
        String[] parts = doit.split("/by");
        Task current = new Deadline(parts[0], parts[1]);
        tasklist.add(current);
        String str1 = "Got it. I've added this task:";
        String str2 = current.toString();
        String str3 = "Now you have " + tasklist.length() + " tasks in the list";
        return str1 + "\n" + str2 + "\n" + str3;
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
