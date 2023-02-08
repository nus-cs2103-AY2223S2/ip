package duke;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class Duke {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Image chatBackground= new Image(this.getClass().getResourceAsStream("/images/ChatBackground.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private MainWindow mainWindow;

    public Ui ui;
    public Storage storage;
    public TaskList listOfTasks;
    public Parser parser;

    /**
     * Constructor for the Duke Class.
     * Initializes Ui, Storage, Parser and TaskList while loading items found in the path file into it.
     */
    public Duke()  {
        ui = new Ui();
        ui.displayLogo();
//        ui.greet();
        storage = new Storage("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt");
        parser = new Parser();

        try {
            listOfTasks = new TaskList();
            listOfTasks.allTasks = storage.loadFile(listOfTasks.getTasks());

        }  catch (IOException i) {
            ui.printText("Remember that since the file cannot be loaded, you cannot save your file!");
        }
    }

    /**
     * Starts running the entire program.
     * Serves as the starting point of the Duke chatbot.
     * @throws FileNotFoundException the executeCommand of the EndCommand class needs save the data into file.
     */
    private void run() throws FileNotFoundException {
        boolean hasExit = false;
        ui.greet();

        while(!hasExit) {
            String input = ui.getInput();
            ui.showLine();
            Command c = parser.parse(input, listOfTasks, ui);
            c.executeCommand(listOfTasks, storage, ui);
            ui.showLine();

            hasExit = c.isExit();
        }
    }

    String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public void runInput(String input) throws FileNotFoundException {

        ui.showLine();
        Command c = parser.parse(input, listOfTasks, ui);
        c.executeCommand(listOfTasks, storage, ui);
        ui.showLine();

    }


    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        ui.setMainWindow(mainWindow);
    }



    /**
     * Main method of the program.
     * Create a Duke object and runs it.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }

//
//    @Override
//    public void start(Stage stage) {
//
//        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
//        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label
//
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        //Step 2. Formatting the window to look as expected
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        //Step 3. Add functionality to handle user input.
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
////        dialogContainer.setBackground(new Background(chatBackground));
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//
//        ImageView userImage = new ImageView(user);
//        ImageView dukeImage = new ImageView(duke);
//
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, userImage),
//                DialogBox.getDukeDialog(dukeText, dukeImage)
//        );
//
//        userInput.clear();
//    }
////dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    private String getResponse(String input) {
//        return "Duke heard: " + input;
//    }




}
