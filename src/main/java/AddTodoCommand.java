public class AddTodoCommand extends Command {
    private String name;

    public AddTodoCommand(String name) {
        this.name = name;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addTaskResponse(tasks.addTodo(name), tasks);
    }
}
