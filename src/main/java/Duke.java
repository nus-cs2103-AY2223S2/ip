import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.PatternSyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    /**
     * Minimum length of a string command is given by
     * The length of the command +2 (for whitespace and
     * at least 1 letter for the command)
     */
    static final String HOMEDIRECTORY = System.getProperty("user.dir");
    static final Path DUKELISTDIRECTORY = Paths.get(HOMEDIRECTORY, "SavedList.txt");
    static final HashMap<String, Integer> MINVALIDLENGTH = new HashMap<>(Map.of(
            "todo", 6,
            "deadline", 10,
            "event", 7,
            "mark", 6,
            "unmark", 8,
            "delete", 8
    ));
    /**
     * Correct formatting of commands given that the name of the command is correct
     */
    static final HashMap<String, String> CORRECTFORMAT = new HashMap<>(Map.of(
            "todo", "todo THE TASK",
            "deadline", "deadline THE TASK /by yyyy-mm-ddThh:mm:ss",
            "event", "event THE TASK /from TIME /to TIME",
            "mark", "mark NUMBER",
            "unmark", "unmark NUMBER",
            "delete", "delete NUMBER"
    ));

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke(DUKELISTDIRECTORY).run();
    }
    /*private static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(greet());

        // arr => needs to be updated w old list
        ArrayList<Task> arr = new ArrayList<>();
        try {
            arr = readSavedFile();
        } catch (IOException unknown) {
            print(unknown + "\nUnsure of error");
        }

        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                exit();
                break;
            }
            execute(cmd, arr);
//            echo(sc.nextLine());
        }
        sc.close();
    }*/
}
