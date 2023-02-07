package duke.task;

import java.util.Objects;

public class EventTask extends Task {

    private static final long serialVersionUID = -150197333726686918L;

    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof EventTask)) {
            return false;
        }
        EventTask task = (EventTask) obj;
        return super.equals(obj) && Objects.equals(from, task.from) && Objects.equals(to, task.to);
    }
}
