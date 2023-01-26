public class Todo extends Task{
    Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T||" + super.toSaveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
