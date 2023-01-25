public class EventCommand extends Command {
    private String content;

    public EventCommand(String content) {
        this.content = content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (!content.matches("^.+(\\s)/from(\\s).+(\\s)/to.*$")) {
            throw new DukeException(
                "Please use the correct format to add an event.");
        }
        String[] eventTask = content.split("/from|/to");
        eventTask[0] = eventTask[0].trim();
        eventTask[1] = eventTask[1].trim();
        eventTask[2] = eventTask[2].trim();
        try {
            tasks.add(eventTask[0], eventTask[1], eventTask[2], true);
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
