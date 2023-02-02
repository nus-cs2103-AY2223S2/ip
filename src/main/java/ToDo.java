public class ToDo extends Task {
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public ToDo(String description) {
        this(description, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveAsStr() {
        return "T" + super.saveAsStr();
    }
}
