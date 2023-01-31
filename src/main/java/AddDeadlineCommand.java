import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    private String description;
    private LocalDate by;
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.add(new Deadline(description, by));
        super.printCreatedTaskStatus(taskList, ui);
    }
}
