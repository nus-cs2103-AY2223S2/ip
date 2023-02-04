import java.io.IOException;
import java.util.ArrayList;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasktypes.Task;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main Duke class whereby an instance of the Duke chatbot is initialized.
 */
public class Duke extends Application {

    //These are for the tutorial
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));


    //These are for actual Duke
//    private static ArrayList<Task> storedText = new ArrayList<Task>();
//    private static Ui dukeUi;
//    private static Parser dukeParser;
//    private static Storage dukeStorage;
//    private static TaskList dukeTaskList;
//
//    /**
//     * Constructor to create an instance of Duke chatbot.
//     * @throws IOException
//     * @throws DukeExceptions
//     */
//    Duke() throws IOException, DukeExceptions {
//        dukeUi = new Ui();
//        dukeParser = new Parser();
//        dukeStorage = new Storage("data", "dukedata.txt");
//
//        try {
//            dukeTaskList = new TaskList(dukeStorage.loadTask());
//        } catch (DukeExceptions DE) {
//            System.out.println(DE.toString());
//        }
//    }
//
//    /**
//     * Runs the Duke chatbot program.
//     * @throws IOException
//     * @throws DukeExceptions
//     */
//    public void run() throws IOException, DukeExceptions {
//        String input = dukeUi.gettingUserInput();
//
//        while (!input.equals("bye")) {
//            dukeParser.readInput(input, dukeTaskList);
//            input = dukeUi.gettingUserInput();
//        }
//    }
//
//    /**
//     * Initializes, runs and stores the resulting list of tasks for Duke chatbot.
//     */
//    public static void main(String[] args) throws IOException, DukeExceptions {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
//        Duke initDuke = new Duke();
//        initDuke.run();
//        dukeStorage.storeTask(dukeTaskList.getListOfTasks());
//        System.out.println("Bye! Hope to see you again soon!");
//    }

    @Override
    public void start(Stage stage) {
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
        stage.setResizable(true);
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

        //Handle user input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
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

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
