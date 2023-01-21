package Duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public static void createTodo(TaskList taskList, String desc) {
        Ui.addedTask();
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        Ui.indent("" + todo);
    }

    public static void runTodo(TaskList taskList, String description) {
            createTodo(taskList, description);
            Ui.checkList(taskList);
    }

    @Override
    public String toSave() {
        return "T | " + super.toSave();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
