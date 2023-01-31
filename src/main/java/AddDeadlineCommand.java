import java.time.LocalDate;

public class AddDeadlineCommand extends AddCommand {
    private String description;
    private LocalDate by;
    public AddDeadlineCommand(Ui ui, TaskList taskList, String description, LocalDate by) {
        super(ui, taskList);
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute() {
        taskList.add(new Deadline(description, by));
        super.printCreatedTaskStatus();
    }
}
