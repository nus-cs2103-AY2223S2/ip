package smudge.command;

import smudge.main.SmudgeException;
import smudge.main.Storage;
import smudge.main.Tasklist;
import smudge.main.Ui;
import java.io.IOException;

public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public String execute(Tasklist tasklist, Ui ui, Storage storage) throws IOException, SmudgeException {
        try {
            int initialSize = tasklist.getTasksNum();
            //assert taskNum > 0 && taskNum <= initialSize : "task number must be > 0 and <= to size of tasklist";
            tasklist.markDone(this.taskNum - 1);
            storage.update(tasklist);
            return ui.printMarkTaskMessage(tasklist.getTasks().get(this.taskNum - 1));
        } catch (IndexOutOfBoundsException e) {
        throw new SmudgeException("☹ Meow!!! Task number to be unmarked is out of bounds of current list. " +
                "Please use a task number within current list.");
    }
    }
}