public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList taskStore, Ui ui, Storage storage) throws DukeException {
        taskStore.addTask(task);
        storage.save(taskStore.createTaskListString());
        ui.showAddTask(task, taskStore);
    }

}
