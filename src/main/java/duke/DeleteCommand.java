package duke;

import java.io.IOException;

public class DeleteCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] userInputComponents;

    public DeleteCommand (String userInput, TaskList list, Storage storage, String[] userInputComponents) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
        this.userInputComponents = userInputComponents;
    }

    @Override
    public String execute() throws IOException {

        try {
            String firstOutput = list.deleteTask(Integer.parseInt(userInputComponents[1]));
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        } catch (NumberFormatException e) {
            return "You have to specify a number representing the task number!";
        }
    }
}
