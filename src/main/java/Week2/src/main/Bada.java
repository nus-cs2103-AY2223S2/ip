package Week2.src.main;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import Commands.Task;
import Exceptions.EmptyContentException;
import Exceptions.UnknownCommandException;
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
 * A simple todo bot to manage my todo list.
 * It can mark done jobs or unmark.
 * A simple todo bot that helps you to manage your tasks with deadline or occuring time.
 * @author Park Hyunjin
 */
public class Bada extends Application {

    private static Storage storage;
    static boolean isBye = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    static TaskList tasklist = new TaskList();
    //assert tasklist.length() < 100 : "Task list is too long";
    static FileWriter fw;

    Ui ui = new Ui();

    /**
     * Runs Bada and begins the program.
     * It invokes parser to start managing tasks.
     * @param input User input line
     * @return Output line to show to user
     * @throws IOException to write on the data
     */
    public String run(String input) throws IOException {
        storage = new Storage("/saves/data.txt");
        try {
            Parser parser = new Parser(tasklist);
            if (!isBye) {
                return parser.runParser(input);
            } else {
                return ui.bye();
            }
        } catch (EmptyContentException e) {
            return ui.showEmptyError();
        } catch (FileNotFoundException e) {
            return ui.showFileError();
        } catch (UnknownCommandException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bada's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label badaText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBadaDialog(badaText, new ImageView(bot))
        );
        userInput.clear();
    }

    /**
     * Get response input from user
     * @param input entered line from user
     * @return runs Bada
     * @throws IOException to write on
     */
    private String getResponse(String input) throws IOException {
        return run(input);
    }

    /**
     * Starts the dialog GUI.
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Bada");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
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

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Label msg = new Label(ui.hello());
        dialogContainer.getChildren().addAll(DialogBox.getBadaDialog(msg, new ImageView(bot)));
    }

}
