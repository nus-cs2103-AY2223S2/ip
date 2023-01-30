package src.main.c4po;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;



    protected static Integer listSize = 0; //or just increment accordingly

    protected static String delimiter = Pattern.quote(" | ");
    public Integer index;

    /**
     * Task item with a description and a boolean representing the done status of the task
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.index = listSize + 1;
        //add to taskList
        listSize++;
    }


    /**
     * Returns a String formatted for writing to file, using vertical bar as
     * the delimiter
     * @return
     */
    protected String getTaskFileFormat() {
        String statusIcon = this.getStatusIcon();
        String isDone = statusIcon.equals("X") ? "1" : "0";
        return isDone + " | " + this.description;
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
        return "[" + statusIcon + "] " + this.description;
    }

    public String toString() {
        return this.description;
    }





}
