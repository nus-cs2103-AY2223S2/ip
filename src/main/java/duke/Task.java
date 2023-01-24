package duke;

import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    public static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy (EEE)");
    public static final Pattern p = null;
    protected String classIcon;
    protected String description;
    protected boolean isDone;

    protected static Task factory(char type, char done, String text) {
        Task curr;

        Matcher m;
        switch (type) {
            case 'T':
                curr = new Todo(text);
                break;
            case 'D':
                m = Deadline.p.matcher(text);
                m.find();
                curr = new Deadline(m.group(1), m.group(2));
                break;
            case 'E':
                m = Event.p.matcher(text);
                m.find();
                curr = new Event(m.group(1), m.group(2), m.group(3));
                break;
            default:
                //Error here, maybe enum?
                return null;
        }

        if (done == 'X') {
            curr.setDone(true);
        }

        return curr;
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

    public String toLog() {
        return toString();
    }
}
