public class Todo extends Task {
    Todo(String title) throws DukeException {
        super(title);
    }

    Todo(String title, boolean done) throws DukeException {
        super(title, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
