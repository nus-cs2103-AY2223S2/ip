import java.util.ArrayList;

public class Task {

    private static ArrayList<Task> tasks = new ArrayList<>();
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void addTask(Task t){
        tasks.add(t);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Boolean markAsDone() {
        this.isDone = true;
        return isDone;
    }

    public Boolean markAsUndone() {
        this.isDone = false;
        return isDone;
    }

    public void wording(String str){
        // converting string to integer
        if (str.equals("mark")){
            this.markAsDone();
            System.out.println("Dubzzzz! I've marked this task as done:");
            System.out.println("  " + "[" + this.getStatusIcon() + "] " + this.description);
        } else if (str.equals("unmark")){
            this.markAsUndone();
            System.out.println("Aites cool, I've marked this task as not done yet:");
            System.out.println("  " + "[" + this.getStatusIcon() + "] " + this.description);
        }
    }

    public String toString() {
        return this.description.toString();
    }
}