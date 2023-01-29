import java.time.LocalDate;

public class EventCommand extends Command {
    private String title;
    private LocalDate from;
    private LocalDate to;

    public EventCommand(String title, LocalDate from, LocalDate to) {
        this.title = title;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newEvent = tasks.addEvent(this.title, this.from, this.to);
        ui.printAddTask(newEvent, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

