package src.main.c4po;

public class AddTaskCommand extends Command {

    private final Task task;
    private final boolean toAppendFile;
    public AddTaskCommand(Task taskToAdd, boolean toAppend) {
        task = taskToAdd;
        toAppendFile = toAppend;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            tasks.addItem(task);

            if (toAppendFile) {
                storage.writeToFile(task.getTaskFileFormat());
            }
            Ui.showTaskAdded();
            Ui.printTask(task);
            Ui.print(tasks.getTaskCount());
        } catch (Exception e) {
            throw new BotException(e.getMessage() + "Add command failed");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
