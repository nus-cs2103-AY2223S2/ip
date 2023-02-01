public class deleteCommand extends Command {
    private final int index;

    public deleteCommand(int index) {
        this.index = index;
    }

    public deleteCommand(String fullCommand) throws DukeException {
        try {
            index = Integer.parseInt(fullCommand
                    .split("delete")[1]
                    .trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Index given is not an integer");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Index given is not in the list");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.delete(index);
        ui.echo("Noted. I've removed this task:");
        ui.showTask(task);
        ui.showTaskCount(tasks);
        storage.save(tasks);
    }
}
