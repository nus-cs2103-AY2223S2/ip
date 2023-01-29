public class TodoCommand extends Command {
    private String title;

    public TodoCommand(String title) {
        this.title = title;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTodo = tasks.addTodo(title);
        ui.printAddTask(newTodo, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
