package Duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String desc;
    private boolean done;

    public Task(String desc, boolean done) {
        this.desc = desc;
        this.done = done;
    }

    /**
     * Changes the status of done, true <-> false
     */
    public void toggleDone() {
        this.done = true;
    }

    public void toggleNotDone() {
        this.done = false;
    }

    /**
     * if done, returns String "X" else returns empty string
     * @return string
     */
    public String getDoneStatus(){
        return done ? "X" : " ";
    }

    /**
     * formats input date to "yyyy/MM/dd hh:mm am/pm"
     * @param dateTime LocalDateTime object
     * @return formatted dateTime string
     */
    public String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
        return dateTime.format(dtf);
    }

    public String format24HrDate(LocalDateTime dateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return dateTime.format(dtf);
    }

    /**
     * getter for done
     * @return this.done
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * getter for description
     * @return this.desc
     */
    public String getDesc(){
        return this.desc;
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + this.desc;
    }
}
