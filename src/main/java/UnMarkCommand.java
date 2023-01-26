public class UnMarkCommand extends Command {
    private int index;

    public UnMarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        System.out.println(taskList.unMarkTask(this.index));
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
