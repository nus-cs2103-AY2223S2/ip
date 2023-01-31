import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

public class Duke {
//    public static String projName = " ____        _        \n"
//                                    + "|  _ \\ _   _| | _____ \n"
//                                    + "| | | | | | | |/ / _ \\\n"
//                                    + "| |_| | |_| |   <  __/\n"
//                                    + "|____/ \\__,_|_|\\_\\___|\n";
//    ArrayList<Task> tasks = new ArrayList<>();
    private final static String SAVED_PATH = "data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private final Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList taskList;
        ui.showWelcomeMessage();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingErrorMessage();
            taskList = new TaskList();
        }
        parser = new Parser(ui, storage, taskList);
    }

    public void run() throws DukeException {
        boolean isContinueRunning = true;

        while (isContinueRunning) {
            try {
                String input = ui.requestUserInput();
                isContinueRunning = parser.readInput(input);
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke(SAVED_PATH).run();
    }
}
