package duke;
import java.util.ArrayList;
import java.util.List;

public class Task{
    public static List<Task> tasks = new ArrayList<>();
    protected String description;
    protected boolean isDone;
    protected String words;
    static int actions = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.words = "";

    }

    public String toSaveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    public String toString() {
        if (this.isDone) {
            this.words =  "["+this.getStatusIcon()+"]" + this.description;
            return this.words;
        } else {
            this.words = "["+this.getStatusIcon()+"]" + this.description;
            return this.words;
        }
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +  this);

    }

    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n"+ this);
    }
}
