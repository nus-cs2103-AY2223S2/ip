public class ListCommand implements Command {
    @Override
    public String getCommandName() {
        return "list";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^list$";
    }

    @Override
    public String getCommandPattern() {
        return "list";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) {
        ui.printHorizontal();
        ui.printText("Here are the tasks in your list:");
        for (int taskNo = 1; taskNo <= taskList.getTotalTasks(); taskNo++) {
            ui.printText(String.format("%d. %s", taskNo, taskList.getTask(taskNo).toString()));
        }
        ui.printHorizontal();
    }
}
