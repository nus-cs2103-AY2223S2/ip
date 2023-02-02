package duke.tasks;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return super.getStatusIcon();
    }

    @Override
    public String saveString() {
        return String.format("T|%s|%s", super.saveString(), super.description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
