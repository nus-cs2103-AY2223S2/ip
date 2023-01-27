public class ResetCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, TaskStore taskStore) throws IrisException {
        tasks.clear();
        taskStore.reset();
        Ui.output("Your task list has been resetted. You have no commands.");
    }
}
