package duke;

import java.io.IOException;
public class FindCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] userInputComponents;

    public FindCommand (String userInput, TaskList list, Storage storage, String[] userInputComponents) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
        this.userInputComponents = userInputComponents;
    }

    @Override
    public String execute() throws IOException {

        String keyword = userInputComponents[1].strip();
        String firstOutput = list.findTask(keyword);
        return firstOutput;
    }
}
