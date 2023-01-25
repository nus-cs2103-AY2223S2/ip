

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        super(false);
        this.description = description;
        this.by = by;
    }
    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.addDeadline(description, by);
        int size = task.getSize();
        Task temp = task.getTask(size - 1);
        ui.showAdd(temp, size);
    }
}
