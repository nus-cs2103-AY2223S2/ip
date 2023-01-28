public class ByeCommand extends Command {

    private Ui ui;

    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public void action() {
        ui.byeMessage();
        System.exit(0);
    }
}
