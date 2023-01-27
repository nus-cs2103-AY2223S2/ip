package command;

import task.Task;
import task.TaskList;
import ui.TextUi;

public class UnmarkCommand extends CommandClass {
    public UnmarkCommand(String command, boolean doesPrint) {
        super(command, doesPrint, false);
    }

    @Override
    public void execute(TaskList taskList, TextUi ui) {
        int idx = Integer.parseInt(command.substring(7)) - 1;
        Task t = taskList.get(idx);
        t.unmarkDone();
        uiPrint(ui, String.format("OK, I've marked this task as not done yet:\n  %s", t));
    }
}
