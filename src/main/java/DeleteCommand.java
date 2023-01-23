public class DeleteCommand extends Command {
    private int taskNum;
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskException {
        if (this.taskNum < 0 || this.taskNum >= tasks.getTasksNum()) {
            throw new InvalidTaskException();
        } else {
            Task currentTask = tasks.deleteTask(this.taskNum);
            ui.showMesssage("Noted. I've removed this task:\n " + currentTask + "\n"
                    + "Now you have " + tasks.getTasksNum() + " tasks in the list");
        }
    }
}
