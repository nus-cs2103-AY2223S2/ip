package duke.task;

import java.time.LocalDate;
import java.util.Objects;

import duke.DukeUtils;

public class DeadlineTask extends Task {

    private static final long serialVersionUID = 7701406742949264888L;

    private LocalDate by;

    public DeadlineTask(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DukeUtils.convertDateToString(by));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeadlineTask)) {
            return false;
        }
        DeadlineTask task = (DeadlineTask) obj;
        return super.equals(obj) && Objects.equals(by, task.by);
    }
}
