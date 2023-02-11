package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Main.Flag;
import duke.command.Command;
import duke.command.Command.ReturnCode;
import duke.gui.MainWindow;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Serves as the main logic of the program.
 */
public class Duke extends Application {

    private static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";

    private static ArrayList<Flag> flags = new ArrayList<>();

    public final Ui ui;

    public final TaskList taskList;
    public final Storage storage;
    public final DateTimeParser dateTimeParser;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage(taskList);
        dateTimeParser = new DateTimeParser();
        ui = new Ui();

        assert Duke.flags != null : "Duke flags is null";

        // Retrieve saved data (if any)
        if (!Duke.flags.contains(Flag.NO_LOAD_SAVES)) {
            storage.loadDataFromFile();
        }
    }

    public static void setFlags(ArrayList<Flag> flags) {
        Duke.flags = flags;
    }

    /**
     * Displays the number of task in taskList to user.
     */
    public void displayTaskCount() {
        assert taskList != null : "Duke has no taskList instance";

        if (taskList.isEmpty()) {
            ui.println("You do not have any task!");
        } else {
            ui.println("Now you have " + taskList.size() + " task(s) in the list.");
        }
    }

    private void displayTasks() {
        assert taskList != null : "Duke has no taskList instance";
        assert ui != null : "Duke has no ui instance";

        if (taskList.size() == 0) {
            ui.println("Your list is empty.");
        } else {
            ui.println("You have the following task(s):");
            for (int i = 0; i < taskList.size(); i++) {
                ui.println("\t" + (i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Adds the specified task the taskList.
     *
     * @param task The new Task to be added to the taskList.
     */
    public void addNewTask(Task task) {
        assert taskList != null : "Duke has no taskList instance";
        assert storage != null : "Duke has no storage instance";
        assert ui != null : "Duke has no ui instance";

        taskList.add(task);
        ui.println("Got it. I've added this task:");
        ui.println("\t" + task);

        storage.saveDataToFile();
        displayTaskCount();
    }

    /**
     * Starts Duke program as a Command Line Interface.
     */
    void startAsCli() {
        // Program Intro
        ui.println("Hello! I'm Duke! :D");
        ui.println("What can I do for you today?");

        //Initialise variables used inside the program loop
        Scanner sc = new Scanner(System.in);
        ReturnCode code = null;
        String userInput;

        // Program Loop
        while (code != ReturnCode.EXIT) {
            System.out.print("\n > ");
            userInput = sc.nextLine();

            try {
                Command cmd = Command.parseCommand(userInput);
                if (cmd == null) {
                    ui.warn("Sorry, I don't understand your request :(");
                    ui.println("Did you spell something wrongly?");
                    continue;
                }
                code = cmd.execute(this);
            } catch (DukeException e) {
                ui.warn(e.getMessage());
            }
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource(MAIN_WINDOW_FXML));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void stop() throws Exception {
        super.stop();

        assert storage != null : "Duke has no storage instance";
        storage.saveDataToFile();
    }
}
