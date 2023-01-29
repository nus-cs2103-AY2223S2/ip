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

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        classIcon = "-";
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

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

    public String toLog(Parser p) {
        return toString();
    }

}
