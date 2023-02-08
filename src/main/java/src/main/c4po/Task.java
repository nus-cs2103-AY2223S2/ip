package src.main.c4po;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    protected LocalDateTime timeCreated;

    protected Integer priority;

    protected static Integer listSize = 0; //or just increment accordingly

    protected static String delimiter = Pattern.quote(" | ");
    public Integer index;

    /**
     * Task item with a description and a boolean representing the done status of the task
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone, Integer priority) {
        this.description = description;
        this.isDone = isDone;
        this.index = listSize + 1;
        //add to taskList
        listSize++;
        this.timeCreated = LocalDateTime.now();

        this.priority = priority;
    }

//    /**
//     * Task item with a description and a boolean representing the done status of the task
//     * Default priority of 0
//     * @param description
//     * @param isDone
//     */
//    public Task(String description, boolean isDone) {
//        this.description = description;
//        this.isDone = isDone;
//        this.index = listSize + 1;
//        //add to taskList
//        listSize++;
//        this.timeCreated = LocalDateTime.now();
//        this.priority = 0;
//    }

    private Integer getPriority() {
        return this.priority;
    }

    protected LocalDateTime getTimeCreated() {
        return this.timeCreated;
    }


    /**
     * Returns a String formatted for writing to file, using vertical bar as
     * the delimiter
     * @return
     */
    protected String getTaskFileFormat() {
        String statusIcon = this.getStatusIcon();
        String isDone = statusIcon.equals("X") ? "1" : "0";
        return this.getPriority().toString() + " | " + isDone + " | " + this.description;
    }


    /**
     * Returns the done status of the Task
     * @return a string of "X" for done and " " if undone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Mark as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a ArrayList of Strings of the task's description split into keywords
     * @return
     */
    protected ArrayList<String> getDescInArray() {
        String str = this.description;
        String[] stringArr = str.split(" ");

        return new ArrayList<>(List.of(stringArr));
    }




    /**
     * Returns an inline String that details the task type and the
     * task details
     * @return String
     */
    public String getTaskInline(Integer index) {
        return index.toString() + ". " + this.getTaskInline();
    }

    public String getTaskInline() {
        String statusIcon = this.getStatusIcon();
        return "[" + statusIcon + "] " + this.description + " (priority: " + this.getPriority().toString() + ")";
    }

    public String toString() {
        return this.description + " (priority: " + this.getPriority().toString() + ")";
    }


    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Task o) {
        return -Integer.compare(this.getPriority(), o.getPriority());
    }
}
