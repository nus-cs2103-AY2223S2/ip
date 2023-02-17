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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


import java.io.IOException;

import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Utilities.*;
import DukeHelpfulCode.Commands.*;


/**
 * The DOOK program is adapted DUKE from NUS SoC CS2103
 * DOOK is a glorified to-do list.
 *
 * @author  Yuan Hao
 * @version who knows
 * @since   11 Feb 2023
 */

public class DOOK {//extends Application{

    private static String LINEBREAK = "_________________________________________________________________\n";
    private static TaskList USERLIST = new TaskList();

    UI ui;
    Storage storage;
    TaskList tasks;

    public DOOK(){
        new DOOK("./src/main/resources/data/tasks.txt");
    }

    public DOOK(String filePath) {
        this.ui = new UI();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) { // e should be EmptyTaskListException
            ui.showLoadingError();
            this.tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        /**
         * Runs DOOK.
         *
         * @param none
         * @return none
         */
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public static void main(String[] args) {
        new DOOK().run();
    }


}
