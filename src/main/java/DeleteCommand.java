public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = tasks.deleteTask(this.idx - 1);
        storage.saveTasks(tasks);
        ui.showDelete(taskString, tasks);
    }
}
