package Task;
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean marked) {
        super(description,marked);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
