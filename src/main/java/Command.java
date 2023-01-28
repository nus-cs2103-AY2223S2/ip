abstract class Command {

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    public boolean isBye() {
        return false;
    }
}
