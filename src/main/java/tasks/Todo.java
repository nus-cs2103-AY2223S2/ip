package tasks;

public class Todo extends ITask {

    public Todo(String description) {
        super(description, TaskTypes.ToDos);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
