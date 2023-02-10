package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

import java.util.Scanner;

/**
 * Duke is a program that helps you keep track of tasks
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("../images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("../images/DaDuke.png"));
    TasksList list = new TasksList(100);
    UI ui;
    Storage storage = new Storage(list);
    Parser parser;
    ScrollPane scrollPane;
    VBox dialogContainer;
    MainWindow mainWindow = new MainWindow();
    

    public Duke(UI ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    public void initialize() {
        System.out.println("starting 9");
        ui.start();
        storage.findData();
        storage.loadData();
        ui.start();
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
    }

    @Override
    public void start(Stage stage) {
        System.out.println("starting 2");
        //ScrollPane scrollPane = new ScrollPane();
        //VBox dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        TextField userInput = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(1000.0);
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
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });
    
        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(userInput, dialogContainer);
        });
    
        userInput.setOnAction((event) -> {
            handleUserInput(userInput, dialogContainer);
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        System.out.println("Starting 3");
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public void handleUserInput(TextField userInput, VBox dialogContainer) {
        System.out.println("Starting 4");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        System.out.println("Starting 5");
        return response(ui, storage, parser, list, input);
    }


    /**
     * Starts the duke program and checks for various commands
     * @param ui UI onject responsible for User interactions
     * @param storage Stores your tasks in the program
     * @param parser formats your commands that helps Duke read
     * @param list list of Tasks
     */

    public String response(UI ui, Storage storage, Parser parser, TasksList list, String command) {
        String[] commandArr = command.split(" ");
        try {
            DukeExceptions.checkCommand(commandArr);
            if (commandArr[0].equals("todo")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.addTodo(list, parser.getTodoDescription(command));
                } catch (EmptyDescriptionException e) {
                    //System.out.println(e.getMessage());
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("deadline")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.addDeadline(list, parser.getDeadlineDescription(command), parser.getDeadlineby(command));
                } catch (EmptyDescriptionException e) {
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("event")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.addEvent(list, parser.getEventDescription(command),
                        parser.getEventFrom(command), parser.getEventEnd(command));
                } catch (EmptyDescriptionException e) {
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("mark")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.mark(list, parser.getMarkNum(command, true));
                } catch (EmptyDescriptionException e) {
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("unmark")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.mark(list, parser.getMarkNum(command, false));
                } catch (EmptyDescriptionException e) {
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("delete")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.removeTask(list, parser.getMarkNum(command, false));
                } catch (EmptyDescriptionException e) {
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("list")) {
                return ui.showList(list);
            } else if (commandArr[0].equals("find")) {
                try {
                    DukeExceptions.checkEmptyDescription(commandArr);
                    return ui.findTask(list, parser.getKeyword(command));
                } catch (EmptyDescriptionException e) {
                    return e.getMessage();
                }
            } else if (commandArr[0].equals("bye")) {
                storage.saveData();
                return ui.showExit();
            } else if (command.equals("greet")) {
                return ui.start();
            } else {
                return "Should not reach this condition";
            }
        } catch (DontKnowWhatThatMeansException e) {
            return e.getMessage();
        }
    }

    public static void begin(UI ui, Storage storage, Parser parser, TasksList list) {
        Scanner input = new Scanner(System.in);
        String command;
        while (true) {
            command = input.nextLine();
            String[] commandArr = command.split(" ");
            try {
                DukeExceptions.checkCommand(commandArr);
                if (commandArr[0].equals("todo")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.addTodo(list, parser.getTodoDescription(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("deadline")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.addDeadline(list, parser.getDeadlineDescription(command), parser.getDeadlineby(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("event")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.addEvent(list, parser.getEventDescription(command),
                            parser.getEventFrom(command), parser.getEventEnd(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("mark")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.mark(list, parser.getMarkNum(command, true));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("unmark")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.mark(list, parser.getMarkNum(command, false));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("delete")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.removeTask(list, parser.getMarkNum(command, false));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("list")) {
                    ui.showList(list);
                } else if (commandArr[0].equals("find")) {
                    try {
                        DukeExceptions.checkEmptyDescription(commandArr);
                        ui.findTask(list, parser.getKeyword(command));
                    } catch (EmptyDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (commandArr[0].equals("bye")) {
                    storage.saveData();
                    ui.showExit();
                    return;
                } else {
                    System.out.println("Should not reach this condition");
                }
            } catch (DontKnowWhatThatMeansException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
