import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DueDateCommand extends Command {
    private static DateTimeFormatter dueDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String dueDateString;

    public DueDateCommand(String dueDateString) {
        this.dueDateString = dueDateString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            LocalDate dueDate = LocalDate.parse(dueDateString, dueDateFormatter);
            ui.showMesssage(tasks.getTasksOnDateList(dueDate));
        } catch (DateTimeParseException e) {
            ui.showError("Due date must be in the format yyyy-MM-dd, e.g. 2023-01-23");
        }
    }
}
