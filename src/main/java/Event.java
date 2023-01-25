import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, boolean isDone, String taskType, LocalDateTime from, LocalDateTime to) {
        super(description, isDone, taskType);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, false, "E", from, to);
    }

    public Task markTask() throws DukeException {
        if (super.isDone) {
            throw new DukeException("This task is already marked!");
        }
        System.out.println("Nice! I've marked this task as done:");
        Task markedTask = new Event(super.description, true, super.taskType, this.from, this.to);
        System.out.println(markedTask);
        return markedTask;
    }

    public Task unmarkTask() throws DukeException {
        if (!super.isDone) {
            throw new DukeException("This task is already unmarked!");
        }
        System.out.println("Ok, I've marked this task as not done yet:");
        Task unmarkedTask = new Event(super.description, false, super.taskType, this.from, this.to);
        System.out.println(unmarkedTask);
        return unmarkedTask;
    }

    public String formatTask() {
        return String.format("D|%b|%s|%s|%s", this.isDone, this.description, this.from.toString(), this.to.toString());
    }

    @Override
    public String toString() {
        String timePattern = "d MMM yyyy, HHmm";
        return String.format("%s%s %s (from: %s to: %s)", super.getTaskTypeBox(), super.getStatusCheckbox(),
                super.toString(), this.from.format(DateTimeFormatter.ofPattern(timePattern)),
                this.to.format(DateTimeFormatter.ofPattern(timePattern)));
    }
}
