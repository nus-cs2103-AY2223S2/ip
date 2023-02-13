package duke;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * {@code GUI} class that manages the graphic user interface
 * of Duke program
 */
public class GUI{

    /**
     * Application's window
     */
    private ScrollPane scrollPane;

    /**
     * Container for Node Objects
     */
    private VBox dialogContainer;

    /**
     * Wraps around input from user
     */
    private TextField userInput;

    /**
     * Allows user to press send button to send
     * command
     */
    private Button sendButton;

    /**
     * User's icon
     */
    private Image user = new Image(this.getClass().getResourceAsStream("/DaUser.png"));

    /**
     * Duke's icon
     */
    private Image duke = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    /**
     * dukeProgram to access and modify taskList and storage
     */
    private Duke dukeProgram = new Duke(System.getProperty("user.dir"));

    /**
     * Borderlines for every message sent by Duke
     */
    static final String BORDERLINE =  "_____________________________________\n";

    /**
     * VValue for scroll and anchor panes
     */
    private final double VVALUE = 1.0;

    /**
     * Sets up the required components for Duke program
     * @param stage Default stage provided to run application
     */
    public void startUpProgram(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        formatWindow(stage, mainLayout);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Formats the application window
     * @param stage Default stage provided to run application
     * @param pane Application's main window
     */
    private void formatWindow(Stage stage, Pane pane) {
        double userInput_PrefWidth = 325.0;
        double sendButton_PrefWidth = 55.0;

        // Set up primary stage and main layout pane
        setUpStagePane(stage, pane);

        setUpScrollPane();
        setUpAnchorPane();

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(userInput_PrefWidth);
        sendButton.setPrefWidth(sendButton_PrefWidth);
    }

    /**
     * Sets up primary stage and main layout pane
     * @param stage primary stage used in application
     * @param pane main layout pane for application
     */
    private void setUpStagePane(Stage stage, Pane pane){
        double stage_MinHeight = 600.0;
        double stage_MinWidth = 400.0;

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(stage_MinHeight);
        stage.setMinWidth(stage_MinWidth);

        pane.setPrefSize(stage_MinWidth, stage_MinHeight);
    }

    /**
     * Sets up Scroll Pane of application
     */
    private void setUpScrollPane(){
        double scroll_PrefWidth = 385.0;
        double scroll_PrefHeight = 535.0;

        scrollPane.setPrefSize(scroll_PrefWidth, scroll_PrefHeight);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(VVALUE);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets up Anchor Pane of application
     */
    private void setUpAnchorPane(){
        AnchorPane.setTopAnchor(scrollPane, VVALUE);

        AnchorPane.setBottomAnchor(sendButton, VVALUE);
        AnchorPane.setRightAnchor(sendButton, VVALUE);

        AnchorPane.setLeftAnchor(userInput , VVALUE);
        AnchorPane.setBottomAnchor(userInput, VVALUE);
    }

    /**
     * Performs necessary actions when user presses the send button
     */
    public void runEvent() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Handles inputs from user and performs the necessary
     * actions
     */
    public void handleUserInput() {
        String text = userInput.getText();
        Label userText = new Label(text);
        String dukeReply = dukeProgram.getResponse(text);

        if (dukeReply.equals("bye")){
            String byeMessage = BORDERLINE
                    + "See ya later!\n"
                    + BORDERLINE;
            Label dukeBye = new Label(byeMessage);
            setDialogContainer(userText, dukeBye);

            Platform.exit();
            System.exit(0);
        } else {
            Label dukeText = new Label(dukeReply);
            setDialogContainer(userText, dukeText);
        }
        userInput.clear();
    }

    /**
     * Adds user's dialog box and duke's dialog box into dialog container
     * @param userText user's dialog box
     * @param dukeText duke's dialog box
     */
    private void setDialogContainer(Label userText, Label dukeText){
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    /**
     * Prints out error message when program fails to start up
     */
    public void showLoadingError() {
        System.out.println("Unable to start up program.");
        System.exit(-1);
    }

    /**
     * Prints out error message stored in {@code DukeException} object
     * @param dukeError error message in DukeException object
     */
    public void displayError(DukeException dukeError){
        System.out.println(dukeError.errorMessage);
    }
}
