public class ByeCommand extends Command {
    Storage storage;
    TaskList taskList;
    Ui ui;
    public ByeCommand(Storage storage, TaskList taskList, Ui ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
    }
    @Override
    public void execute() {
        ui.systemPrint("Saving state, please wait...");
        storage.saveState(taskList.getList());
        ui.systemPrint("State successfully saved.");
        ui.prettyPrint("Hope I helped. Goodbye!");
    }
}
