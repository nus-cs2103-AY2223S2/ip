package leo;

import leo.command.Command;
import leo.command.Parser;
import leo.leoexception.LeoException;
import leo.storage.Storage;
import leo.ui.Ui;

/**
 * Leo class to run the main task application.
 */
public class Leo {

    private Parser parser;

    public Leo(String filePath) {
        try {
            Storage storage = new Storage(filePath);
            parser = new Parser(storage);
        } catch (LeoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the response from Duke
     *
     * @param input User input
     * @return Response from Duke
     */
    public String getResponse(String input) {
        try {
            Command command = this.parser.readCommand(input);
            return command.execute();
        } catch (LeoException e) {
            return e.getMessage();
        }
    }
}
