package duke.tasks;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }

    @Override
    public String saveString() {
        return String.format("T|%s|%s", super.saveString(), super.description);
    }
}
