package duke.model.task;

import java.time.LocalDate;
import java.util.Objects;

import duke.util.DukeUtils;

public class DeadlineTask extends Task {

    private static final long serialVersionUID = 7701406742949264888L;

    private LocalDate deadline;

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DukeUtils.showDate(deadline));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask task = (DeadlineTask) obj;
        return super.equals(obj) && Objects.equals(deadline, task.deadline);
    }
}
