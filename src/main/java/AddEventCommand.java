import java.time.LocalDate;

public class AddEventCommand extends AddCommand {
    private String description;
    private LocalDate from;
    private LocalDate to;

    public AddEventCommand(Ui ui, TaskList taskList, String description,
                           LocalDate from, LocalDate to) {
        super(ui, taskList);
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        taskList.add(new Event(description, from, to));
        super.printCreatedTaskStatus();
    }
}
