import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The Duke class represents a CLI chatbot that performs operations based on CLI user input.
 * <p>
 * Currently, Duke accepts the commands: {@code echo, list, mark, unmark, todo, deadline, event, bye}
 */
public class Duke extends Application {
    private TaskList tasks;
    private Ui ui;
    private Storage store;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = Ui.getInstance();
        try {
            store = new Storage("src/main/resources/duke.txt");
            tasks = store.loadFromFile();
            tasks = tasks == null ? new TaskList() : tasks;
        } catch (Exception e) {
            ui.printException(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main function of Duke.
     *
     * @param args There are no options available for now.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Runs Duke as a CLI chatbot.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.getCommand();
                Command c = Parser.parseCommand(command);
                c.execute(tasks, ui, store);
                isExit = c.canExit();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
    }

}
