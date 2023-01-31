public abstract class AddCommand extends Command {
    protected Ui ui;
    protected TaskList taskList;

    public AddCommand(Ui ui, TaskList taskList) {
        this.ui= ui;
        this.taskList = taskList;
    }

    protected void printCreatedTaskStatus() {
        ui.prettyPrint("Got it! I've added this task:");
        ui.prettyPrint(taskList.getLastTask().toString());
        ui.prettyPrint(String.format("Now you have %d task%s in the list.",
                taskList.getSize(), taskList.getSize() == 1 ? "" : "s"));
    }
}
