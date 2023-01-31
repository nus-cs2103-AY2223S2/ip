package duke;
import task.TaskList;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class stores the information of the data files.
 * Checking, Loading and Creation of data files is done here.
 * @author Bryan Ong
 */
public class Storage {

    private static String DIRPATH;
    private static String FILEPATH;

    private static TaskList taskList;
    public Storage (String dirpath, String filepath) throws Exception {
        DIRPATH = dirpath;
        FILEPATH = filepath;
        this.init();
    }

    /**
     * This method initialises the process of checking
     * loading and creation of the data file.
     * The method will try to create the directory and file
     * by default, exception will throw if it already exists.
     * Exception caught will then just load the file into File object
     * for future reference.
     * @throws Exception
     */
    private void init() throws Exception {
        try {
            Files.createDirectory(Paths.get(DIRPATH));
            File file = new File(Files.createFile(Paths.get(FILEPATH)).toString());
            taskList = new TaskList(file, true);
            Ui.printInit();
        } catch (FileAlreadyExistsException m) {
            try {
                File file = new File(Files.createFile(Paths.get(FILEPATH)).toString());
                taskList = new TaskList(file, true);
            } catch (FileAlreadyExistsException n) {
                File file = new File(FILEPATH);
                taskList = new TaskList(file, false);
                Ui.printWelcomeBack();
            }
        }
    }
}
