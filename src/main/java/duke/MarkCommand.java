package duke;

import java.io.IOException;

/**
 * Class that calls that relevent methods for the Mark command when the user enters this command.
 */
public class MarkCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] userInputComponents;


    /**
     * Constructor for a MarkCommand
     * @param userInput The relevant details entered in by the user
     * @param list The TaskList object that stores the user's tasks
     * @param storage The instance of the Storage class that handles reading from and writing to the data file
     * @param userInputComponents A String array that contains the different details entered by the user
     */
    public MarkCommand (String userInput, TaskList list, Storage storage, String[] userInputComponents) {
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
            String firstOutput = list.markDone(taskNumber);
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        } catch (NumberFormatException e) {
            return "You have to specify a number representing the task number!";
        }
    }

}

