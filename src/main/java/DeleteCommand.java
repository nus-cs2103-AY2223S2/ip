public class DeleteCommand extends Command {
    private int taskNum;

    DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 1 || taskNum >= tasks.size() + 1) {
            throw new DukeException("\u2639 OOPS!!! The index to mark as done cannot be less than 0 or "
                    + "greater than the length of the list.");
        }

        Task deletedTask = tasks.get(taskNum - 1);
        tasks.delete(taskNum - 1);
        Ui.ShowDeleteMessage(deletedTask, tasks.size());
        storage.save(tasks.getAllTasks());
    }
}