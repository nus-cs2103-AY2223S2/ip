public abstract class Command {
    protected String commandMessage;

    public Command(String commandMessage) {
        this.commandMessage = commandMessage;
    }

    public abstract boolean execute();
}
