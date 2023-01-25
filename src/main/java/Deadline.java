import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    // for init of Deadline object from Duke.java
    public Deadline(String description, LocalDateTime deadline) {
        this(description, false, "D", deadline);
    }

    // for internal use
    public Deadline(String description, boolean isDone, String taskType, LocalDateTime deadline) {
        super(description, isDone, taskType);
        this.deadline = deadline;
    }

    public Task markTask() throws DukeException {
        if (super.isDone) {
            throw new DukeException("This task is already marked!");
        }
        System.out.println("Nice! I've marked this task as done:");
        Task markedTask = new Deadline(super.description, true, super.taskType, this.deadline);
        System.out.println(markedTask);
        return markedTask;
    }

    public Task unmarkTask() throws DukeException {
        if (!super.isDone) {
            throw new DukeException("This task is already unmarked!");
        }
        System.out.println("Ok, I've marked this task as not done yet:");
        Task unmarkedTask = new Deadline(super.description, false, super.taskType, this.deadline);
        System.out.println(unmarkedTask);
        return unmarkedTask;
    }

    public String formatTask() {
        return String.format("D|%b|%s|%s", this.isDone, this.description, this.deadline.toString());
    }

    @Override
    public String toString() {
        String timePattern = "d MMM yyyy, HHmm";
        return String.format("%s%s %s (by: %s)", super.getTaskTypeBox(), super.getStatusCheckbox(),
                super.toString(), this.deadline.format(DateTimeFormatter.ofPattern(timePattern)));
    }
}
