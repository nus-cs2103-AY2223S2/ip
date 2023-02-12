package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Duke is a program that helps you keep track of tasks
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("../images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("../images/DaDuke.png"));
    private TasksList list = new TasksList(100);
    private UI ui;
    private Storage storage = new Storage(list);
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;

    /**
     * Creates a Duke Object that helps you keep track of tasks
     * @param ui in charge of UI interactions
     * @param parser in charge of making sense of the commands
     */
    public Duke(UI ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * initializes Duke, starts the UI, fetches the data from storage
     * and keeps it in the list
     */
    public void initialize() {
        assert !ui.equals(null) && !storage.equals(null);
        ui.start();
        storage.findData();
        storage.loadData();
        ui.start();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
    }

    /**
     * Initializes the stage of the main application page
     */
    @Override
    public void start(Stage stage) {
        assert !stage.equals(null);

        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);

        setStage(stage, scene);
        mainLayout.setPrefSize(400.0, 600.0);
        setScrollPane();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        setActionsOne(userInput, sendButton);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        setActionsTwo(userInput, sendButton);
    }

    /**
     * set actions of the user input as well as the send buttion
     * @param userInput command that the user types in
     * @param sendButton Send button that initiates an action from the user command
     */
    private void setActionsOne(TextField userInput, Button sendButton) {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
    
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
    }

    /**
     * Sets again
     * @param userInput command that the user types in
     * @param sendButton Send button that initiates an action from the user command
     */
    private void setActionsTwo(TextField userInput, Button sendButton) {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });
    }

    /**
     * Sets the stage of the main application page
     * @param stage Stage of the main application page
     * @param scene Scene of the main application page
     */
    private void setStage(Stage stage, Scene scene) {
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(1000.0);
        stage.setMinWidth(400.0);
    }

    /**
     * Sets the scroll Pane of the main application page
     */
    private void setScrollPane() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        assert text.length() > 0;
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput(TextField userInput, VBox dialogContainer) {
        assert !userInput.equals(null) && !dialogContainer.equals(null);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * Get the response of the user input
     * @param input
     * @return
     */
    public String getResponse(String input) {
        assert !input.equals(null);
        return response(ui, storage, parser, list, input);
    }


    /**
     * Starts the duke program and checks for various commands
     * @param ui UI onject responsible for User interactions
     * @param storage Stores your tasks in the program
     * @param parser formats your commands that helps Duke read
     * @param list list of Tasks
     * @return the response from the command
     */

    public String response(UI ui, Storage storage, Parser parser, TasksList list, String command) {
        assert !ui.equals(null) && !storage.equals(null) && !parser.equals(null) 
            && !list.equals(null) && command.length() > 0;

        String[] commandArr = command.split(" ");
        try {
            DukeExceptions.checkCommand(commandArr);
            if (commandArr[0].equals("todo")) {
                return todoResponse(commandArr, command);
            } else if (commandArr[0].equals("deadline")) {
                return deadlineResponse(commandArr, command);
            } else if (commandArr[0].equals("event")) {
                return eventResponse(commandArr, command);
            } else if (commandArr[0].equals("mark")) {
                return markResponse(commandArr, command);
            } else if (commandArr[0].equals("unmark")) {
                return unmarkResponse(commandArr, command);
            } else if (commandArr[0].equals("delete")) {
                return deleteResponse(commandArr, command);
            } else if (commandArr[0].equals("list")) {
                return listResponse(commandArr, command);
            } else if (commandArr[0].equals("find")) {
                return findResponse(commandArr, command);
            } else if (commandArr[0].equals("bye")) {
                return byeResponse();
            } else if (command.equals("greet")) {
                return ui.start();
            } else {
                return "Should not reach this condition";
            }
        } catch (DontKnowWhatThatMeansException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a todo command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String todoResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.addTodo(list, parser.getTodoDescription(command));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a deadline command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String deadlineResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.addDeadline(list, parser.getDeadlineDescription(command), parser.getDeadlineby(command));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a event command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String eventResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.addEvent(list, parser.getEventDescription(command),
                parser.getEventFrom(command), parser.getEventEnd(command));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a mark command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String markResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.mark(list, parser.getMarkNum(command, true));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a unmark command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String unmarkResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.mark(list, parser.getMarkNum(command, false));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a deadline command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String deleteResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.removeTask(list, parser.getMarkNum(command, false));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a list command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String listResponse(String[] commandArr, String command) {
        return ui.showList(list);
    }

    /**
     * Gets the response from a find command
     * @param commandArr array of strings from command String
     * @param command command from user input
     * @return the response from the command
     */
    private String findResponse(String[] commandArr, String command) {
        try {
            DukeExceptions.checkEmptyDescription(commandArr);
            return ui.findTask(list, parser.getKeyword(command));
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        }
    }

    /**
     * Gets the response from a bye command
     * @return the response from the command
     */
    private String byeResponse() {
        storage.saveData();
        return ui.showExit();
    }
}
