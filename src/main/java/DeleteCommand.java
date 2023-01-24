public class DeleteCommand implements Command {
    @Override
    public String getCommandName() {
        return "delete";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^delete ([0-9]+)$";
    }

    @Override
    public String getCommandPattern() {
        return "delete <taskNo>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        int taskNo = Integer.parseInt(args[0]);

        if (taskNo > 0 && taskNo <= taskList.getTotalTasks()) {
            Task removedTask = taskList.deleteTask(taskNo);

            ui.printHorizontal();
            ui.printText("Noted. I've removed this task:");
            ui.printText(removedTask.toString());
            ui.printText(String.format("Now you have %d tasks in the list.", taskList.getTotalTasks()));
            ui.printHorizontal();

            storage.save(taskList.getAllTasks());
        } else {
            ui.printHorizontal();
            ui.printText("Invalid task number!");
            ui.printHorizontal();
        }
    }
}
