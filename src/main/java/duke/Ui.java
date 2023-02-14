package duke;

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
 * Ui class is used for all user interface interactions with
 * the user.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Ui {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Parser parser;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));
    /**
     * Constructor for Ui class, displaying greeting UI when user first starts the program.
     */
    public Ui(Stage stage) {
        parser = new Parser(stage);
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        String greeting = "Hey there, DUKE MK-II here!\n"
                + "Use 'help' to get started.\n";
        Label greetingLabel = new Label(greeting);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane = setupScrollPane();

        dialogContainer = new VBox();

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable)-> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetingLabel, new ImageView(duke))
        );

        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        userInput.setPrefWidth(320.0);


        sendButton = new Button("Send");
        sendButton.setPrefWidth(60.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setRightAnchor(scrollPane, 10.0);
        AnchorPane.setBottomAnchor(sendButton, 10.0);
        AnchorPane.setRightAnchor(sendButton, 10.0);
        AnchorPane.setLeftAnchor(userInput, 10.0);
        AnchorPane.setBottomAnchor(userInput, 10.0);


        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        String css = this.getClass().getResource("/stylesheet/application.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }


    private void handleUserInput() {
        String userCmd = userInput.getText();
        Label userText = new Label(userCmd);
        final String userResponseBorder = "-fx-border-color: grey;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n";
        userText.setStyle(userResponseBorder);
        Label dukeText = new Label(parser.parseCommandWithResponse(userCmd));
        final String dukeResponseBorder = "-fx-border-color: orange;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 3;\n"
                + "-fx-border-style: dashed;\n";
        dukeText.setStyle(dukeResponseBorder);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private ScrollPane setupScrollPane() {
        //The container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }
}
