public class Todo extends Task {
    Todo(String title) throws DukeException {
        super(title);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
