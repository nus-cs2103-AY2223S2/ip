package duke;

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


import duke.command.Command;
import duke.parser.CommandType;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a chatbot that one can interact with to keep track of tasks.
 */
public class Duke extends Application {
    //GUI elements
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    //Bot elements
    private Ui userInterface;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;


    /**
     * Gets the chatbot instance running.
     */
    public void run() {
        //Prepare components
        Ui userInterface = new Ui();
        Storage storage = new Storage("data", "tasks.txt");
        TaskList taskList = new TaskList();
        Parser parser = new Parser(userInterface, taskList);


        //Prepare data file
        if (!storage.prepareFile()) {
            //Shuts down the bot as data file cannot be created successfully
            userInterface.printShutDownMessage();
            userInterface.cleanUpUi();
            return;
        }

        //Load data from data file
        if (!storage.loadTasksFromFile(taskList)) {
            //Cannot read from data file. Start with new empty task list.
            taskList = new TaskList();
        }

        //Read in and process user commands
        while (true) {
            //Process command
            String rawCommand = userInterface.getUserCommand();
            CommandType commandType = parser.parseRawCommand(rawCommand);
            Command command = parser.parseCommandType(commandType, taskList, storage);

            //Incorrect format
            if (command == null) {
                continue;
            } else {
                //Run command
                command.runCommand();

                //Check for exit command
                if (command.isExit()) {
                    break;
                }
            }
        }
    }


    /**
     * Launches the chatbot.
     *
     * @param args The command line arguments that one can type.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Acts as the entry point when Launcher launches the Javafx application.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage stage) {
        //Step 0: Prepare and initialise the bot
        initialiseBot();

        //Step 1: Setting up the required components

        //The container for the content of the chat to scroll.
        //This is because we want to display multiple Nodes together in the Scene.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);


        //Sets the scene onto the stage
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Step 2: Format the window to look as expected

        //Customise the title and dimensions of the window
        stage.setTitle("Boo");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        //Horizontal scroll bar of scroll pane never to be shown
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //Vertical scroll bar of scroll pane always to be shown
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        //Anchors the scroll pane with respect to the anchor pane
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        //Anchors the send button with respect to the anchor pane
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        //Anchors the user input text field with respect to the anchor pane
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);


        //Step 3: Add functionality to handle user input

        /* Event handler for send button.
           When user clicks send, a label containing the user's input will be created
           and set as a child of the dialog container.
        */
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });


        /* Event handler for the user input text field
           When the user presses enter while the text field is active, it will create
           a label containing the user's input and set as a child of the dialog container.
        */
        userInput.setOnAction((event) -> {
            handleUserInput();
        });


        //Make scroll pane responsive by automatically scrolling down if VBox stretches beyond the scroll pane.
        //Scrolls down to the end everytime the dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));


    }


//    /**
//     * Creates a label with the specified text and adds it to the dialog container.
//     *
//     * @param text String containing text to be added.
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//        return textToAdd;
//    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing the bot's reply,
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText())); //Just echo for now
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    /**
     * Stub function that generates a response to user input
     */
    private String getResponse(String input) {
        CommandType commandType = parser.parseRawCommand(input);
        Command command = parser.parseCommandType(commandType, taskList, storage);
        //Incorrect format
        if (command == null) {
            return "An error occurred. Please try again.";
        } else {

            //Run command
            command.runCommand();

            //Check for exit command
            if (command.isExit()) {
                break;
            }
        }
    }



        return "Duke heard: " + input;
    }


    /**
     * Initialises the bot by setting up required components
     */
    private void initialiseBot() {
        //Initialise components
        userInterface = new Ui();
        storage = new Storage("data", "tasks.txt");
        taskList = new TaskList();
        parser = new Parser(userInterface, taskList);

        //Prepare data file
        if (!storage.prepareFile()) {
            System.exit(1);
        }

        if (!storage.loadTasksFromFile(taskList)) {
            //Cannot read from data file. Start with new empty task list.
            taskList = new TaskList();
        }
    }

//
//    //Read in and process user commands
//        while (true) {
//        //Process command
//        String rawCommand = userInterface.getUserCommand();
//        CommandType commandType = parser.parseRawCommand(rawCommand);
//        Command command = parser.parseCommandType(commandType, taskList, storage);
//
//        //Incorrect format
//        if (command == null) {
//            continue;
//        } else {
//            //Run command
//            command.runCommand();
//
//            //Check for exit command
//            if (command.isExit()) {
//                break;
//            }
//        }
//    }







}



