package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.MainWindow;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Graphical user interface app for Duke.
 */
public class Duke extends Application {

    /** Storage to load and save tasks on disk */
    private final Storage storage;

    /** Task list to store tasks in memory */
    private final TaskList tasks;

    /** Graphical user interface stage */
    private Stage stage;

    /** Graphical user interface main window controller */
    private MainWindow controller;

    /**
     * Constructs a duke.
     */
    public Duke() {
        storage = new Storage("./data/tasks.txt");
        tasks = new TaskList();
    }

    /**
     * Starts the app.
     *
     * @param stage the primary stage for this application,
     *      onto which the application scene can be set.
     *      Applications may create other stages, if needed,
     *      but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(event -> exit());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            stage.setScene(new Scene(fxmlLoader.load()));
            controller = fxmlLoader.getController();
            controller.setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller.showGreeting();
        try {
            tasks.load(storage.getScanner());
        } catch (FileNotFoundException e) {
            controller.showStorageLoadFailure();
        } catch (IOException e) {
            controller.showStorageCorrupted(e.getMessage());
        }
        controller.showPrompt();
    }

    /**
     * Handles user input.
     *
     * @param input User input.
     */
    public void handleUserInput(String input) {
        try {
            execute(Parser.parse(input));
        } catch (IllegalArgumentException | IllegalStateException e) {
            controller.showBadCommandMessage(e.getMessage());
        }
    }

    /**
     * Executes a command.
     *
     * @param command Command to execute.
     */
    private void execute(Command command) {
        switch (command.getName()) {
        case NO_OP:
            break;
        case BYE:
            exit();
            break;
        case TODO:
            // FallThrough
        case DEADLINE:
            // FallThrough
        case EVENT:
            controller.showAddTaskSuccessMessage(tasks.execute(command));
            break;
        case LIST:
            controller.showAllTasks(tasks);
            break;
        case FIND:
            controller.showTasksWithKeyword(tasks.findAll(
                    command.getArgumentValue(Command.Argument.FIND)));
            break;
        case MARK:
            Task task = tasks.execute(command);
            controller.showMarkTaskSuccessMessage(task);
            break;
        case DELETE:
            controller.showDeleteTaskSuccessMessage(tasks.execute(command));
            break;
        default:
            assert false : "Unhandled command: " + command;
        }
    }

    /**
     * Cleans up the duke to exit.
     * Save tasks to the disk.
     */
    private void exit() {
        try {
            tasks.save(storage.getFileWriter());
        } catch (IOException e) {
            controller.showStorageSaveFailure();
        }
        controller.showFarewellMessage();
        PauseTransition delay = new PauseTransition(Duration.seconds(5));
        delay.setOnFinished((event) -> stage.close());
        delay.play();
    }
}
