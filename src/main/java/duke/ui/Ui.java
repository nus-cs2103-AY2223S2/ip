package duke.ui;

import duke.exception.DukeException;
import duke.Duke;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.Text;

public class Ui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private HBox topTasks;
    private VBox todoContainer;
    private TextField todoInput;
    private TextField deadlineDescription;
    private TextField deadlineDate;
    private VBox deadlineContainer;
    private Text deadlineInput;
    private TextField userInput;
    private Button todoSend;
    private Button deadlineSend;
    private Button sendButton;
    private Button todoButton;
    private Button deadlineButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.jpg"));
    private Duke dukeList;
    private Label deadlineDescriptionLabel;
    private Label deadlineDateLabel;
    private Label taskDescription;
    private VBox eventContainer;
    private Button eventButton;
    private Button eventSend;
    private Label eventDescriptionLabel;
    private TextField eventDescription;
    private Label eventDateStartLabel;
    private TextField eventDateStart;
    private Label eventDateEndLabel;
    private TextField eventDateEnd;
    private Button listButton;
    private VBox markContainer;
    private Button markButton;
    private Button markSend;
    private Label markDescriptionLabel;
    private TextField markDescription;
    private VBox unmarkContainer;
    private Button unmarkButton;
    private Button unmarkSend;
    private Label unmarkDescriptionLabel;
    private TextField unmarkDescription;
    private VBox deleteContainer;
    private Button deleteButton;
    private Button deleteSend;
    private Label deleteDescriptionLabel;
    private TextField deleteDescription;
    private VBox findContainer;
    private Button findButton;
    private Button findSend;
    private Label findDescriptionLabel;
    private TextField findDescription;

    public Ui() {

    }



    @Override
    public void start(Stage stage) {
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        topTasks = new HBox();

        userInput = new TextField();
        sendButton = new Button("Send");

        todoButton = new Button("ToDo");

        deadlineButton = new Button("Deadline");

        eventButton = new Button("Event");

        listButton = new Button("List");

        markButton = new Button("Mark");

        unmarkButton = new Button("UnMark");

        deleteButton = new Button("Delete");

        findButton = new Button("Find");

        todoSend = new Button("Add Task");

        deadlineSend = new Button("Add Task");

        eventSend = new Button("Add Task");

        markSend = new Button("Mark Task");

        unmarkSend = new Button("UnMark Task");

        deleteSend = new Button("Delete Task");

        findSend = new Button("Find");

        todoContainer = new VBox();

        deadlineContainer = new VBox();

        eventContainer = new VBox();

        markContainer = new VBox();

        unmarkContainer = new VBox();

        deleteContainer = new VBox();

        findContainer = new VBox();

        topTasks.getChildren().addAll(todoButton, deadlineButton, eventButton, listButton, markButton, unmarkButton, deleteButton, findButton);
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, topTasks, todoContainer, deadlineContainer, eventContainer, markContainer
        , unmarkContainer, deleteContainer, findContainer);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();


        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(810.0);

        mainLayout.setPrefSize(800.0, 800.0);

        scrollPane.setPrefSize(785, 225);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(675.0);

        sendButton.setPrefWidth(100.0);

        todoButton.setPrefWidth(100.0);

        deadlineButton.setPrefWidth(100.0);

        eventButton.setPrefWidth(100.0);

        listButton.setPrefWidth(100.0);

        markButton.setPrefWidth(100.0);

        unmarkButton.setPrefWidth(100.0);

        deleteButton.setPrefWidth(100.0);

        findButton.setPrefWidth(100.0);

        todoSend.setPrefWidth(100.0);

        deadlineSend.setPrefWidth(100.0);

        eventSend.setPrefWidth(100.0);

        markSend.setPrefWidth(100.0);

        unmarkSend.setPrefWidth(150.0);

        deleteSend.setPrefWidth(150.0);

        findSend.setPrefWidth(100.0);


        AnchorPane.setBottomAnchor(scrollPane, 55.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        AnchorPane.setTopAnchor(topTasks, 1.0);
        AnchorPane.setLeftAnchor(topTasks, 1.0);

        AnchorPane.setTopAnchor(todoContainer, 40.0);
        AnchorPane.setTopAnchor(deadlineContainer, 40.0);
        AnchorPane.setTopAnchor(eventContainer, 40.0);
        AnchorPane.setTopAnchor(markContainer, 40.0);
        AnchorPane.setTopAnchor(unmarkContainer, 40.0);
        AnchorPane.setTopAnchor(deleteContainer, 40.0);
        AnchorPane.setTopAnchor(findContainer, 40.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        todoButton.setOnMouseClicked((event) -> {
            handleTodo();
        });

        todoSend.setOnMouseClicked((event) -> {
            handleToDoInput();
        });

        deadlineButton.setOnMouseClicked((event) -> {
            handleDeadline();
        });

        deadlineSend.setOnMouseClicked((event) -> {
            handleDeadlineInput();
        });

        eventButton.setOnMouseClicked((event) -> {
            handleEvent();
        });

        eventSend.setOnMouseClicked((event) -> {
            handleEventInput();
        });

        listButton.setOnMouseClicked((event) -> {
            handleList();
        });

        markButton.setOnMouseClicked((event) -> {
            handleMark();
        });

        markSend.setOnMouseClicked((event) -> {
            handleMarkInput();
        });

        unmarkButton.setOnMouseClicked((event) -> {
            handleUnMark();
        });

        unmarkSend.setOnMouseClicked((event) -> {
            handleUnMarkInput();
        });
        deleteButton.setOnMouseClicked((event) -> {
            handleDelete();
        });

        deleteSend.setOnMouseClicked((event) -> {
            handleDeleteInput();
        });
        findButton.setOnMouseClicked((event) -> {
            handleFind();
        });

        findSend.setOnMouseClicked((event) -> {
            handleFindInput();
        });

        taskDescription = new Label("Task Description:");
        todoInput = new TextField();


        deadlineDescriptionLabel = new Label("Task Description:");
        deadlineDescription = new TextField();
        deadlineDateLabel = new Label("Deadline (yyyy-mm-dd HHMM(24-hr clock))");
        deadlineDate = new TextField();

        eventDescriptionLabel = new Label("Task Description:");
        eventDescription = new TextField();
        eventDateStartLabel = new Label("Start (yyyy-mm-dd HHMM(24-hr clock))");
        eventDateStart = new TextField();
        eventDateEndLabel = new Label("End (yyyy-mm-dd HHMM(24-hr clock))");
        eventDateEnd = new TextField();

        markDescriptionLabel = new Label("Which task to mark?");
        markDescription = new TextField();

        unmarkDescriptionLabel = new Label("Which task to unmark?");
        unmarkDescription = new TextField();

        deleteDescriptionLabel = new Label("Which task to delete?");
        deleteDescription = new TextField();

        findDescriptionLabel = new Label("Keyword:");
        findDescription = new TextField();


        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dukeList = new Duke();
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
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private void handleTodo() {
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        todoContainer.getChildren().addAll(taskDescription, todoInput, todoSend);
    }

    private void handleToDoInput() {
        Label dukeText = new Label(getResponse("todo " + todoInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        todoInput.clear();

    }
    private void handleDeadline() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        deadlineContainer.getChildren().addAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);

    }

    private void handleDeadlineInput() {
        Label dukeText = new Label(getResponse("deadline " + deadlineDescription.getText() + " /by "  + deadlineDate.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        deadlineDescription.clear();
        deadlineDate.clear();

    }
    private void handleEvent() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        eventContainer.getChildren().addAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);


    }

    private void handleEventInput() {
        Label dukeText = new Label(getResponse("event " + eventDescription.getText() + " /from "  + eventDateStart.getText()
        + " /to " + eventDateEnd.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        eventDescription.clear();
        eventDateStart.clear();
        eventDateEnd.clear();

    }

    private void handleList() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        Label dukeText = new Label(getResponse("list"));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

    }

    private void handleMark() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        markContainer.getChildren().addAll(markDescriptionLabel, markDescription, markSend);

    }

    private void handleMarkInput() {
        Label dukeText = new Label(getResponse("mark " + markDescription.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        markDescription.clear();

    }

    private void handleUnMark() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        unmarkContainer.getChildren().addAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);

    }

    private void handleUnMarkInput() {
        Label dukeText = new Label(getResponse("unmark " + unmarkDescription.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        unmarkDescription.clear();

    }
    private void handleDelete() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        findContainer.getChildren().removeAll(findDescriptionLabel, findDescription, findSend);
        deleteContainer.getChildren().addAll(deleteDescriptionLabel, deleteDescription, deleteSend);

    }

    private void handleDeleteInput() {
        Label dukeText = new Label(getResponse("delete " + deleteDescription.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        deleteDescription.clear();

    }
    private void handleFind() {
        todoContainer.getChildren().removeAll(taskDescription, todoInput, todoSend);
        deadlineContainer.getChildren().removeAll(deadlineDescriptionLabel, deadlineDescription, deadlineDateLabel, deadlineDate, deadlineSend);
        eventContainer.getChildren().removeAll(eventDescriptionLabel, eventDescription, eventDateStartLabel, eventDateStart
                , eventDateEndLabel, eventDateEnd, eventSend);
        markContainer.getChildren().removeAll(markDescriptionLabel, markDescription, markSend);
        unmarkContainer.getChildren().removeAll(unmarkDescriptionLabel, unmarkDescription, unmarkSend);
        deleteContainer.getChildren().removeAll(deleteDescriptionLabel, deleteDescription, deleteSend);
        findContainer.getChildren().addAll(findDescriptionLabel, findDescription, findSend);

    }

    private void handleFindInput() {
        Label dukeText = new Label(getResponse("find " + findDescription.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        findDescription.clear();

    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {

        return dukeList.run(input);
    }


    public void showLoadingError() {
        System.out.println("Error initialising DUKE! File Corrupted!");
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLine() {
        for (int n = 0; n < 50; n++) {
            System.out.print("-");
        }
        System.out.print("\n");

    }


    public String showError(String message) {

        //System.out.println(message);
        return message;
    }

    public String readCommand(TaskList tasks, int currIteration) throws DukeException {
        String result = tasks.printCommands(currIteration);
        if (result == null) {
            throw new DukeException("End of command!");
        }
        System.out.println(result);
        return result;
    }

}
