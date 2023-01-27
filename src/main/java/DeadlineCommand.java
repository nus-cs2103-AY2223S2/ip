import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    private String deadlineMessage;
    private String deadline;
    public DeadlineCommand(String deadlineMessage, String deadline) {
        this.deadlineMessage = deadlineMessage;
        this.deadline = deadline;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {

        deadline = formatDate(deadline);
        Task curr = new Deadline(deadlineMessage, deadline);
        tasks.addTask(curr);
        ui.showAddTaskMessage(tasks, curr);

    }

    public static String formatDate(String dateString) {
        LocalDate d = null;
        String result = dateString;
        try {
            d = LocalDate.parse(dateString);
            result = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e){
            return result;
        }
        return result;
    }

    @Override
    public String toString() {
        return DeadlineCommand.COMMAND_WORD;
    }
}
