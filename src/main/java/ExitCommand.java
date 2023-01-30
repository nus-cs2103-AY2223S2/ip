public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(taskList);
        ui.showMessage("Bye. Hope to see you again soon!");
    }
    public boolean isExit() {
        return true;
    }
}