import java.util.Scanner;
import java.io.IOException;

import storage.TaskList;
import ui.Ui;
import storage.Storage;
import parser.Parser;
import exception.DukeException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {
    private static final TaskList list = new TaskList();
    private static final Ui ui = new Ui();
    private static final Parser logic = new Parser();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));




    public static void main(String[] args) throws DukeException, IOException {
        ui.printWelcomeMessage();
        ui.showLine();
        Storage.loadData(list);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!logic.checkBye(input)) {
            if (logic.checkList(input)) {
                ui.printListMessage();
                list.list();
                ui.showLine();
            }else if (logic.checkFind(input)) {
                String word = input.split(" ")[1];
                ui.printFindMessgae();
                list.find(word);
                ui.showLine();
            } else if (logic.checkMark(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.mark(num);
                ui.printMarkMessage(list.get(num));
                ui.showLine();
            } else if (logic.checkUnmark(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                list.unmark(num);
                ui.printUnmarkMessage(list.get(num));
                ui.showLine();
           } else if (logic.checkDelete(input)) {
                int num = Integer.parseInt(input.split(" ")[1]);
                ui.printDeleteMessage(list.get(num), list);
                list.removeFind(list.get(num));
                list.delete(num);
                ui.showLine();
            } else if (logic.checkTask(input)) {
                list.add(input);
                ui.printAddMessage(list.getLast(), list);
                ui.showLine();
            } else if (!logic.isValidCommand(input)) {
                ui.printInvalidCommandMessage();
                ui.showLine();
            }
            input = sc.nextLine();
        }
        Storage.saveData(list);
        ui.printByeMessage();
        ui.showLine();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Iteration 1:
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
