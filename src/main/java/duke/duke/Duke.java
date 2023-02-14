package duke.duke;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.scene.control.Label;

/**
 * Represents the Tohtoro program, a chatbot that helps with keeping track of your tasks.
 */
public class Duke {
    private Ui ui;
    private final Storage storage;
    private TaskList tl;
    private boolean isExit;

    /**
     * The constructor for the Duke Chatbot.
     *
     * @param filePath the file path to save the tasks to
     */
    public Duke(String filePath) {
        this.isExit = false;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tl = this.storage.load();
        } catch (DukeException e) {
            this.ui.showToUser(e.getMessage());
        }
    }

    public Duke() {
        this("./save.txt");
    }

    /**
     * Represents the main method to run the Duke program.
     *
     * @param args Command line arguments input by the user.
     */
    public static void main(String[] args) {
        String txtDir = "./save.txt";
        new Duke(txtDir).run();
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.showWelcomeMessage();
        while (!this.isExit) {
            try {
                String[] commandString = ui.getUserCommand();
                Parser parser = new Parser();
                Command c = parser.parse(commandString);
                c.execute(tl, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
            }
        }
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Returns the string which is to be passed into the GUI under Poolsheen's response.
     *
     * @param input The full string passed in by the user.
     * @return The string which is to be passed onto the GUI.
     */
    public String getResponse(String input) {
        Parser parser = new Parser();
        String[] processedLines = input.split(" ");
        Command c;
        try {
            c = parser.parse(processedLines);
            return c.execute(tl, ui, storage);
        } catch (DukeException err) {
            return err.getMessage();
        }

    }
}

