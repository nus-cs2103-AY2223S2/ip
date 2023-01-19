package features.event_manager;

public class ToDo extends Task {
    ToDo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
