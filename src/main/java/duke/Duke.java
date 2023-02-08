package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main runner class for the Duke program.
 */
public class Duke extends Application {
    private Scanner scanner;
    private String horizontalLine = "************************";
    private TaskList taskList;

    private Storage storage;
    private Ui ui;
    private Parser parser;
    private DukeGui dukeGui;

    public Duke() {
        scanner = new Scanner(System.in);

        storage = new Storage("list_storage.txt");
        parser = new Parser();
        taskList = new TaskList(storage);
        ui = new Ui(taskList);
        dukeGui = new DukeGui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();

        dukeGui.start(stage);
    }


    /**
     * Runs the Duke program
     */
    public void run() {
        // Show intro
        ui.showIntro();

        boolean isExit = false;
        while (!isExit) {
            try {
                String rawInput = ui.readInput();
                Command c = parser.parseInput(rawInput);
                c.execute(taskList, ui);
                isExit = c.getExit();
            } catch (DukeException e) {
                ui.showUnknown(e.getMessage());
            }
        }
    }


}
