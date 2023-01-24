package duke.tasks;

public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }

    public String toDukeFileString() {
        return "T|" + super.toDukeFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
