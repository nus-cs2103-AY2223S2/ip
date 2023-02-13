package center;

import UI.Ui;
import command.Parser;
import command.Storage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import UI.DialogBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import task.TaskList;

import java.io.IOException;

public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    //Todo: Let the user input the path to an existing list file that they have
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public String getResponse(String input) {
        return parser.processInput(input);
    }
}
