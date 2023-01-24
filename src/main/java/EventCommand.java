import java.util.Arrays;

public class EventCommand extends Command {

    private final String[] tokens;

    EventCommand(String[] tokens) {
        this.tokens = tokens;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws DukeInvalidEventCommandException {

        int indexOfFrom = -1;
        int indexOfTo = -1;

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (token.equals("/from")) {
                indexOfFrom = i;
            } else if (token.equals("/to")) {
                indexOfTo = i;
            }
        }

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new DukeInvalidEventCommandException();
        }

        String[] taskNameArray = Arrays.copyOfRange(tokens, 1, indexOfFrom);
        String[] fromArray = Arrays.copyOfRange(tokens, indexOfFrom + 1, indexOfTo);
        String[] toArray = Arrays.copyOfRange(tokens, indexOfTo + 1, tokens.length);

        if (taskNameArray.length == 0 || fromArray.length == 0 || toArray.length == 0) {
            throw new DukeInvalidEventCommandException();
        }

        String taskName = String.join(" ", taskNameArray);
        String from = String.join(" ", fromArray);
        String to = String.join(" ", toArray);

        EventTask newEventTask = new EventTask(taskName, from, to);

        taskList.addTask(newEventTask);
        ui.showMessage("Added:\n" + newEventTask);
        ui.showNumberOfTasks(taskList.getSize());
        storage.saveTaskList(taskList);
    }

    public boolean isByeCommand() {
        return false;
    }
}
