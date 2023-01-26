public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList);
        System.out.println("Goodbye! It's been a pleasure talking to you!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
