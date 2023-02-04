package berry;

import java.io.FileNotFoundException;

import berry.command.Command;
import berry.exception.BerryException;
import berry.parser.Parser;
import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;

/**
 * Initialises the application and starts the interaction with the user.
 */
public class Berry {
    /* Default path for if given file path throws an {@code InvalidStorageFilePathException} */
    public static final String DEFAULT_PATH = "data/tasks.txt";

    // Functionality
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Berry() throws Storage.InvalidStorageFilePathException {
        this(DEFAULT_PATH);
    }

    /**
     * Initialises user interaction interface, storage files and task list.
     *
     * @param filePath is the file path to load data from/save data into
     * @throws Storage.InvalidStorageFilePathException if the given file path is not valid
     */
    public Berry(String filePath) throws Storage.InvalidStorageFilePathException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws Storage.InvalidStorageFilePathException {
        new Berry("data/tasks.txt").run();
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
//                ui.showLine();
                Command c = Parser.parseInput(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BerryException e) {
                ui.showError(e.getMessage());
            } finally {
//                ui.showLine();
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parseInput(input);
            String berryOutput = c.execute(tasks, ui, storage);
            return berryOutput;
        } catch (BerryException e) {
            return e.getMessage();
        }
    }
//
//    @Override
//    public void start(Stage stage) {
//        //Step 1. Formatting the window to look as expected.
//        //The container for the content of the chat to scroll.
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
//        stage.setTitle("Welcome to Berry the Bunny's Chatbox");
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
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        //Scroll down to the end every time dialogContainer's height changes.
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//    }

//    /**
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//    /**
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText.getText(), user),
//                DialogBox.getBerryDialog(dukeText.getText(), duke)
//        );
//        userInput.clear();
//    }
}
