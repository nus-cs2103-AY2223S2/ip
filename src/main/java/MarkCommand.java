public class MarkCommand implements Command {
    @Override
    public String getCommandName() {
        return "mark";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^mark ([0-9]+)$";
    }

    @Override
    public String getCommandPattern() {
        return "mark <taskNo>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        int taskNo = Integer.parseInt(args[0]);

        if (taskNo > 0 && taskNo <= taskList.getTotalTasks()) {
            Task task = taskList.getTask(taskNo);
            task.setIsDone(true);

            ui.printHorizontal();
            ui.printText("Nice! I've marked this task as done:");
            ui.printText(task.toString());
            ui.printHorizontal();

            storage.save(taskList.getAllTasks());
        } else {
            ui.printHorizontal();
            ui.printText("Invalid task number!");
            ui.printHorizontal();
        }
    }
}
