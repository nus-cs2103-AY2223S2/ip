package features.event_manager;

public class Event extends Task {
    Event(String name, boolean isComplete) {
        super(name, isComplete);
    }

    Event(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
