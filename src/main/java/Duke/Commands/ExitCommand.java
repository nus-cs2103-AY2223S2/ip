package Duke.Commands;

import Duke.Ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    public void processCommand(Ui ui) {
        ui.printBye();
    }
}
