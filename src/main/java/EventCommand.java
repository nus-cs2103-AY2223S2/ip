public class EventCommand extends Command {

    private final String name;
    private final String from;
    private final String until;

    public EventCommand(String name, String from, String until) {
        this.name = name;
        this.from = from;
        this.until = until;
    }
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        Event ev = new Event(name, from, until);
        ui.showConfirmation(tasks.addTask(ev));
        storage.saveToFile(tasks.tasks);
    }
}
