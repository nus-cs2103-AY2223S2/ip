public class MarkTaskCommand extends Command {
    private final boolean isMarked;
    public MarkTaskCommand(String[] inputArr, boolean isMarked) {
        super(inputArr);
        this.isMarked = isMarked;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(isMarked, inputArr, ui);
        storage.writeData(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
