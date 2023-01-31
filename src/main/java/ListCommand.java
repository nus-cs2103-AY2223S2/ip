public class ListCommand extends Command {
    private Ui ui;
    private TaskList taskList;
    public ListCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        if (taskList.isEmpty()) {
            ui.prettyPrint("No tasks found!");
        }
        for (int i = 1; i <= taskList.getSize(); i++) {
            ui.prettyPrint(String.format("%d. %s", i, taskList.get(i - 1)));
        }
    }
}
