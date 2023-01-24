package duke.Tasks;

public class Todo extends Task {
    public Todo(String description, boolean isInFile) {
        super(description);
        if (isInFile == false) {
            System.out.println(" " + "____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + toString());
            System.out.println(" Now you have " + taskNum + " tasks in the list.");
            System.out.println(" " + "____________________________________________________________");
        }
    }
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }
    @Override
    public String file() {
        String status = isDone? "1" : "0";
        return "T | " + status + " | " + getDescription();
    }
}
