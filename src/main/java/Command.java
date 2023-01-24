abstract class Command {
    public String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public boolean isExit() {
        return true;
    }

}
