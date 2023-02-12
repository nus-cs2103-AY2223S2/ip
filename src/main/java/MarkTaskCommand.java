public class MarkTaskCommand extends Command {

    private final boolean isMarked;

    public MarkTaskCommand(String inputArr, boolean isMarked) {
        super(inputArr);
        this.isMarked = isMarked;
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(isMarked, this.getInputArr(), ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}