public class EventCommand extends Command {
    private static final String EVENT_COMMAND = "event";
    private final Event event;

    public EventCommand(Event event) {
        super(EVENT_COMMAND);
        this.event = event;
    }

    @Override
    public void execute(TaskList lst, Ui ui) {
        lst.addTask(this.event);
        ui.showEvent(this.event, lst.getSize());
    }
}
