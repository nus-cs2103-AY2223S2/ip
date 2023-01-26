package jarvis;

import java.util.Scanner;

import jarvis.command.Command;
import jarvis.exception.InvalidActionException;
import jarvis.parser.Parser;
import jarvis.storage.Storage;
import jarvis.task.TaskList;
import jarvis.ui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Jarvis class to run the program.
 */
public class Jarvis extends Application {
    private static final String BOT_NAME = Jarvis.class.getSimpleName();

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructor for Jarvis.
     */
    public Jarvis() {
        this.storage = new Storage();
        this.ui = new Ui(BOT_NAME);
        this.taskList = new TaskList(storage.readTasks());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Hello world");
        Scene scene = new Scene(label);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Runs Jarvis.
     */
    public void run() {
        this.ui.printLogo();
        this.ui.printStandard(Ui.Response.INTRO);

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while (!isExit && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                this.ui.printUserPrompt();
                continue;
            }

            Command command;
            try {
                command = Parser.parse(line);
            } catch (InvalidActionException e) {
                this.ui.printStandard(Ui.Response.CONFUSED);
                continue;
            }
            command.execute(this.ui, this.taskList, this.storage);
            isExit = command.isExit();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Jarvis().run();
    }
}
