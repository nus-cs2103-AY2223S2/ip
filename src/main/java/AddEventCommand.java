import java.time.LocalDate;

public class AddEventCommand extends AddCommand {
    private String description;
    private LocalDate from;
    private LocalDate to;

    public AddEventCommand(String description, LocalDate from, LocalDate to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.add(new Event(description, from, to));
        super.printCreatedTaskStatus(taskList, ui);
    }
}
