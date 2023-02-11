package duke;


import java.util.ArrayList;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType = "T";
    static String divider = "    ────────────── ⋆⋅☆⋅⋆ ───────────────";


    public Task(String description) {
        this.description = description;
        this.isDone = isDone;
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


    public static void unmarkTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1])-1)).markAsUnDone();
        Ui.unmarkTask(array, splitInput);
    }



    public static void markTask(ArrayList<Task> array, String[] splitInput) {
        array.get((Integer.parseInt(splitInput[1])-1)).markAsDone();
        Ui.markTask(array, splitInput);
    }

  

    public static void deleteTask(ArrayList<Task> array, String[] splitInput) {
        array.remove((Integer.parseInt(splitInput[1])-1));
        Ui.removeTask(array, splitInput);
    }


}