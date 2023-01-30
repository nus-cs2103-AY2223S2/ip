import java.util.ArrayList;
import java.util.Scanner;
import command.Command;
import dukeexeption.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import parser.Request;
import storage.LocalStorage;
import storage.TaskList;
import task.Task;
import ui.Ui;

/**
 * The main duke program.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Ui ui;
    private LocalStorage localTaskList;

    public Duke() {
        this.ui = new Ui();
        this.tasks = new TaskList();
    }

    public Duke(String filepath) {
        this.ui = new Ui();
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            this.localTaskList = new LocalStorage(filepath);
            taskList = this.localTaskList.createTaskList();
        } catch (DukeException error) {
            ui.printFormattedError(error);
        }
        this.tasks = new TaskList(taskList);
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt").run();
    }

    /**
     * Begins the execution of the Duke program.
     */
    public void run() {
        this.ui.printStartUpMessage();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String request = scanner.nextLine();
            if ("BYE".equalsIgnoreCase(request)) {
                if (this.localTaskList != null) {
                    this.localTaskList.writeFromProgramTaskList(this.tasks);
                }
                break;
            }
            try {
                Command command = new Request(request).parse();
                String reply = command.run(this.tasks);
                this.ui.printFormattedResponse(reply);
            } catch (DukeException error) {
                this.ui.printFormattedError(error);
            }
        }

        this.ui.printExitingMessage();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
