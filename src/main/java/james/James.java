package james;

import james.command.Command;
import james.parser.Parser;
import james.storage.Storage;
import james.task.TaskList;
import james.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class James extends Application{
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

        public James() throws JamesException {
            ui = new Ui();
            parser = new Parser();
            storage = new Storage();
            taskList = storage.loadFile();

            ui.welcome();
            String input = ui.readCommand();

            while (!input.equals("bye")) {
                try {
                    Command command = parser.parseCommand(input);
                    command.assign(taskList, ui);
                    command.execute();
                } catch (JamesException e) {
                    ui.printError(e);
                } finally {
                    input = ui.readCommand();
                }
            }
            storage.writeToFile(taskList);
            ui.exit();
        }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }
}



