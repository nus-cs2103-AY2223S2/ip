package duke;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;

/**
 * Main class of Duke.
 * Starts a new Ui and Storage to prepare running duke and runs when they are ready.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "./src/main/java/duke/data.txt";

    /**
     * Class constructor of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke by using ui to read command and execute the command.
     * Continues running until the ui reads EndCommand.
     */
    public String getResponse(String input) {
        String res = "";
        try {
            Command c = Parser.parse(input);
            res += c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            res += ui.showError(e);
        } finally {
        }
        try {
            storage.writeToFile(tasks.toTxtString());
        } catch (IOException e) {
            System.out.println("Error during saving");
        }
        return res;
    }
}
