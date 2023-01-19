public class ToDoTask extends Task{
    ToDoTask(String name) throws DukeException {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
