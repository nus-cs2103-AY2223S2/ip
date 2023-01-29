public class MarkCommand extends Command {
    private int taskNumber;
    public MarkCommand(String rest) throws InvalidCommandException {
        if (rest.isEmpty()) {
            throw new InvalidCommandException();
        }
        String[] restCommand = rest.split(" ");
        if (restCommand.length > 1) {
            throw new InvalidCommandException();
        }
        try {
            this.taskNumber = Integer.valueOf(restCommand[0]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        tasks.markTask(taskNumber);
        ui.showMarkText(tasks.getTaskDescription(taskNumber));
    }
}
