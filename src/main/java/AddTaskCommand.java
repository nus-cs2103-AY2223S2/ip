public abstract class AddTaskCommand implements Command {
    protected void addTask(Task task, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        ui.printHorizontal();

        taskList.addTask(task);
        ui.printText("Got it. I've added this task:");
        ui.printText(String.format("  %s", task.toString()));
        ui.printText(String.format("Now you have %d tasks in the list.", taskList.getTotalTasks()));

        ui.printHorizontal();

        storage.save(taskList.getAllTasks());
    }
}
