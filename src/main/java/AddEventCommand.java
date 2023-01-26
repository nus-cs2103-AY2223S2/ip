public class AddEventCommand extends Command {
    private String name;
    private String from;
    private String to;

    public AddEventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addTaskResponse(tasks.addEvent(name, from, to), tasks);
    }
}
