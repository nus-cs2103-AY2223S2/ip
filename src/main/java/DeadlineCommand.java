public class DeadlineCommand extends Command {

    private final String name;
    private final String deadline;

    public DeadlineCommand(String name, String deadline) {
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Deadline dl = new Deadline(name, deadline);
        ui.showConfirmation(tasks.addTask(dl));
        storage.saveToFile(tasks.tasks);
    }

}
