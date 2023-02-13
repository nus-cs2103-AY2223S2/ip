package duke;

import java.util.Scanner;
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


/**
 * Class that defines the Duke task list manager
 */
public class Duke extends Application {

    private static Storage fileManager = new Storage();
    private static Parser parser = new Parser();
    private static TaskList taskList = fileManager.read();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user_img.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke_img.png"));

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
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 700.0);

        scrollPane.setPrefSize(490, 630);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(420.0);
        userInput.setPrefHeight(30.0);

        sendButton.setPrefWidth(60.0);
        sendButton.setPrefHeight(30.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     *  Returns a response based on the user's input
     * @param input The user's input
     * @return String that is Duke's response based on the user's input
     */
    private String getResponse(String input) {
        return parser.parse(input, taskList, fileManager);
    }

    /**
     * Adds a task to the taskList.
     *
     * @param listItem Task to be added to taskList
     * @return String to inform the user that the specified task has been added to the taskList
     */
    public static String addToList(Task listItem) {
        taskList.addTask(listItem);
        String result = "I've added the following task to your list:\n";
        result += listItem.toString() + "\nCurrent tasks count: " + (taskList.size());
        return result;
    }

    /**
     * Removes the task at a specified position in taskList
     *
     * @param pos Position in taskList at which the task to be removed is stored
     * @return String to inform the user that the specified task has been removed from the taskList
     */
    public static String removeFromList(int pos) {
        Task curr = null;
        try {
            curr = taskList.deleteTask(pos - 1);
        } catch (IndexOutOfBoundsException ie) {
            return ie.getMessage();
        }
        String result = "I've removed the following task from your list:\n";
        result += curr.toString() + "\nCurrent tasks count: " + (taskList.size());
        return result;
    }

    /**
     * Prints the contents of the taskList
     *
     * @return String listing the tasks in taskList
     */
    public static String displayList() {
        if (taskList.size() == 0) {
            return "Your list is currently empty, you can add tasks using the commands specified in the user guide.";
        }
        return "Here are the tasks in your list:\n" + taskList.printList();
    }

    /**
     * Greets user, awaits user input and updates/displays taskList accordingly.
     *
     * @param args Commands from user, to interact with taskList
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Ui.greet();

        String userInput =  sc.nextLine();
        String exitCommand = "bye";

        while (!userInput.equals(exitCommand)) {
            System.out.println(parser.parse(userInput, taskList, fileManager));
            userInput = sc.nextLine();
        }
        Ui.exit();
        System.exit(0);
    }
}
