package duke;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] userInputComponents;

    public UnmarkCommand (String userInput, TaskList list, Storage storage, String[] userInputComponents) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
        this.userInputComponents = userInputComponents;
    }

    @Override
    public String execute() throws IOException {
        try {
            int taskNumber = Integer.parseInt(userInputComponents[1]);
            assert taskNumber < list.getNumberOfTasks();
            String firstOutput = list.markUndone(taskNumber);
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        } catch (NumberFormatException e) {
            return "You have to specify a number representing the task number!";
        }
    }
}
