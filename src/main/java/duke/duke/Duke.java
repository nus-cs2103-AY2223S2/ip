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
    private Ui ui;
    private Storage storage;
    private TaskList list;

    /**
     * Creates a Duke which handles responses to user inputs.
     * @param filePath A string which denotes the path to store Duke's data.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        TaskList temp;
        try {
            temp = this.storage.loadFile();
        } catch (DukeException e) {
            this.ui.display(e.getMessage());
            temp = new TaskList();
        }
        this.list = temp;

    }

    public Duke() {
        this(DEFAULT_PATH);
    }

    public static void main(String[] args) {
        String txtDir = System.getProperty("user.dir") + "/data/tasks.txt";
        Duke instance = new Duke(txtDir);
        instance.run();
    }

    /**
     * Runs the Duke programme.
     */
    public void run() {
        ui.showLogo();
        ui.showWelcome();

        Parser parser = new Parser();
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
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    public String getResponse(String input) {
        Parser parser = new Parser();
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
