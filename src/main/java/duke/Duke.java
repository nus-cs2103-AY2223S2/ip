package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Duke class which initiates the program and execute it.
 */
public class Duke extends Application {
    protected static final String DIV_OPEN = "____________________________________________________________\n";
    protected static final String DIV_CLOSE = "____________________________________________________________\n";
    protected static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n"
            + "\n";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Empty Constructor for Duke class.
     */
    public Duke() {

    }

    /**
     * Constructor for Duke class.
     * Returns Duke object which runs the program.
     *
     * @param filePath File path which Duke will use for file creation and management.
     */
    public Duke(String filePath) {
        System.out.println("Loading Duke...");
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        this.parser = new Parser(this.tasks);
    }

    private void run() {
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        while (isRunning) {
            String input = ui.takeInput(sc);
            if (input.equals("bye")) {
                isRunning = false;
            } else {
                try {
                    parser.parse(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                storage.save(tasks.tasks);
            }
        }
        sc.close();
    }

    private void terminate() {
        storage.save(tasks.tasks);
        ui.printByeMsg();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
    }

    /**
     * Method that executes on start up.
     */
    public static void main(String[] args) {

        String home = System.getProperty("user.home");
        Duke duke = new Duke(home);
        System.out.println("Loading complete.\n");

        duke.ui.printHelloMsg();

        duke.run();

        duke.terminate();

    }
}
