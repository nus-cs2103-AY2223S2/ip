package duke.task;

import java.time.LocalDate;
import java.util.Objects;

import duke.DukeUtils;
import duke.exception.DukeIllegalArgumentException;

public class EventTask extends Task {

    private static final long serialVersionUID = -150197333726686918L;

    private LocalDate startTime;
    private LocalDate endTime;

    public EventTask(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        if (startTime.isAfter(endTime)) {
            throw new DukeIllegalArgumentException("start time cannot be after end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                DukeUtils.showDate(startTime), DukeUtils.showDate(endTime));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask task = (EventTask) obj;
        return super.equals(obj) && Objects.equals(startTime, task.startTime)
                && Objects.equals(endTime, task.endTime);
    }
}
