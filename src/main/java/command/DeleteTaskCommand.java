package command;

import duke.DukeException;
import task.Task;
import task.TaskList;
import ui.TextUi;

public class DeleteTaskCommand extends TaskCommand {

    public DeleteTaskCommand(String command, boolean doesPrint) {
        super(command, doesPrint);
    }

    public void execute(TaskList taskList, TextUi ui) throws DukeException {
        String indexToDelete = getCommandContent(command);
        int idx = Integer.parseInt(indexToDelete);
        idx = idx - 1;   // count from zero
        Task t = taskList.get(idx);
        taskList.remove(idx);
        String toPrint = String.format("Noted. I've removed this task:\n  %s\n"
                + "Now you have %d tasks in the list.", t, taskList.size());
        uiPrint(ui, toPrint);
    }
}
