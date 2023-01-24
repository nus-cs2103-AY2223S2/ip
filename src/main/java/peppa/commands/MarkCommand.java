package peppa.commands;

import peppa.*;

public class MarkCommand implements Command {
    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    public MarkCommand() {}
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui screen, Storage storage) throws PeppaException {
        if (this.taskIndex < 0 || this.taskIndex >= taskList.getLength()) {
            throw new PeppaException("Boink! Peppa could not find the requested task. " +
                    "Please enter a valid integer and try again.");
        } else {
            Task task = taskList.retrieveTask(taskIndex);
            task.setDone(true);
            Ui.displayMarkDoneMessage(task);
            storage.saveChanges(taskList);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
