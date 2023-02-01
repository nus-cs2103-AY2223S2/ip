public class addCommand extends Command {
    private final Task task;

    public addCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(task);
        ui.echo("Got it. I've added this task:");
        ui.showTask(task);
        ui.showTaskCount(tasks);
        storage.save(tasks);
    }
}
