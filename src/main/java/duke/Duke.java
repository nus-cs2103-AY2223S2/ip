package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Duke Class to handle execution of Duke program.
 */
public class Duke {
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
            // Assert that task numbers are not negative
            assert tasks.getNumOfTasks() >= 0;
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

    protected void terminate() {
        storage.save(tasks.tasks);
        ui.printByeMsg();
        System.exit(0);
    }
    
    protected String getResponse(String input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            parser.parse(input);
            storage.save(tasks.tasks);
            return baos.toString();
        } catch (DukeException e) {
            return e.getMessage();
        }
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
