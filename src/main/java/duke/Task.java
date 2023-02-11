package duke;


import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType = "T";
    static String divider = "    ────────────── ⋆⋅☆⋅⋆ ───────────────";


    public Task(String description) {
        this.description = description;
    }

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }
    

    public abstract String saveFormat();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "]" +  this.description;
    }

    public String getDescription() {
        return description;
    }


}