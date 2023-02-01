package duke.duke;




import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * The main class of the programme.
 */
public class Duke {

    public static final String DEFAULT_PATH = System.getProperty("user.dir")
            + "/data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private TaskList list;


    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

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



    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }



}