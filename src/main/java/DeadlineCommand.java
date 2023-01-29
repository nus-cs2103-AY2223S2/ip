import java.time.LocalDate;

public class DeadlineCommand extends Command {
    private String title;
    private LocalDate by;

    public DeadlineCommand(String title, LocalDate by) {
        this.title = title;
        this.by = by;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newDeadline = tasks.addDeadline(this.title, this.by);
        ui.printAddTask(newDeadline, tasks.getTaskCount());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
