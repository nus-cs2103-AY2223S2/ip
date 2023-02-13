import AddTasks.Task;
import Exceptions.IncompleteInputException;
import Exceptions.InvalidInputException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import munch.Storage;
import munch.TaskList;
import munch.Ui;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


/**
 * Main class of Munch.
 */
public class Munch {

    private static Ui ui;
    private Storage storage;
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for Munch object
     * @param filePath Path of the object file where the task objects are stored in.
     */
    public Munch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = Storage.load(tasks, filePath);
    }

    /**
     * Runs the program flow.
     * Prints a welcome message when the user starts the chat.
     * Continues the program while taking in commands from the user.
     * Exits the program when user types in "bye".
     * Prints a goodbye message and close thr program.
     * @param args String array input
     */
    public static void main(String[] args) {
//        ui.welcomeMessage();
//        String filePath = "src/main/java/data/SavedTaskList.txt";
//        new Munch(filePath);
//        Boolean stillRunning = true;
//        while (stillRunning) {
//            try {
//                String word = ui.readCommand();
//                String[] words = word.split(" ");
//                stillRunning = TaskList.run(tasks, word, words);
//                Storage.save(tasks, filePath);
//            } catch (IncompleteInputException | InvalidInputException e) {
//                System.out.println(e.getMessage());
//            }
//        }
    }
}
