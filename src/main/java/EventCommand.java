public class EventCommand extends Command {
    String description;
    String from;
    String by;
    public EventCommand(String description, String from, String by) {
        super(false);
        this.description = description;
        this.from = from;
        this.by = by;
    }

    @Override
    public void execute(TaskList task, Storage storage, Ui ui) throws DukeException {
        task.addEvent(description,from,by);
        int size = task.getSize();
        Task temp = task.getTask(size - 1);
        ui.showAdd(temp, size);
    }
}
