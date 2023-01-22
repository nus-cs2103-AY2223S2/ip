package commands;

import view.Printable;

public class ByeCommand extends Command {
    public ByeCommand(Printable ui) {
        super(ui);

        this.isExit = true;
    }

    @Override
    public void execute() {
        this.ui.printlnIndent("Have a good day! Good bye!");
    }
}
