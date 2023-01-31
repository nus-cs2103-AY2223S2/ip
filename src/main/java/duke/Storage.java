package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;



public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void changeFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void loadTasklistFromFile() throws DukeException {

    }

    public void saveTasklistToFile(TaskList tasks) {
        Path file = Paths.get(this.filePath);
        try {
            Files.deleteIfExists(file);
            Files.createFile(file);
        } catch (IOException e) {
            // printErrorMessage(ErrorEnum.FILE_SAVE_ERROR);
        }

        for (int i = 0; i < tasks.getSize(); i++) {
            try {
                Files.writeString(file, tasks.get(i).getFileRepresentation() + System.lineSeparator(),
                        StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error when writing task " + tasks.get(i) + " to file");
            }
        }
    }
}
