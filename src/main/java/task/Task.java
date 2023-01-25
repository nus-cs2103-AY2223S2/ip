package task;
import util.DukeException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        //mark done task with X
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone(){
        isDone = false;
    }

    public String dateFormatter(String str) throws DukeException {
        //"d/M/y H:mm" for auto detection of AM/PM d/M/yy h:mma for manual but in 12hr time
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("d/M/yy Hmm"));
            String dt = localDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a"));
            return dt;
        } catch (DateTimeException e) {
            throw new DukeException("Please enter date in dd/mm/yy and time in hhmm 24hr format!");
        }
    }

    @Override
    public String toString() {
        String str = "[" + this.getStatusIcon() + "] " + this.description;
        return str;
    }
}
