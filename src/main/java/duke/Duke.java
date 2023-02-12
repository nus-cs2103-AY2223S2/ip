package duke;

import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * This class is for the starting screen and the loop the commands in Ui class.
 * 
 * CS2103T
 * AY22/23 Semester 2.
 *
 * @author Lyndon Lim Liang Hng
 */
public class Duke {
    private Storage storage;
    private boolean hasEnded;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private MainWindow mainWindow;
    private Stage stage;

    /**
     * Constructs a new Duke instance.
     *
     * @param mainWindow Controller for MainWindow.
     */
    public Duke(MainWindow mainWindow, Stage stage) {
        assert mainWindow != null : "Something is wrong with the creaiton of mainWindow";
        this.mainWindow = mainWindow;
        this.stage = stage;
        storage = new Storage();
        parser = new Parser(mainWindow);
        ui = new Ui(parser,mainWindow);
        tasks = new TaskList(storage.loadFile(),mainWindow);
        mainWindow.sendDukeResponse("Hello! I'm Eren\nWhat can I do for you?");
        mainWindow.sendDukeResponse("Type your input below: \n");
    }

    /**
     * Receives input from user and sends to Ui class to handle it.
     *
     * @param input User input command.
     */
    public void processInput(String input) {
        if(ui.receiveInput(tasks, storage,input)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished( event -> stage.close() );
            delay.play();
        }
    }
}
