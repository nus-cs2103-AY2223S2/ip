package duke;
import duke.task.*;
import duke.command.*;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * main duke class. helps abstract all the other components
 */
public class Duke extends Application {
    private static Ui ui; //deal with user interactionss
    private static TaskList taskList;
    private static Storage storage;
    private static ArrayList<Task> list;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try { 
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    public Duke() {
        String filePath = "./duke.txt";
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try { 
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }
    @Override
    public void start(Stage stage) {
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
        //step 2 set label properties 
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
        stage.show();
        //Step 3. Add functionality to handle user input.
           sendButton.setOnMouseClicked((event) -> {
        dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
        userInput.clear();
    });

    userInput.setOnAction((event) -> {
        dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
        userInput.clear();
    }); 
    }

    private String getResponse(String input) {
        return "Duke heard: " + "nothing";
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }  

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke("./duke.txt");
        duke.ui.printLogo();
        while (true) {
            System.out.print('\n');
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if (in.equals("bye")) {
                System.out.println("No don't go!!");
                break;
            }
            ui.line(in.length());
            try {
                Command c = Parser.parseIn(in);
                c.execute(taskList, ui, storage);
                duke.storage.save(taskList);
            } catch (DukeException e) {
                //empty catch, error should have been handled by parser or command
            }
            ui.line(in.length());
        }
    }
}
