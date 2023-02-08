package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

public class TagCommand extends Command {
    public static final String COMMAND_WORD = "tag";
    protected int taskNumber;
    protected String tagName;

    public TagCommand(int taskNumber, String tagName) {
        this.taskNumber = taskNumber;
        this.tagName = tagName;
    }

    public boolean isExit() {
        return false;
    }
    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        try {
            Task t = taskList.getTask(taskNumber - 1);
            if (!t.doesTagExist(tagName)) {
                t.setTag(tagName);
                storage.save(taskList);
                return ui.printTaggedTask(t, taskList);
            } else {
                return ui.printError("This task has already been tagged as #" + tagName + "." );
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Huh... the task does not exist.");
        }
    }
}
