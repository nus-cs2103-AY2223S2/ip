import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends AddTaskCommand {
    @Override
    public String getCommandName() {
        return "deadline";
    }

    @Override
    public String getCommandRegexPattern() {
        return "deadline (.*) \\/by (.*)";
    }

    @Override
    public String getCommandPattern() {
        return "deadline <description> /by <deadline>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String description = args[0];
        String by = args[1];

        try {
            LocalDateTime byDateTime = LocalDateTime.parse(by, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
            addTask(new Deadline(description, byDateTime), ui, taskList, storage);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter date and time in this format: dd/MM/yyyy HH:mm");
        }
    }
}
