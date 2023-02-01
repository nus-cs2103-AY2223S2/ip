package duke;

import duke.ui.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.StorageList;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Main Class which runs the whole chatbot.
 */
public class Duke {

    private StorageList storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for class Duke
     * @param filePath Directory of the textfile to be used for the saved commands
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new StorageList(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(String.valueOf(e));
            tasks = new TaskList();
        }
    }

    /**
     * Method which starts the program to output the various messages and checks the commands.
     */

    public String run(String userInput) {
        //ui.showWelcome();
       // boolean isExit = false;
       // while (!isExit) {
            try {
              //  String fullCommand = ui.readCommand();
              //  ui.showLine(); // show the divider line ("_______")
               // Command c = Parser.parse(fullCommand);
               // c.execute(tasks, ui, storage);
                //isExit = c.isExit();
                Command c = Parser.parse(userInput);
                return c.execute(tasks, ui, storage);
            } catch (IllegalArgumentException e) {
               // ui.showLoadingError("Sorry i did not understand that command!");
                return "Sorry i did not understood that command!";
            } catch (ArrayIndexOutOfBoundsException e) {
                //ui.showLoadingError("Pls fill in the command");
                return "Pls fill in the command accordingly";
            } catch (DukeException e) {
                return e.getMessage();
            } finally {
                storage.updateStorage();
                //ui.showLine();
            }
      //  }
        //return userInput;
    }

}




