package duke;

import java.io.IOException;

public class DeadlineCommand extends Command {
    private final String userInput;
    private final TaskList list;
    private final Storage storage;

    private final String[] splitDeadline;

    public DeadlineCommand (String userInput, TaskList list, Storage storage, String[] splitDeadline) {
        this.userInput = userInput;
        this.list = list;
        this.storage = storage;
        this.splitDeadline = splitDeadline;
    }

    @Override
    public String execute() throws IOException {

        try {
            String description = splitDeadline[0].substring(9).strip();
            String deadline = splitDeadline[1];
            String firstOutput = list.addTask(new Deadline(description, deadline));
            String secondOutput = list.getTaskDetails();
            storage.updateStorage();
            return firstOutput + "\n" + secondOutput;
        } catch (InvalidDateFormatException e) {
            return "You have to enter the deadline date in this format: dd-Mmm-yyyy";
        }
    }
}
