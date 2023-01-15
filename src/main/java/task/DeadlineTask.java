package task;

import exception.MissingParameterException;

public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String description, String deadline) throws MissingParameterException {
        super(description);
        if (deadline == null || deadline.isBlank()) {
            throw new MissingParameterException("Missing deadline", "A deadline ('/by ...') is needed.");
        }
        this.deadline = deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline == null ? "[none]" : this.deadline
        );
    }
}
