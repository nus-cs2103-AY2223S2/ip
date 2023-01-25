package duke.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isInFile) {
        super(description);
        this.by = by;

        if (!isInFile) {
            System.out.println(" " + "____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + toString());
            System.out.println(" Now you have " + taskNum + " tasks in the list.");
            System.out.println(" " + "____________________________________________________________");
        }
    }
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (" + "by: " + this.by+ ")";
    }
    @Override
    public String file() {
        String status = isDone? "1" : "0";
        return "D | " + status + " | " + getDescription() + " | "
                + this.by;
    }
}
