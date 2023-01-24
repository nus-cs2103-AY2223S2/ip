import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public AddEventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = new EventTask(description, startDateTime, endDateTime);
        taskList.add(task);
        ui.printMessage("added: " + task);
    }
}
