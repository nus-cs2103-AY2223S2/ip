public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(String rest) throws InvalidCommandException {
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
        ui.showDeleteText(tasks.getTaskDescription(taskNumber), tasks.getSize()-1);
        tasks.remove(taskNumber);
    }
}
