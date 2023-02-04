package duke;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * This class is for the starting screen and the loop the commands in Ui class
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

    /**
     * Constructs a new Duke instance
     *
     * @param mainWindow Controller for MainWindow
     */
    public Duke(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        storage = new Storage();
        parser = new Parser(mainWindow);
        ui = new Ui(parser,mainWindow);
        tasks = new TaskList(storage.loadFile(),mainWindow);
        mainWindow.sendDukeResponse("Hello! I'm Eren\nWhat can I do for you?");
        mainWindow.sendDukeResponse("Type your input below: \n");
    }

    /**
     * Receives input from user and sends to Ui class to handle it
     *
     * @param input User input command
     */
    public void processInput(String input) throws IOException {
        ui.receiveInput(tasks, storage,input);
    }

    public void printDukeIntro(){
        String logo = " _______   _______    _______   ___     __   \n" 
        + "|   ____| |   __  \\  |   ____| |   \\   |  |\n" 
        + "|  |____  |  |  \\  | |  |____  |    \\  |  |\n"
        + "|  _____| |  |__/ /  |  _____| |  |\\ \\ |  |\n"
        + "|  |____  |  | |  \\  |  |____  |  | \\ \\|  |\n"
        + "|_______| |__| |___\\ |_______| |__|  \\____|\n";
        mainWindow.sendDukeResponse("Hello from\n" + logo);
        mainWindow.sendDukeResponse("Hello! I'm Eren\nWhat can I do for you?");

    }
}
