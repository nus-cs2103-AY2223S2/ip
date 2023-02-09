import java.util.ArrayList;
import java.util.Scanner;
import command.Command;
import dukeexeption.DukeException;
import javafx.application.Application;
import javafx.stage.Stage;
import parser.Request;
import storage.LocalStorage;
import storage.TaskList;
import task.Task;
import ui.Gui;
import ui.TextUi;

/**
 * The main duke program.
 */
public class Duke extends Application {
    private TaskList tasks;
    private TextUi textUi;
    private LocalStorage localTaskList;

    public Duke() {
        this.textUi = new TextUi();
        this.tasks = new TaskList();
    }

    public Duke(String filepath) {
        this.textUi = new TextUi();
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            this.localTaskList = new LocalStorage(filepath);
            taskList = this.localTaskList.createTaskList();
        } catch (DukeException error) {
            textUi.printFormattedError(error);
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
        this.textUi.printStartUpMessage();

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
                this.textUi.printFormattedResponse(reply);
            } catch (DukeException error) {
                this.textUi.printFormattedError(error);
            }
        }

        this.textUi.printExitingMessage();
    }

    @Override
    public void start(Stage stage) {
        new Gui((String input) -> this.parseAndRespond(input)).start(stage);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String parseAndRespond(String input) {
        try {
            Command command = new Request(input).parse();
            String reply = command.run(this.tasks);
            return "Duke shouts:\n " + reply;
        } catch (DukeException error) {
            return "Duke shouts:\n " + error;
        }
    }
}
