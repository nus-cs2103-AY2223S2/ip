public class UnmarkCommand extends Command {
    private final String index;
    public UnmarkCommand(String index) {
        super("unmark");
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any items in your list!");
        }
        try {
            int idx = Integer.parseInt(this.index);
            tasks.get(idx).unmarkAsDone();
            ui.print(String.format("Nice! I've marked this task as done: \n\t%s",
                    tasks.get(idx)));
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
