package clippy.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Gui extends Application {
    private Scene scene;
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private Label label;
    private Button sendButton;
    private TextField inputBox;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/UserChadderson.jpeg"));
    private Image clippy = new Image(this.getClass().getResourceAsStream("/images/Clippy.png"));

    @Override
    public void start(Stage stage) {
        // 1. Adding the relevant elements into the scene
        this.scrollPane = new ScrollPane();
        this.dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        this.inputBox = new TextField();
        this.sendButton = new Button("Send!");

        this.mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, inputBox, sendButton);

        this.scene = new Scene(mainLayout);
        stage.setScene(this.scene);
        stage.show();

        // 2. Laying out the elements for proper display
        stage.setTitle("Clippy");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385.0, 535.0);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        inputBox.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setBottomAnchor(inputBox, 1.0);
        AnchorPane.setLeftAnchor(inputBox, 1.0);

        // 3. Set user input handlers

        // Enables sending of user input on button click or enter keypress
        sendButton.setOnMouseClicked(event -> handleUserInput());
        inputBox.setOnAction(event -> handleUserInput());

        // Autoscrolling when dialog box size exceeds window size
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));

    }

    public Label createDialogLabel(String text) {
        Label result = new Label(text);
        result.setWrapText(true);

        return result;
    }
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(inputBox.getText());
        Label clippyText = new Label(getResponse(inputBox.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.createUserDialog(userText, new ImageView(user)),
                DialogBox.createClippyDialog(clippyText, new ImageView(clippy))
        );
        inputBox.clear();
    }

    /**
     * TODO: replace this with actual Clippy command parser.
     */
    private String getResponse(String input) {
        return "Clippy heard: " + input;
    }
}
