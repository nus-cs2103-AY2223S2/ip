import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public void markDone(){
        this.isDone = true;
    }

    public void markUnDone(){
        this.isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public int isDoneToInt(){
        return isDone ? 1 : 0;
    }
    public static Task loadTask(String data){
        String [] dataSplit = data.trim().split("\\|",4);
        boolean isDone = dataSplit[1].equals("1");
        char taskType = dataSplit[0].charAt(0);
        String description = dataSplit[2].trim();
        switch (taskType) {
        case 'T':
            return new ToDo(description, isDone);
        case 'D':
            return new Deadline(description, dataSplit[3].trim(), isDone);
        case 'E':
            return new Event(description, dataSplit[3].split("to",2)[0],
                    dataSplit[3].split("to",2)[1], isDone);
        default:
            throw new DukeException("☹ OOPS!!! Unrecognised task type!");
        }


    }

    public String saveFormat(){
        return String.format("%d | %s", isDoneToInt(), this.description);
    }

}