public class DeleteCommand extends Command{

    private int indexOfTaskToDelete;

    public DeleteCommand(int indexOfTaskToDelete) {
        this.indexOfTaskToDelete = indexOfTaskToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        ui.showAction(tasks.delete(indexOfTaskToDelete));
        ui.showLine();
    }
}
