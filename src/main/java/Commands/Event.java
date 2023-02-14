package Commands;

import Week2.src.main.Duke;
import Week2.src.main.TaskList;

import java.io.IOException;

/**
 * A subclass that extends Task.
 * Event contains its occuring time (start time and end time)
 */
public class Event extends Task {
    String from;
    String to;

    /**
     * Event constructor
     * @param content content of the task
     * @param from starting time of the task
     * @param to end time of the task
     */
    public Event(String content, String from, String to) {
        super(content);
        this.from = from;
        this.to = to;
    }

    public static String execute(String doit, TaskList tasklist) throws IOException {
        String[] froms = doit.split("/from");
        String[] fromses = froms[1].split("/to");
        String[] tos = doit.split("/to");
        Task current = new Event(froms[0], fromses[0], tos[1]);
        tasklist.add(current);

        String str1 = "Got it. I've added this task:";
        String str2 = current.toString();
        Duke.writeOn(current);

        return str1 + "\n" + str2;
    }

    /**
     * It overrides toString() method to change information to a string format
     * @return String format of the event task data
     */
    @Override
    public String toString() {

        return "[E][" + this.getDone() + "] " + this.content + "(from:" + this.from + " to:" + this.to + ")";

    }
}
