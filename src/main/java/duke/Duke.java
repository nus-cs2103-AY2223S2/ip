package duke;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeTaskArgumentException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeInvalidArgumentsException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents the chatbot
 */
public class Duke extends Application{
    private static String FILEPATH = "./data/duke.txt";
    private TaskList list;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/dukeUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/dukeBot.png"));

    /**
     * Initializes a Duke object
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        this.list = new TaskList(this.storage.loadData());
        this.parser = new Parser();
    }

    private enum DukeCommand {
        BYE, LIST,
        MARK, UNMARK,
        TODO, DEADLINE, EVENT,
        DELETE,
        LISTDATE, FIND,
        FAIL
    }


    protected String getResponse(String input) {
        String response = null;
        String[] userInput = this.parser.parseUserInput(input);
        String dukeQuery = userInput[0];

        //to check if the command is a valid command
        DukeCommand dukeCommand = DukeCommand.FAIL;
        for (DukeCommand c : DukeCommand.values()) {
            if (c.name().equals(dukeQuery.toUpperCase())) {
                dukeCommand = DukeCommand.valueOf(dukeQuery.toUpperCase());
            }
        }

        try {
            switch (dukeCommand) {
                case BYE :
                    this.storage.storeData(this.list);
                    response = this.ui.exitDisplay();
                    this.ui.closeInput();
                    break;
                case LIST :
                    response = this.ui.displayList(this.list);
                    break;
                case MARK :
                    response = this.markComplete(userInput);
                    break;
                case UNMARK :
                    response = this.markIncomplete(userInput);
                    break;
                case TODO :
                    response = this.addTodo(userInput);
                    break;
                case DEADLINE :
                    response = this.addDeadline(userInput);
                    break;
                case EVENT :
                    response = this.addEvent(userInput);
                    break;
                case DELETE :
                    response = this.deleteTask(userInput);
                    break;
                case LISTDATE:
                    response = this.displayTasksWithDates(userInput);
                    break;
                case FIND:
                    response = this.findTasks(userInput);
                    break;
                case FAIL :
                    throw new DukeInvalidCommandException();
                default :
                    throw new DukeInvalidCommandException();

            }
        } catch (DukeException e) {
            response = String.format("%s\n", e);
        } catch (IOException e) {
            response = "Error while storing data..";
        }
        return response;
    }

    private String markComplete(String[] userInput) throws DukeInvalidArgumentsException, DukeMissingArgumentException, DukeTaskArgumentException {
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.getListLength()) {
                throw new DukeTaskArgumentException();
            }
            if (list.getTask(taskIndex).getStatus()) {
                throw new DukeTaskArgumentException();
            }

            Task taskToBeMarked = this.list.getTask(taskIndex);
            taskToBeMarked.changeStatus();
            return this.ui.markTaskDisplay(taskToBeMarked);
        } catch (NumberFormatException e){
            throw new DukeInvalidArgumentsException();
        } catch (IndexOutOfBoundsException e) {
            String task = "mark";
            throw new DukeMissingArgumentException(task);
        }
    }

    private String markIncomplete(String[] userInput) throws DukeMissingArgumentException, DukeInvalidArgumentsException, DukeTaskArgumentException{
        try {
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.getListLength()) {
                throw new DukeTaskArgumentException();
            }
            if (list.getTask(taskIndex).getStatus() == false) {
                throw new DukeTaskArgumentException();
            }
            Task taskToBeUnmarked = this.list.getTask(taskIndex);
            taskToBeUnmarked.changeStatus();
            return this.ui.unmarkTaskDisplay(taskToBeUnmarked);
        }  catch(IndexOutOfBoundsException e) {
            String task = "unmark";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private String addTodo(String[] userInput) throws DukeMissingArgumentException{
        try {
            Todo todo = new Todo(userInput[1]);
            this.list.addTask(todo);
            return this.ui.taskAddDisplay(todo, this.list.getListLength());
        } catch (IndexOutOfBoundsException e) {
            String task = "todo";
            throw new DukeMissingArgumentException(task);
        }
    }

    private String addDeadline(String[] userInput) throws DukeMissingArgumentException {
        try {
            String deadlineInfo[] = userInput[1].split("/by");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String deadlineText = deadlineInfo[0].trim();
            LocalDateTime deadlineDate = LocalDateTime.parse(deadlineInfo[1].trim(), formatter);
            Deadline deadline = new Deadline(deadlineText, deadlineDate);
            this.list.addTask(deadline);
            return this.ui.taskAddDisplay(deadline, this.list.getListLength());
        } catch(IndexOutOfBoundsException e) {
            String task = "deadline";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            return "Invalid Date format (Required format: DD/MM/YYYY HH:MM)";
        }
    }

    private String addEvent(String[] userInput) throws DukeMissingArgumentException{
        try {
            String eventInfo[] = userInput[1].split("/from|/to");
            String eventText = eventInfo[0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime eventFrom = LocalDateTime.parse(eventInfo[1].trim(), formatter);
            LocalDateTime eventTo = LocalDateTime.parse(eventInfo[2].trim(), formatter);

            Event event = new Event(eventText, eventFrom, eventTo);
            this.list.addTask(event);
            return this.ui.taskAddDisplay(event, this.list.getListLength());
        } catch (IndexOutOfBoundsException e) {
            String task = "event";
            throw new DukeMissingArgumentException(task);
        } catch(DateTimeParseException e) {
            return "Invalid Date format (Required format: DD/MM/YYYY HH:MM)";
        }
    }

    private String deleteTask(String[] userInput) throws DukeTaskArgumentException, DukeMissingArgumentException, DukeInvalidArgumentsException{
        try{
            int taskIndex = Integer.parseInt(userInput[1]);
            if(taskIndex > this.list.getListLength()) {
                throw new DukeTaskArgumentException();
            }
            String deleteResponse = this.ui.taskDeleteDisplay(this.list, taskIndex) + '\n' + this.ui.displayTasks(this.list.getListLength() - 1);
            this.list.deleteTask(taskIndex);
            return deleteResponse;
        } catch(IndexOutOfBoundsException e) {
            String task = "delete";
            throw new DukeMissingArgumentException(task);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentsException();
        }
    }

    private String displayTasksWithDates(String[] userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(userInput[1], formatter);
        int counter = 1;
        String response = "";
        for(int i = 0; i < this.list.getListLength(); i++) {
            String taskType = this.list.getTask(i).getTaskType();
            LocalDate taskDate = this.list.getTask(i).getDate().toLocalDate();
            if(taskType.equals("D") || taskType.equals(("E"))){
                if(date.equals(taskDate)) {
                    response += String.format("\t%d. %s", counter, this.list.getTask(i).toString()) + '\n';
                }
            }
        }
        return response;
    }

    private String findTasks(String[] userInput) {
        String keyword = userInput[1];
        if(this.list.isEmpty()) {
            return "There is not no task in the list.";
        }

        ArrayList<Task> filteredTasks= new ArrayList<>();
        TaskList foundTasks = new TaskList(filteredTasks);
        for(int i = 1; i <= this.list.getListLength(); i++) {
            Task task = this.list.getTask(i);
            if(task.toString().contains(keyword)){
                foundTasks.addTask(task);
            }
        }

        if(foundTasks.isEmpty()) {
            return this.ui.noMatchFoundDisplay();
        } else {
            return this.ui.matchFoundDisplay(foundTasks);
        }
    }

    public static void main(String[] args)  {
        Duke dukeObj = new Duke();
        dukeObj.ui.initialDisplay();
    }

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
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }



}
