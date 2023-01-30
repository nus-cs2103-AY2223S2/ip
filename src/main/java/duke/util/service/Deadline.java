package duke.util.service;

import duke.util.Task;
public class Deadline extends Task {
    String deadline;
    public Deadline(String deadline, String action) {
        super("D", action);
        this.deadline = deadline;
    }

    @Override
    public String getAdditionalInfo() {
        return " (BY: " + this.deadline + ")";
    }

    @Override
    public String toString() {
        return super.toString() + " (BY: " + this.deadline + ")";
    }
}