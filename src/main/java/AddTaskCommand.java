public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showAdd();
        tasks.addTask(task);
        storage.saveTasks(tasks);
    }
}

