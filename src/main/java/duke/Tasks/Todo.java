package duke.tasks;

/**
 * Represents to do tasks
 */
public class Todo extends Task {

    /**
     * the constructor
     * @param description task name
     * @param isInFile check whether is in the saving file
     */
    public Todo(String description, boolean isInFile) {
        super(description);

        if (!isInFile) {
            System.out.println(" " + "____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + toString());
            System.out.println(" Now you have " + taskNum + " tasks in the list.");
            System.out.println(" " + "____________________________________________________________");
        }
    }

    /**
     * Override toString method
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + getDescription();
    }


    /**
     * Override file method, changing into data saving format
     * @return String
     */
    @Override
    public String file() {
        String status = isDone ? "1" : "0";
        return "T | " + status + " | " + getDescription();
    }
}
