package features.event_manager;

public class Deadline extends Task {
    Deadline(String name, boolean isComplete) {
        super(name, isComplete);
    }

    Deadline(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
