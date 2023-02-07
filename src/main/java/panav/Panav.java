package panav;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import panav.command.Command;
import panav.command.ExitCommand;
import panav.command.ListCommand;
import panav.exception.DukeException;
import panav.parser.Parser;
import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;



/**
 * The class where Panav starts. It contains the main() and all functionality starts here.
 */
public class Panav extends Application {

    private String filePath = "C:\\Users\\panav\\OneDrive\\Desktop\\CS2103T\\ip\\src\\main\\java\\data\\panav.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isExit;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/stick.png"));
    private Image panav = new Image(this.getClass().getResourceAsStream("/images/panav.jpeg"));
    /**
     * Constructor to initialise the various classes in program.
     * @param filePath the path of the file to be read from.
     */
    public Panav(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }


    }

    public Panav() {}

    @Override
    public void start(Stage stage) {
        //Requirements for Panav
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

        //Step 1. Setting up required components

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

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Panav");
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        Label welcome = new Label(ui.showWelcome());
        dialogContainer.getChildren().addAll(new DialogBox(welcome, new ImageView(panav), true));
        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        if (isExit) {
            Platform.exit();
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label panavText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getPanavDialog(panavText, new ImageView(panav))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        //return "Duke heard: " + input;
        try {
            Command c = Parser.parse(input);
            String res = c.execute(tasks, ui, storage);
            if (c.toString().compareTo(ListCommand.COMMAND_WORD) != 0
                    || c.toString().compareTo(ExitCommand.COMMAND_WORD) != 0) {
                storage.write(tasks);
            }
            if (input.compareTo("bye") == 0) {
                isExit = true;
            }
            return res;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException();
        }
    }


    /**
     * The method which is called as soon as Panav is loaded up. Controls the flow of the
     * program and runs till user types 'bye'.
     */
//    public void run() {
//        ui.showWelcome();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                if (c.toString().compareTo(ListCommand.COMMAND_WORD) != 0
//                        || c.toString().compareTo(ExitCommand.COMMAND_WORD) != 0) {
//                    storage.write(tasks);
//                }
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (NullPointerException e) {
//                continue;
//            } finally {
//                ui.showLine();
//            }
//        }
//    }

    /*
    public static void main(String[] args) {
        new Panav("C:\\Users\\panav\\OneDrive\\Desktop\\CS2103T\\ip\\src\\main\\java\\data\\panav.txt").run();
    } */
}

