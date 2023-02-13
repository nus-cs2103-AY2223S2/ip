package duke;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class Duke {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Image chatBackground= new Image(this.getClass().getResourceAsStream("/images/ChatBackground.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private MainWindow mainWindow;

    public Ui ui;
    public Storage storage;
    public TaskList listOfTasks;
    public Parser parser;

    /**
     * Constructor for the Duke Class.
     * Initializes Ui, Storage, Parser and TaskList while loading items found in the path file into it.
     */
    public Duke()  {
        ui = new Ui();
        ui.displayLogo();
        storage = new Storage("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt");
        parser = new Parser();


    }

    public void runInput(String input) throws FileNotFoundException {

        assert listOfTasks != null : "TaskList does not exist in Duke";
        Command c = parser.parse(input, listOfTasks, ui);
        c.executeCommand(listOfTasks, storage, ui);
    }

    public void setMainWindow(MainWindow mainWindow) {

        assert mainWindow != null: "MainWindow not found in Duke";

        this.mainWindow = mainWindow;
        ui.setMainWindow(mainWindow);

        try {
            listOfTasks = new TaskList();
            listOfTasks.allTasks = storage.loadFile(listOfTasks.getTasks(), ui);

        }  catch (IOException i) {
            ui.printText("Remember that since the file cannot be loaded, you cannot save your file!");
        }
        ui.greet();
    }

}
