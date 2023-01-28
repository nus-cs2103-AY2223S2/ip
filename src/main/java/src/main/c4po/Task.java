package src.main.c4po;
import java.util.regex.Pattern;

public class Task {
    protected String description;
    protected boolean isDone;



    protected static Integer listSize = 0; //or just increment accordingly

    protected static String delimiter = Pattern.quote(" | ");
    public Integer index;

    //Constructor
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.index = listSize + 1;
        //add to taskList
        listSize++;
    }







    protected String getTaskFileFormat() {
        String statusIcon = this.getStatusIcon();
        String isDone = statusIcon.equals("X") ? "1" : "0";
        return isDone + " | " + this.description;
    }



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




    //Get inline print of task description with specified index
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
