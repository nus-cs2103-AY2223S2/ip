package tasks;
/**
 * ToDos are tasks without any date/time associated
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDataString() {
        return "T | " + super.getDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}