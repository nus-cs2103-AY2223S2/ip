package duke;

import java.io.IOException;

public class TodoCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] userInputComponents;

    public TodoCommand (String userInput, TaskList list, Storage storage, String[] userInputComponents) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
        this.userInputComponents = userInputComponents;
    }

    @Override
    public String execute() throws IOException {

        try {
            assert userInput.length() > 5;
            String firstOutput = list.addTask(new ToDo(userInput.substring(5).strip()));
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        } catch (StringIndexOutOfBoundsException e) {
            return "The description of todo cannot be empty!";
        }
    }
}
