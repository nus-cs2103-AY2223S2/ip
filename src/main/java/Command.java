public abstract class Command {

    public abstract void handleCommand(Ui ui);
    public abstract boolean isExit();
}
