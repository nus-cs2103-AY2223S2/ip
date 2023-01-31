package AddTasks;

import java.util.ArrayList;

public class Task {

    private static ArrayList<Task> tasks = new ArrayList<>();
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public void wording(String str) {
        // converting string to integer
        if (str.equals("mark")) {
            this.markAsDone();
            System.out.println("Okay! I've marked this task as done:");
            System.out.println(this);
        } else if (str.equals("unmark")) {
            this.markAsUndone();
            System.out.println("Aites cool, I've marked this task as not done yet:");
            System.out.println(this);
        }
    }

    public String toString() {
        return this.description;
    }
}