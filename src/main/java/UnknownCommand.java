public class UnknownCommand extends Command{

    public UnknownCommand() {
        super();
    }

    @Override
    public void execute(TaskList Tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

}
