package duke.command;

import duke.TaskList;

public class ListCommand extends Command {
    public ListCommand(String userInput) {
        super(userInput);
    }

    @Override
    public String execute(TaskList tasks) {
        String returnMessage = "";
        int taskNumber = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            String task = taskNumber + ". " + tasks.getTask(i).toString() + '\n';
            returnMessage += task;
            taskNumber++;
        }
        return returnMessage;
    }
}
