public class unmarkCommand extends Command {
    private final int index;

    public unmarkCommand(int index) {
        this.index = index;
    }

    public unmarkCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("unmark")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Index given is not in the list");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.unmark(index);
        ui.echo("OK, I've marked this task as not done yet:");
        ui.showTask(task);
        storage.save(tasks);
    }
}
