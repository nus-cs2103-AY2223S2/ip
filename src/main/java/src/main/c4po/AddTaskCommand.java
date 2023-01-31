package src.main.c4po;

public class AddTaskCommand extends Command {

    private final Task task;
    private final boolean toAppendFile;
    public AddTaskCommand(Task taskToAdd, boolean toAppend) {
        task = taskToAdd;
        toAppendFile = toAppend;
    }

    /**
     * {@inheritDoc}
     * @param tasks are the list of tasks
     * @param ui is the instance of UI
     * @param storage the instance of Storage which holds and writes to the data file
     * @throws BotException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BotException {
        try {
            tasks.addItem(task);
            //error here
            if (toAppendFile) {
                storage.writeToFile(task.getTaskFileFormat());
                storage.writeToFile("\n");
            }
            Ui.showTaskAdded();
            Ui.printTask(task);
            Ui.print(tasks.getTaskCount());
        } catch (Exception e) {
            throw new BotException(e.getMessage() + "Add command failed");
        }

    }

    /**
     * Executes the command with actions specific to each extension of this
     * class Command
     *
     * @param tasks          are the list of tasks
     * @param ui             is the instance of UI
     * @param storage        the instance of Storage which holds and writes to the data file
     * @param isStringOutput
     * @throws BotException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, boolean isStringOutput) throws BotException {
        StringBuilder addTaskResponse = new StringBuilder();

        try {
            tasks.addItem(task);

            if (toAppendFile) {
                storage.writeToFile(task.getTaskFileFormat());
                storage.writeToFile("\n");
            }
            addTaskResponse.append(Ui.showTaskAdded(true)).append("\n");
            addTaskResponse.append(Ui.printTask(task, true)).append("\n");
            addTaskResponse.append(tasks.getTaskCount());

        } catch (Exception e) {
            throw new BotException(e.getMessage() + "Add command failed");
        }

        return addTaskResponse.toString();
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
