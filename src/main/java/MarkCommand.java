public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println(taskList.markTask(this.index));
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
