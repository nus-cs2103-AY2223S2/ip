package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * Github id: adam07018
 * @author Lu Chenyu
 */
public class Duke {
    private TaskList list;
    private Storage s;
    private Parser parser;
    private Ui ui;
    private boolean isExit;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * default constructor
     */
    public Duke() {
        list = new TaskList();
        s = new Storage();
        parser = new Parser();
        ui = new Ui();
        isExit = false;
    }

    /**
     * Starting main method
     *
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke a = new Duke();
        a.begin();
    }

    /**
     * Starts chat with user
     * Format for tasks:
     * 1. todo {description}
     * eg. todo buy lunch
     * 2. deadline {description} /by {time}
     * eg. deadline return book /by 2019-10-15 1530
     * 3. event {description} /from {time} /to {time}
     * eg. event read book /from 2019-10-15 1530 /to 2020-12-11 1200
     *
     * @throws DukeException
     */
    public void begin() {
        Scanner sc = new Scanner(System.in);
        ui.sayHello();
        while (sc.hasNext()) {
            String input = sc.nextLine();
            try {
                Command cmd = parser.parse(input);
                cmd.execute(list);
                if (cmd.isExit()) {
                    sc.close();
                    return;
                }
            } catch (DukeException e) {
                continue;
            } catch (NullPointerException e) {
                continue;
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command cmd = parser.parse(input);
            isExit = cmd.isExit();
            return cmd.execute(list);
        } catch (DukeException e) {
            return e.toString();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
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

        //Step 2. Formatting the window to look as expected
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

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    /*

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     * private Label getDialogLabel(String text) {
     *         Label textToAdd = new Label(text);
     *         textToAdd.setWrapText(true);
     *
     *         return textToAdd;
     *     }
     */


    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * private void handleUserInput() {
     *         Label userText = new Label(userInput.getText());
     *         Label dukeText = new Label(getResponse(userInput.getText()));
     *         dialogContainer.getChildren().addAll(
     *                 DialogBox.getUserDialog(userText, new ImageView(user)),
     *                 DialogBox.getDukeDialog(dukeText, new ImageView())
     *         );
     *         userInput.clear();
     *     }
     */

    public boolean getIsExit() {
        return isExit;
    }


    public Ui getUi() {
        return ui;
    }
}
