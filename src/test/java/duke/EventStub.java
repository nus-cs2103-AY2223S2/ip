package duke;

import duke.task.Task;

public class EventStub extends Task {
    private boolean isDone;

    public EventStub(String description) {
        super(TaskType.EVENT, description);
        isDone = false;
    }

    @Override
    public String taskToSavedForm() {
        return "event competition /from 22/10/2022 0800 /to 22/10/2022 1700";
    }

    @Override
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public void mark() {
        this.isDone = true;
    }

    @Override
    public void unMark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] competition (from: Oct 22 2022 0800 to: Oct 22 2022 1700)";
    }
}
