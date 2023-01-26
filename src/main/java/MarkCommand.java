public class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = tasks.markTask(this.idx - 1);
        storage.saveTasks(tasks);
        ui.showMark(taskString, tasks);
    }
}
