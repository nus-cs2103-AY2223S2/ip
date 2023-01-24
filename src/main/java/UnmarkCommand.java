public class UnmarkCommand implements Command {
    @Override
    public String getCommandName() {
        return "unmark";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^unmark ([0-9]+)$";
    }

    @Override
    public String getCommandPattern() {
        return "unmark <taskNo>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        int taskNo = Integer.parseInt(args[0]);

        if (taskNo > 0 && taskNo <= taskList.getTotalTasks()) {
            Task task = taskList.getTask(taskNo);
            task.setIsDone(false);

            ui.printHorizontal();
            ui.printText("OK, I've marked this task as not done yet:");
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
