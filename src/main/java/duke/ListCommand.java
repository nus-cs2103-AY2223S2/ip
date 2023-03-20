package duke;

import java.io.IOException;

public class ListCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    public ListCommand (String userInput, TaskList list, Storage storage) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
    }

    @Override
    public String execute() throws IOException {

            String firstOutput = list.printItems();
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        }
}

