package duke.gui;

import duke.Duke;
import duke.gui.DialogBox;
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

/**
 * Class that renders the GUI for Duke.
 */
public class Gui extends Application {
    private Duke duke;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Gui() {
        duke = new Duke();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        duke.run();
        Scene scene = loadScene();
        primaryStage.setTitle("Duke");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(600.0);
        primaryStage.setMinWidth(400.0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Returns a JavaFX Scene instance with appropriate JavaFx
     * controls configured.
     *
     * @return A Scene instance containing appropriately
     * configured controls.
     */
    public Scene loadScene() {
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        Label greeting = new Label(duke.getGreeting());
        dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog(greeting, new ImageView(dukeImg)));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        return new Scene(mainLayout);
    }

    /**
     * Method called upon user submitting user input. The
     * input is passed to Duke to process, and a response
     * is returned from Duke accordingly.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        assert userText != null;
        Label dukeText = new Label(duke.getResponse(userInput.getText()));
        assert dukeText != null;
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImg)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
        userInput.clear();
    }

}
