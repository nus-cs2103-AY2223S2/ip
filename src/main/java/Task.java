import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    private final String separator = "____________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void taskDone() {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println(separator);
        isDone = true;
    }

    public void taskNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println(separator);
        isDone = false;
    }

    public void announceAdded(ArrayList<Task> myTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("Now we have " + myTask.size() + " tasks in the list.");
        System.out.println(separator);
    }

    public void announceRemoved(ArrayList<Task> myTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(this.toString());
        System.out.println("Now we have " + myTask.size() + " tasks in the list.");
    }


}

