package command;

import task.Task;
import task.TaskList;
import ui.TextUi;

public class MarkCommand extends CommandClass {

    /**
     * Default constructor, saves the command
     *
     * @param command the user-input command
     */
    public MarkCommand(String command, boolean doesPrint) {
        super(command, doesPrint, false);
    }

    @Override
    public void execute(TaskList taskList, TextUi ui) {
        int idx = Integer.parseInt(command.substring(5)) - 1;
        Task t = taskList.get(idx);
        t.markDone();
        uiPrint(ui, String.format("Nice! I've marked this task as done:\n  %s", t));
    }
}
