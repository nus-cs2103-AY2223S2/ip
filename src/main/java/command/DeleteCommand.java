public class DeleteCommand extends Command {
    private final String index;
    public DeleteCommand(String index) {
        super("delete");
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int idx = Integer.parseInt(this.index);
            String taskDescription = tasks.delete(idx);
            ui.print(String.format("Noted, I've removed this task: \n\t%s\nNow you have %d tasks in this list.",
                    taskDescription, tasks.size()));
            storage.saveList(tasks);
        } catch (NumberFormatException notANumber) {
            throw new DukeException("Please enter a valid number");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
