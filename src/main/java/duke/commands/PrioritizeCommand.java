package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

public class PrioritizeCommand extends Command {
    public static final String COMMAND_WORD = "prioritize";
    protected int taskNumber;
    protected String priority;


    public PrioritizeCommand(int taskNumber, String priority) {
        this.taskNumber = taskNumber;
        this.priority = priority;
    }

    public boolean isExit() {
        return false;
    }

    public String execute(TaskList taskList, Storage storage, TextUi ui) {
        try {
            Task t = taskList.getTask(taskNumber - 1);
            String currPriority = t.getPriority();
            if (currPriority == null || !currPriority.equals(priority)) {
                t.setPriority(priority);
                storage.save(taskList);
                return ui.printPrioritizedTask(t, taskList);
            } else {
                return ui.printError("Oops! This task has already been tagged as a " + priority +
                        " priority." );
            }
        } catch (IndexOutOfBoundsException e) {
            return ui.printError("Huh... the task does not exist.");
        }
    }
}
