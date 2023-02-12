package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DukeGui {

    private Duke duke;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Stage stage;

    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/wall_e.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/eve.png"));
    private Image spaceImg = new Image(this.getClass().getResourceAsStream("/images/space_bg.png"));

    public DukeGui(Duke duke) {
        this.duke = duke;
    }

    public void start(Stage stage) {
        this.stage = stage;

        createWindow(stage);
        formatComponents();
        setAnchors();
        handleActions();

        showIntro();

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private void createWindow(Stage stage) {
        // Creating window
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        // Set background
        scrollPane.setStyle("-fx-background: #121c2b");
        BackgroundImage bgImage = new BackgroundImage(spaceImg, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        dialogContainer.setBackground(new Background(bgImage));

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // Formatting window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinWidth(450.0);
        stage.setMinHeight(500.0);
        mainLayout.setPrefSize(450.0, 500.0);
    }

    private void formatComponents() {
        scrollPane.setPrefSize(450, 470);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setMinHeight(470);
        dialogContainer.setSpacing(10);
        userInput.setPrefWidth(385.0);
        sendButton.setPrefWidth(55.0);
    }

    private void setAnchors() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void handleActions() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void showIntro() {
        String intro = duke.getIntro();
        Label dukeText = new Label(intro);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(duke.getDukeResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImg)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImg))
        );
        userInput.clear();

        // If user wants to close program
        if (duke.getIsExit()) {
            stage.close();
        }
    }

}
