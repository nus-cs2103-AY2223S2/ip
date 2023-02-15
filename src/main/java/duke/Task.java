package duke;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a superclass for Deadline,Event and Todo,
 * and also the state of task
 */
public class Task{
    public static List<Task> tasks = new ArrayList<>();
    protected String description;
    protected boolean isDone;
    protected String words;
    static int actions = 0;

    /**
     * The constructor of task
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.words = "";

    }

    /**
     * Returns the state of an action is mark done or not in the saved file
     * @return 1 or 0 based on the state of task
     */
    public String toSaveString() {
        if (isDone) {
            return "1";
        } else {
            return "0";
        }
    }

    /**
     * Returns the state of the task, only in the printed lines
     * @return a string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the string representation of Task
     * @return string representation of Task
     */
    public String toString() {
        if (this.isDone) {
            this.words =  "["+this.getStatusIcon()+"]" + this.description;
            return this.words;
        } else {
            this.words = "["+this.getStatusIcon()+"]" + this.description;
            return this.words;
        }
    }

    /**
     * Prints String when a task is mark done
     */
    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n" +  this);

    }

    public void handleMark() {
        this.isDone= true;
    }

    /**
     * Prints String when a Task is mark not done
     */
    public void unmark() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n"+ this);
    }

    public static void delete(Task task) {
        Task.actions -= 1;
        Task.tasks.remove(task);
        task.printDelete();

    }
    public void printDelete() {
        System.out.println("Noted. I've removed this task: \n" + this);
        System.out.println("Now you have " + Task.actions + " tasks in the list");
    }


}
