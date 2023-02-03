package crystal;

import crystal.command.Command;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;





/**
 * Represents the Crystal class.
 *
 */
public class Crystal extends Application{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/cat.png"));
    private Image crystal = new Image(this.getClass().getResourceAsStream("/images/dog.png"));

    String file2 = "/repos/Independentproject/myfiles/Crystal.txt";
    String base = "/repos/Independentproject";
    String relative = new File(base).toURI().relativize(new File(file2).toURI()).getPath();

    /**
     * Constructor for Crystal class.
     *
     */
    public Crystal() {
        ui = new Ui();
        storage = new Storage(relative);
        try {
            tasks = new TaskList(storage.readFileContents());
        } catch (CrystalException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }



    /**
     * Runs the program.
     * Shows the welcome message.
     * While isExit is false, loads the previous saved file and
     * takes in user commands for the list.
     *
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command cmd = Parser.parse(fullCommand);
                cmd.execute(tasks, ui, storage);
                storage.saveFile(tasks);
                isExit = cmd.isExit();
            } catch (CrystalException e) {
                ui.showError(e);

            }
        }
    }


    @Override
    public void start(Stage stage) throws Exception {

        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

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

        stage.setTitle("Crystal");
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }



    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * handles the user input in the GUI
     *
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String crystalText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getCrystalDialog(crystalText, crystal)
        );
        userInput.clear();
    }


    public String printWelcome() {
        return ui.showWelcome();
    }


    /**
     * Returns the bot response in the GUI
     * @param input the message from the user
     * @return the bot message
     */
    public String getResponse(String input) {
        String temp = "";
        try {
            Command c = parser.parse(input);
            temp = c.execute(tasks, ui, storage);
        } catch (CrystalException e) {
            return e.getMessage();
        }

        return temp;
    }


    /**
     * Main method which calls the run method.
     *
     */
    public static void main(String[] args) {
        new Crystal().run();
    }



}