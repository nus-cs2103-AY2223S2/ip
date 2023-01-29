package CatBot.TaskList;

public class ToDoTask extends Task {

    public ToDoTask(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCommand() {
        return "todo " + super.description + (super.isDone ? "\nmark 0": "");
    }
}
