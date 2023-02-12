package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



/**
 * Class representing the Duke Application
 */
public class Duke extends Application {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for the Duke application
     */
    public Duke() {
    }

    /**
     * Runs the Duke application
     * @throws DukeException
     * @throws IOException
     */
    public void run(Storage storage, TaskList tasks, Ui ui) throws DukeException, IOException {
        ui.showLogo();
        ui.showLine();
        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            tasks.parser(userInput);
            ui.showLine();
            storage.save(tasks);
            userInput = myObj.nextLine();
        }
        storage.save(tasks);
        ui.showBye();
    }


    /**
     * Main driver function for the duke Application
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Storage database = new Storage("./data", "duke.txt");
        TaskList tasklist = new TaskList(database.load());
        Ui ui = new Ui();
        Stage stage = new Stage();


        Duke program = new Duke();
        program.start(stage);
        program.run(database, tasklist, ui);
    }

    @Override
    public void start(Stage stage) {
        Label dukeStart = new Label("Duke Application is Starting!"); // Creating a new Label control
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

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(new Label("Duke application is starting!!"), new ImageView(duke))
        );

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }


    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    @FXML
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        Circle userClip = new Circle(user.getWidth() / 2, user.getHeight() / 2, user.getWidth() / 2);
        Circle dukeClip = new Circle(duke.getWidth() / 2, duke.getHeight() / 2, duke.getWidth() / 2 );
        ImageView imageViewUser = new ImageView(user);
        ImageView imageViewDuke = new ImageView(duke);
//        imageViewUser.setClip(userClip);
//        imageViewDuke.setClip(dukeClip);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(userText, new ImageView(duke))
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
