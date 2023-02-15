package duke.commands;

import duke.Ui;
import duke.exceptions.IncorrectIndexException;
import duke.task.Task;
import duke.task.TaskList;

public class MarkCommand extends Command {

    public MarkCommand(Ui ui, TaskList taskList) {
        super(ui, taskList);
    }

    @Override
    public String executeCommand(String userInput) throws IncorrectIndexException {
        String[] input = userInput.split(" ");
        try {
            int taskToMark = Integer.parseInt(input[1]) - 1;
            Task currTask = taskList.get(taskToMark);
            currTask.setAsDone();
            return ui.printMarkedTaskMessage(currTask.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIndexException(taskList.getSize());
        }
    }
}
