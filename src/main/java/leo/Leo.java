package leo;

import leo.command.Parser;
import leo.leoexception.LeoException;
import leo.storage.Storage;
import leo.ui.Ui;

/**
 * Leo class to run the main task application.
 */
public class Leo {

    private final Ui ui;

    public Leo(String filePath) {
        ui = new Ui();
        run();
        try {
            Storage storage = new Storage(filePath);
            Parser parser = new Parser(storage);
            parser.readCommand();
        } catch (LeoException e) {
            Ui.displayMessage(e.getMessage());
        }
    }

    private void run() {
        ui.greetings();
    }
}
