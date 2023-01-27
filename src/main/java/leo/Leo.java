package leo;

import leo.command.Parser;
import leo.leoException.LeoException;
import leo.storage.Storage;
import leo.ui.Ui;

public class Leo {

    private final Ui ui;

    public static void main(String[] args) {
        new Leo("ip/data/leo.txt");
    }

    private Leo(String filePath) {
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
