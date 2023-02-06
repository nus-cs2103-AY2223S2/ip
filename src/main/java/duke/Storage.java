package duke;

import task.TaskList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class stores the information of the data files.
 * Checking, Loading and Creation of data files is done here.
 * @author Bryan Ong
 */
public class Storage {

    static final String DIRPATH = "./data";
    static final String FILEPATH = "./data/tasks.txt";

    private static TaskList taskList;


    /**
     * This method initialises the process of checking
     * loading and creation of the data file.
     * The method will try to create the directory and file
     * by default, exception will throw if it already exists.
     * Exception caught will then just load the file into File object
     * for future reference.
     *
     */
    public String init() {
        try {
            Files.createDirectory(Paths.get(DIRPATH));
            File file = new File(Files.createFile(Paths.get(FILEPATH)).toString());
            taskList = new TaskList(file, true);
            return Ui.printInit();
        } catch (IOException m) {
            try {
                File file = new File(Files.createFile(Paths.get(FILEPATH)).toString());
                taskList = new TaskList(file, true);
                return Ui.printInit();
            } catch (IOException n) {
                File file = new File(FILEPATH);
                taskList = new TaskList(file, false);
                return Ui.printWelcomeBack();
            }
        }
    }
}
