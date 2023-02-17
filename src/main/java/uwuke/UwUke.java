package uwuke;

import java.io.IOException;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uwuke.controller.Advisor;
import uwuke.controller.Command;
import uwuke.model.Deadline;
import uwuke.model.TaskList;
import uwuke.view.DialogBox;
import uwuke.view.Printer;

public class UwUke extends Application {

    private static TaskList tasks;

    private Scene scene;
    private AnchorPane anchorPane;
    private static Stage stage;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/logo.png"));

    @Override
    public void start(Stage stage) {
        loadMainWindow();
        initialiseHelperClasses();
        scene.getStylesheets().add(getClass().getResource("/view/Application.css").toExternalForm());
        UwUke.stage = stage;
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    private void loadMainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UwUke.class.getResource("/view/MainWindow.fxml"));
            anchorPane = fxmlLoader.load();
            scene = new Scene(anchorPane);
        } catch (IOException e) {
            System.out.println("Failed to load MainWindow.fxml");
        }
    }

    private void initialiseHelperClasses() {
        DialogBox.setDukeImage(duke);
        DialogBox.setUserImage(user);
        inititialiseModels();
    }

    public static void displayDukeResponse(String input) {
        run(input);
    }

    private static void inititialiseModels() {
        loadTaskFromStorage();
        remindDeadlines();
    }

    private static void loadTaskFromStorage() {
        try {
            tasks = Storage.readSavedTasks();
        } catch (Exception e) {
            Printer.printError("Could not load save file or it doesn't exist, creating new task list");
            tasks = new TaskList();
        }
    }

    private static void remindDeadlines() {
        // Go through the tasks and if the task is a deadline and is due soon, will remind the user.
        // Typecast here is safe due to short circuit behaviour of &&
        tasks.getList().forEach(t -> {
            if (t instanceof Deadline && ((Deadline) t).isDueSoon()) {
                Printer.printWithDecorations(t.toString() + " is due in less than 1 day!");
            }
        });
    }

    /**
     * Performs the command based on the input
     * Will identify command based on it's type, raw input string can be passed in directly.
     * @param input command string
     */
    private static void performCommand(String input) throws DukeException {
        switch (Command.matchCommand(input)) {
        case LIST:
            Printer.printTasks(tasks.getList());
            break;
        case DEADLINE:
            tasks.addDeadline(input);
            break;
        case EVENT:
            tasks.addEvent(input);
            break;
        case TODO:
            tasks.addTodo(input);
            break;
        case MARK:
            tasks.markTask(input);
            break;
        case UNMARK:
            tasks.unmarkTask(input);
            break;
        case DELETE:
            tasks.deleteTask(input);
            break;
        case FIND:
            tasks.findTask(input);
            break;
        case BYE:
            saveTask();
            stage.close();
            break;
        default:
            Printer.printWithDecorations(Advisor.adviseUser(input));
        }
    }

    /**
     * Handles error if input contains a comma character, which will interfere with save file loading.
     * Can potentially cause fatal errors when trying to read files if commas were allowed.
     */
    private static void handleIllegalCharacter(String input) throws DukeException {
        if (input.contains(",")) { 
            throw new DukeException("Please do not use reserved character \',\'.");
        }
    }

    /**
     * Handles saving tasks and any potential errors
     */
    private static void saveTask() {
        try {
            Storage.saveTasks(tasks.getList());
        } catch (Exception e) {
            Printer.printWithDecorations("Error occured when trying to save tasks");
        }
    }

    private static void run(String input) {
        try {
            handleIllegalCharacter(input);
            performCommand(input);
        } catch (DukeException e) {
            Printer.printError(e.getMessage());
        } catch (NoSuchElementException e) {
            Printer.printError("Error occurred when trying to read next line, try again.");
        } catch (Exception e) {
            Printer.printError("Unknown error ocurred");
        }
    }
}