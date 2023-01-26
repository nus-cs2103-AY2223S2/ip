public class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = tasks.unmarkTask(this.idx - 1);
        storage.saveTasks(tasks);
        ui.showUnmark(taskString, tasks);
    }
}
