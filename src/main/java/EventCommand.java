import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends AddTaskCommand {
    @Override
    public String getCommandName() {
        return "event";
    }

    @Override
    public String getCommandRegexPattern() {
        return "^event (.*) \\/from (.*) \\/to (.*)$";
    }

    @Override
    public String getCommandPattern() {
        return "event <description> /from <from> /to <to>";
    }

    @Override
    public void run(String[] args, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        String description = args[0];
        String from = args[1];
        String to = args[2];

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeUtils.DATE_TIME_FORMAT_INPUT);
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeUtils.DATE_TIME_FORMAT_INPUT);

            addTask(new Event(description, fromDateTime, toDateTime), ui, taskList, storage);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter date and time in this format: dd/MM/yyyy HH:mm");
        }
    }
}
