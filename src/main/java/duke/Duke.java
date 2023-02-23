package duke;

import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;



/**
 * This is the main class that serves as the entry point into the application
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Storage storage;
    private Parser parser;
    private UI ui;
    private TaskList list;

    /**
     * Constructor for a Duke object
     */
    public Duke () {
        ui = new UI();
        list = new TaskList();
        storage = new Storage(list);
        parser = new Parser(storage);
    }


    /**
     * Method to start the interactive process of obtaining the user input and performing the
     * tasks requested by the user
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.printWelcome();
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            try {
                parser.parseAndExecute(userInput, list);
            } catch (IOException e) {
                ui.printInvalidDateFormatMessage();
                userInput = scanner.nextLine();
                continue;
            }
            ui.printNextCommandMessage();
            userInput = scanner.nextLine();

        }
        System.out.println("Thanks and have a great day ahead!");

    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        Scene scene;

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show(); // Render the stage.

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

        welcomeUser(dialogContainer);

        sendButton.setOnMouseClicked((event) -> {
           handleUserInput();
        });

        userInput.setOnAction((event) -> {
           handleUserInput();
        });


        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private void welcomeUser(VBox dialogBox) {
        // the idea for the implementation for this method was taken from 0x787af25e
        Label welcomeMessage = new Label("Hi there! Please enter your request in the box below!");
        dialogBox.getChildren().addAll(DialogBox.getDukeDialog(welcomeMessage, new ImageView(duke)));
    }

    private String getResponse(String input) {

            try {
                String output = parser.parseAndExecute(input, list);
                return output;
            } catch (IOException e) {
                String output = ui.printInvalidDateFormatMessage();
                return output;
            }




    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }




}






