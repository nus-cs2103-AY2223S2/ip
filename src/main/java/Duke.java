import java.util.Scanner;

import exception.DukeException;
import javafx.application.Application;
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
import parser.Parser;
import response.Response;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents the Duke class
 */
public class Duke {
    /**
     * Main entry point of the application
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        Storage storage = new Storage(); // Create a Storage object
        TaskList taskList = storage.loadTaskList(); // Create a TaskList object based on saved list
        Ui ui = new Ui(); // Create Ui object to handle interactions with user
        ui.printIntro();

        while (true) {
            String req = scanner.nextLine(); // Read user req

            // Check if user wants to exit
            if (req.equalsIgnoreCase("bye")) {
                break;
            }

            // Parse the user input
            Parser input = new Parser(req);

            try {
                // Parsing the input returns a Response
                Response res = input.parse();

                // Execute the Response to do what needs to be done and get an output message
                String out = res.exec(taskList);

                // If the response is anything other than list, save the to do list
                if (!input.getInputType().equals("LIST")) {
                    storage.saveTaskList(taskList);
                }

                // Print the output message
                ui.printResponse(out);
            } catch (DukeException err) {
                ui.printError(err);
            }
        }
        ui.printExit();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
