package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private final String desc;
    private boolean isDone;

    public Task(String desc, boolean isDone) {
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Changes the status of done, true <-> false
     */
    public void toggleDone() {
        this.isDone = true;
    }

    public void toggleNotDone() {
        this.isDone = false;
    }

    /**
     * if done, returns String "X" else returns empty string
     * @return string
     */
    public String getDoneStatus(){
        return isDone ? "X" : " ";
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
        return this.isDone;
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
