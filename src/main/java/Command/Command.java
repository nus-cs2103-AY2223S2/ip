package command;

import leoException.LeoException;
import storage.Storage;
import ui.Ui;

public class Command {

    private final Storage storage;
    private final String command;

    public Command(Storage s, String command) {
        this.storage = s;
        this.command = command;
    }

    public int extractTaskNum() {
        String num = command.replaceAll("[^0-9]", "");
        return Integer.parseInt(num);
    }

    public void exit() {
        try {
            storage.writeToFile();
            Ui.displayMessage(Ui.leoResponse("Good bye, have a nice day ahead!"));
            System.exit(0);
        } catch (LeoException e) {
            Ui.displayMessage(Ui.leoResponse(e.getMessage()));
        }
    }

}
