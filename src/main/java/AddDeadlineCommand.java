public class AddDeadlineCommand extends Command{
    private String name;
    private String by;

    public AddDeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addTaskResponse(tasks.addDeadline(name, by), tasks);
    }
}
