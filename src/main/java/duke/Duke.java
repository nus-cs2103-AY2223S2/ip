package duke;

import duke.data.Storage;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class duke.Duke implements a chatbot encapsulates user's tasks and show it
 * to the user later by processing the inputs.
 *
 * @author hhchinh2002
 */
public class Duke extends Application {
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;
    private TextField userInput;

    /**
     * Initialize a duke.Duke object with the corresponding object from other supporting classes.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList(storage);
        this.storage = new Storage(taskList);
        this.parser = new Parser(taskList);
        userInput = new TextField();
    }


    public static void main(String[] args) {
        new Duke().start(new Stage());
    }

    @Override
    public void start(Stage stage) {
        VBox dialogContainer = ui.getDialogContainer();
        ScrollPane scrollPane = ui.getScrollPane();
        Button sendButton = ui.getSendButton();

        ui.displayIntro();

        //set up the UI
        scrollPane.setContent(dialogContainer);
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        ui.setStage(stage);
        ui.setScrollPane();
        mainLayout.setPrefSize(400.0, 600.0);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        //Deal with user input
        ui.setAnchorPane(userInput);
        ui.manageGuiEchoing(userInput, parser);
    }



}
