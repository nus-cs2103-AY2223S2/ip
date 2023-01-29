public class AddTodoCommand extends Command {
    private String data;

    public AddTodoCommand(String commandString, String data) {
        super(Commands.ADD_TODO, commandString);
        this.data = data;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(this.data);
        tasks.addTask(todo);

        ui.showAddTask(todo.toString(), tasks.size());
    }
}
