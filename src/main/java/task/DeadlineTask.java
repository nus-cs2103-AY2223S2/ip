package task;

import exception.MissingParameterException;

public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(String description, String deadline) throws MissingParameterException {
        this(description, deadline, false);
    }

    public DeadlineTask(String description, String deadline, boolean isDone) throws MissingParameterException {
        super(description, isDone);
        if (deadline == null || deadline.isBlank()) {
            throw new MissingParameterException("Missing deadline", "A deadline ('/by ...') is needed.");
        }
        this.deadline = deadline;
    }

    @Override
    public String serialize() {
        String[] data = {"D", String.valueOf(this.isDone()), this.getDescription(), this.deadline};
        return String.join(" / ", data);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
