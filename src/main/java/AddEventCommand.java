public class AddEventCommand extends Command {
    private Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute() throws DukeException {
        taskList.addTask(event);
        ui.printTaskAdded(event, taskList.getSize());
    }
}