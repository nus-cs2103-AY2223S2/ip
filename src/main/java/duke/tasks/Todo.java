package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getTimeline() {
        return "";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}