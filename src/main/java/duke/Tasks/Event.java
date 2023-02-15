package duke.tasks;

import duke.TaskList;

/**
 * Represents event tasks
 */
public class Event extends Task {
    protected String from;

    protected String to;

    /**
     * Event constructor
     * @param description task name
     * @param from starting time for the task
     * @param to deadline for the task
     * @param isInFile check whether is in the saving file
     */
    public Event(String description, String from, String to, boolean isInFile, TaskList tasks, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;

        if (!isInFile) {
            System.out.println(" " + "____________________________________________________________");
            System.out.println(" Got it. I've added this task:");
            System.out.println("  " + toString());
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(" " + "____________________________________________________________");
        }
    }

    /**
     * Override toString method
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription()
                + " (" + "from: " + this.from + " " + "to: " + this.to + ")";
    }

    /**
     * Override file method, changing into data saving format
     * @return String
     */
    @Override
    public String file() {
        //String status = isDone ? "1" : "0";
        return "E | " + super.file() + " | " + this.from + " | " + this.to;
    }
}
