public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.unmarkTask(index);
        ui.showUnmark(task.getTask(index));
    }
}
