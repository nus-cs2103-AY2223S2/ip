package connor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

import connor.gui.Main;
import connor.parser.Parser;
import connor.storage.Storage;
import connor.task.Task;
import connor.task.TaskList;
import connor.ui.Ui;
import javafx.application.Application;

/**
 * Connor object that is the backbone of the program.
 */
public class Connor {


    /** Storage variable for this instance */
    private Storage storage;

    /** Collection of tasks for this instance */
    private TaskList tasks;

    /** UI to print messages for this instance */
    private final Ui ui;

    /** Parser to parse inputs for this instance */
    private final Parser parser;

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
                System.out.println("Existing data detected, loading data.");
                return new File(String.valueOf(dataPath));
            } else {
                System.out.println("No existing data detected, creating new save file.");
                Files.createDirectories(directoryPath);
                return new File("data/connor.Connor.txt");
            }
        } catch (IOException e) {
            System.out.println("ALERT! FAILED TO CREATE DIRECTORY!");
        }
        return new File("data/connor.Connor.txt");
    }

    /**
     * Gets the corresponding response from Connor based on the input user command.
     *
     * @param input the input command.
     * @return String representing Connor's response.
     */
    public String getResponse(String input) {
        String response = this.parser.parse(input, this.tasks, this.ui);
        this.storage.updateFile(tasks.getList());
        return response;
    }

    public String greet() {
        return ui.greet();
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
