package Duke;
import Task.TaskList;
import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {

    private static String DIRPATH;
    private static String FILEPATH;

    private static TaskList taskList;
    public Storage (String dirpath, String filepath) throws Exception {
        DIRPATH = dirpath;
        FILEPATH = filepath;
        this.init();
    }

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
