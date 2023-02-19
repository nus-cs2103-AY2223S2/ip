package duke.ui;

import java.io.IOException;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TodoList;
import duke.command.Command;
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

public class Ui extends Application{
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    private Parser bot;
    private Storage storage;
    private TodoList todoList;

    @Override
    public void init() {
        this.storage = new Storage();
        this.todoList = storage.load();
        this.bot = new Parser(todoList);
    }

    @Override
    public void start(Stage stage) throws IOException {

        //Step 1: Setting
        AnchorPane mainLayout = new AnchorPane();

        ScrollPane scrollPane = new ScrollPane();
        VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField("Type your command here");
        Button sendButton = new Button("Send");
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Step 2: Formatting
        double height = 600.0;
        double width = 400.0;
        stage.setMinHeight(height);
        stage.setMinWidth(width);
        stage.setResizable(false);

        mainLayout.setPrefSize(width, height);

        scrollPane.setPrefSize(width - 15, height - 65);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable -> {scrollPane.setVvalue(1.0);}));

        //Step 3: Handle action
        sendButton.setOnMouseClicked((event) -> {
            String userInputCommand = userInput.getText();
            Label userText = new Label(userInputCommand);
            String response = "";
            try {
                Command command = bot.parse(userInputCommand);
                response = command.execute();
            } catch (DukeExceptions error) {
                response = error.getErrorMessage();
            }
            Label dukeText = new Label(response);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            String userInputCommand = userInput.getText();
            Label userText = new Label(userInputCommand);
            String response = "";
            try {
                Command command = bot.parse(userInputCommand);
                response = command.execute();
            } catch (DukeExceptions error) {
                response = error.getErrorMessage();
            }
            Label dukeText = new Label(response);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, new ImageView(user)),
                    DialogBox.getDukeDialog(dukeText, new ImageView(duke))
            );
            userInput.clear();
        });

        Scene scene = new Scene(mainLayout);
        stage.setTitle("Duke");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        storage.save(todoList);
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

}
