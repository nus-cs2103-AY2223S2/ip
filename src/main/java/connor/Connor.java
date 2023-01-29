package connor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import connor.parser.Parser;
import connor.storage.Storage;
import connor.task.Task;
import connor.task.TaskList;
import connor.ui.Ui;

/**
 * Connor object that is the backbone of the program.
 */
public class Connor {

    /**
     * Valid commands that are allowed to be inputted by the user.
     */
    public enum Commands {
        HI,
        BYE,
        MARK,
        UNMARK,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        DELETEALL,
        FIND
    }

    /** Storage variable for this instance */
    private Storage storage;

    /** Collection of tasks for this instance */
    private TaskList tasks;

    /** UI to print messages for this instance */
    private Ui ui;

    /** Parser to parse inputs for this instance */
    private Parser parser;

    /**
     * Constructor to instantiate a new Connor object.
     */
    public Connor() {
        this.ui = new Ui();
        this.ui.greet();
        this.storage = new Storage(getFile());
        this.parser = new Parser();
        try {
            LinkedList<Task> memory = storage.initialize();
            this.tasks = new TaskList(memory);
        } catch (IOException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns a File object that is created in a relative directory path of the user.
     * Creates a new Directory if the directory does not exist.
     * Creates a new File if the data file does not exist.
     * Utilises an existing file if it exists.
     *
     * @return File object that stores tasks across instances.
     */
    private File getFile() {
        String homeDir = System.getProperty("user.dir");
        Path directoryPath = Paths.get(homeDir, "data");
        Path dataPath = Paths.get(homeDir, "data", "connor.Connor.txt");
        try {
            if (Files.exists(dataPath)) {
                Ui.printMessage("Existing data detected, loading data.");
                return new File(String.valueOf(dataPath));
            } else {
                Ui.printMessage("No existing data detected, creating new save file.");
                Files.createDirectories(directoryPath);
                return new File("data/connor.Connor.txt");
            }
        } catch (IOException e) {
            Ui.printMessage("ALERT! FAILED TO CREATE DIRECTORY!");
        }
        return new File("data/connor.Connor.txt");
    }

    /**
     * Scans in user input and parses it.
     * Stores the output in the file.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        while (!isOver && sc.hasNextLine() ) {
            String input = sc.nextLine().trim();
            isOver = this.parser.parse(input, this.tasks, this.ui);
            this.storage.updateFile(tasks.getList());
        }
        sc.close();
    }

    public static void main(String[] args) {
        new Connor().run();
    }
}
