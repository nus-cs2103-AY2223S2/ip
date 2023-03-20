package duke;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
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
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



/**
 * This is the main class that serves as the entry point into the application
 */
public class Duke  {

    private Storage storage;
    private Parser parser;
    private UI ui;
    private TaskList list;

    /**
     * Constructor for a Duke object
     */
    public Duke () {
        ui = new UI();
        list = new TaskList();
        storage = new Storage(list);
        parser = new Parser(storage, ui);
    }


    /**
     * Method to obtain the String that is output when the different requests are entered by the user
     * @param input The request entered in by the user to the application
     * @return A String representing the response of the application to the request of the user
     */
    public String getResponse(String input) {
        String furtherCommandPrompt = "\n\nAnything else I can do for you? Enter 'help' for a list of commands!";
            try {
                String output = parser.parseAndExecute(input, list);
                if (output.equals("Thank You and have a great day ahead!")) {
                    Main.close();

                }
                return output + furtherCommandPrompt;
            } catch (IOException e) {
                String output = ui.printInvalidDateFormatMessage();
                return output + furtherCommandPrompt;
            }
    }
}






