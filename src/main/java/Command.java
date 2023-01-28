abstract class Command {
    public boolean toQuit;

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BotException;

    public abstract boolean isExit();

}
