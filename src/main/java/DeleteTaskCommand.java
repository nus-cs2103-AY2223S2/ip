public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String inputArr) {
        super(inputArr);
    }

    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(this.getInputArr(), ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}