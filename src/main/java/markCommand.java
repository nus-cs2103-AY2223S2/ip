public class markCommand extends Command {
    private final int index;

    public markCommand(int index) {
        this.index = index;
    }

    public markCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("mark")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Index given is not in the list");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.mark(index);
        ui.echo("Nice! I've marked this task as done:");
        ui.showTask(task);
        storage.save(tasks);
    }
}
