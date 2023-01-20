package duke;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exception.DukeException;

public class Storage {
    private String dirPath;
    private final String fileName = "duke.txt";

    public Storage(String dirPath) {
        this.dirPath = dirPath;
    }

    public String load() throws DukeException {
        try {
            Path dirPath = Paths.get(this.dirPath);
            System.out.println(dirPath);
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            return Files.readString(Paths.get(this.dirPath + this.fileName));
        } catch (IOException e) {
            throw new DukeException("Reading from storage failed");
        }
    }

    public void save(String taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.dirPath + this.fileName);
            fw.write(taskList);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Writing to storage failed");
        }
    }

}
