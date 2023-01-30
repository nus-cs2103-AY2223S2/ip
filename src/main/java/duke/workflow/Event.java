package duke.workflow;

import duke.util.TaskList;

public abstract class Event {
    boolean isFinalEvent;

    public Event (boolean isFinalEvent) {
        this.isFinalEvent = isFinalEvent;
    }
    public abstract Event toNext();

    public abstract TaskList getTaskList();

    public boolean getStatus() {
        return this.isFinalEvent;
    }
}