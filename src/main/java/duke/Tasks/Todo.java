package duke.Tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        System.out.println(" " + "____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + toString());
        System.out.println(" Now you have " + taskNum + " tasks in the list.");
        System.out.println(" " + "____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
}
