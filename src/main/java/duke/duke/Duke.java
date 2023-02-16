package duke.duke;


import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import javafx.scene.control.Label;

/**
 * The main class of the programme.
 */
public class Duke {

    public static final String DEFAULT_PATH = System.getProperty("user.dir")
            + "/data/tasks.txt";
    private static Ui ui = new Ui();
    private Storage storage;
    private TaskList list;

    /**
     * Creates a Duke which handles responses to user inputs.
     * @param filePath A string which denotes the path to store Duke's data.
     */
    public Duke(String filePath) {

        this.storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = this.storage.loadFile(0);
        } catch (DukeException e) {
            this.ui.display(e.getMessage());
            temp = new TaskList();
        }
        this.list = temp;

    }

    public Duke() {
        this(DEFAULT_PATH);
    }

    public String getGreetings() {
        return this.ui.getGreetings();
    }

    public static void main(String[] args) {
        String txtDir = System.getProperty("user.dir") + "/data/tasks.txt";
        Duke instance = new Duke(txtDir);
        instance.run(txtDir);
    }

    /**
     * Runs the Duke programme.
     */
    public void run(String filePath) {
        ui.showLogo();
        ui.showWelcome();

        Parser parser = new Parser(filePath);
        boolean isBye = false;
        while (!isBye) {
            try {
                String[] line = ui.readLine();
                Command c = parser.parse(line);
                c.execute(list, ui, storage);
                isBye = c.isBye();
            } catch (Exception err) {
                ui.showError(err);
            }
        }

    }


    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    public String getWelcome() {
        return this.ui.getWelcome();
    }


    public String getResponse(String input) {
        Parser parser = new Parser(DEFAULT_PATH);
        String[] processedLines = input.split(" ");
        Command c;
        try {
            c = parser.parse(processedLines);
            return c.execute(list, ui, storage);
        } catch (DukeException err) {
            return err.getMessage();
        }

    }







}
