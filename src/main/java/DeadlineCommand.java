import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class DeadlineCommand extends Command {

    private final String[] tokens;

    DeadlineCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidDeadlineCommandException {

        int indexOfBy = -1;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equals("/by")) {
                indexOfBy = i;
                break;
            }
        }

        if (indexOfBy == -1) {
            throw new DukeInvalidDeadlineCommandException();
        }

        String[] taskNameArray = Arrays.copyOfRange(tokens, 1, indexOfBy);
        String[] byArray = Arrays.copyOfRange(tokens, indexOfBy + 1, tokens.length);

        if (taskNameArray.length == 0 || byArray.length == 0) {
            throw new DukeInvalidDeadlineCommandException();
        }

        String taskName = String.join(" ", taskNameArray);
        String by = String.join(" ", byArray);

        LocalDate byDate;

        try {
            byDate = LocalDate.parse(by, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDeadlineCommandException();
        }

        DeadlineTask newDeadlineTask = new DeadlineTask(taskName, byDate);
        taskList.addTask(newDeadlineTask);
        ui.showMessage("Added:\n" + newDeadlineTask);
        ui.showNumberOfTasks(taskList.getSize());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
