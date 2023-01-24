import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    public AddDeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new DeadlineTask(description, dateTime);
        taskList.add(task);
        ui.printMessage("added: " + task);
    }
}
