import commands.Command;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Main extends Application {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasklist;

    private boolean isExit = false;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Call upon the launch of Shao application.
     * 
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        initServices();
        setGUILayout(stage);
        ui.greetUser(dialogContainer, storage);

        storage.getFile(tasklist, parser, ui, storage, dialogContainer);
    }

    private void initServices() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasklist = new TaskList();
    }

    /**
     * Initialise GUI components and user input events.
     * 
     * @param stage
     */
    private void setGUILayout(Stage stage) {
        final String APP_TITLE = "Shao";
        final String SEND_BUTTON_LABEL = "Send";
        final double SEND_BUTTON_WIDTH = 55.0;
        final double APP_HEIGHT = 600.0;
        final double APP_WIDTH = 400.0;
        final double SCROLLPANE_HEIGHT = 535;
        final double SCROLLPANE_WIDTH = 385;
        final double TEXTFIELD_WIDTH = 325;
        final double OFFSET = 1.0;

        // Step 1. Formatting the window to look as expected.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();

        sendButton = new Button(SEND_BUTTON_LABEL);
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        stage.setScene(scene);
        stage.show();

        // Attach the icon to the stage/window
        stage.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/1786/1786548.png"));

        // Step 2. Formatting the window to look as expected
        stage.setTitle(APP_TITLE);
        stage.setResizable(false);
        stage.setMinHeight(APP_HEIGHT);
        stage.setMinWidth(APP_WIDTH);

        mainLayout.setPrefSize(APP_WIDTH, APP_HEIGHT);

        scrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(OFFSET);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(TEXTFIELD_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, OFFSET);

        AnchorPane.setBottomAnchor(sendButton, OFFSET);
        AnchorPane.setRightAnchor(sendButton, OFFSET);

        AnchorPane.setLeftAnchor(userInput, OFFSET);
        AnchorPane.setBottomAnchor(userInput, OFFSET);

        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!userInput.getText().isBlank()) {
                    readCommand(stage);
                }
            }
        });

        userInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ENTER)) {
                    if (!userInput.getText().isBlank()) {
                        readCommand(stage);
                    }
                }
            }
        });

    }

    /**
     * Read and parse user input to command until user terminates the application.
     * 
     * @param stage
     */
    public void readCommand(Stage stage) {
        String fullCommand = userInput.getText();
        assert fullCommand.trim().length() > 0 : "Input cannot be empty";

        userInput.setText("");

        ui.sendInput(dialogContainer, storage, fullCommand);
        Command c = parser.parseInput(fullCommand);
        c.execute(ui, parser, storage, tasklist, dialogContainer);
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        isExit = c.isExit();

        if (isExit) {
            Platform.exit();
        }

    }

}
