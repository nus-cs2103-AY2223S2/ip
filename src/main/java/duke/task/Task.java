package duke.task;

import duke.Parser;
import duke.exception.InvalidFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public static final Pattern p = null;
    protected String classIcon;
    protected String description;
    protected boolean isDone;

    /**
     * A factory method that returns a Task based on logfile input
     *
     * @param type of Task
     * @param done isDone boolean value
     * @param text Description [Date] [Date] as String
     * @param p Parser required to create task
     * @return Todo, Event or Date
     * @throws InvalidFormatException
     */
    public static Task factory(char type, char done, String text, Parser p) throws InvalidFormatException {
        Task curr;

        Matcher m;
        switch (type) {
            case 'T':
                curr = new Todo(text);
                break;
            case 'D':
                m = Pattern.compile("(.+) (.+)$").matcher(text);
                if (!m.find()) {
                    throw new InvalidFormatException();
                }
                curr = new Deadline(m.group(1), m.group(2), p);
                break;
            case 'E':
                m = Pattern.compile("(.+) (.+) (.+)$").matcher(text);
                if (!m.find()) {
                    throw new InvalidFormatException();
                }
                curr = new Event(m.group(1), m.group(2), m.group(3), p);
                break;
            default:
                throw new InvalidFormatException();
        }

        if (done == 'X') {
            curr.setDone(true);
        }

        return curr;
    }

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException("task name");
    }

    /**
     * Returns a basic task
     *
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        classIcon = "-";
    }

    /**
     * the the isDone value of the task
     *
     * @param isDone boolean value to set to
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns "X" if done, else " "
     *
     * @return "X" if done, else " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", classIcon, getStatusIcon(), description);
    }

    public String toString(Parser p) {
        return String.format("[%s][%s] %s", classIcon, getStatusIcon(), description);
    }

    /**
     * Converting the task to String for writing to the logfile
     *
     * @param p
     * @return String that can be read in logfile
     */
    public String toLog(Parser p) {
        return toString();
    }

    public String getDescription() {
        return this.description;
    }
}
