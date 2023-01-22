public class EndChatCommand extends Command{
    private Ui ui;

    public EndChatCommand(String commandMessage, Ui ui) {
        super(commandMessage);
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        this.ui.endChat();
        return true;
    }
}
