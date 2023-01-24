public class EventCommand extends Command{
    public String from;
    public String to;
    public String description;
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Event e = new Event(description, from, to);
        tasks.add(e);
        ui.printEvent(tasks, e);

    }

    public boolean isExit() {
        return false;
    }
}
