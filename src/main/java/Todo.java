public class Todo extends Task {

    public Todo(String description) {
        super(description.trim());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toBackup() {
        return "T | " + super.toBackup() + "\n";
    }
}