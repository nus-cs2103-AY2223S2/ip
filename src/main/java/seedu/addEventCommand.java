package seedu;

public class addEventCommand extends Command {
    private Event event;

    public addEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute() throws JamesException {
        taskList.addToTaskList(event);
        ui.addTask(event, taskList.getSize());
    }
}