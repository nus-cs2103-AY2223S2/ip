package storage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import exceptions.FileLoadException;

/**
 * This class represents the storage for Duke to load and store previous task list
 * from database of local file
 */
public class Storage {

    private String home;
    private Path path;
    private File file;

    /**
     * Creates storage for Duke
     */
    public Storage() {
        this.home = System.getProperty("user.dir");
        this.path = Paths.get(this.home, "../../../DataDuke");
        this.file = Paths.get(this.path.toString(), "DATA_dUkE.txt").toFile();

        if (!Files.exists(this.path)) {
            try {
                Files.createDirectories(this.path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Load previous task list from local storage into the application
     *
     * @return previous task list data as a string
     * @throws FileLoadException
     */
    public String load() throws FileLoadException {
        String data = "";
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                data += sc.nextLine() + "\n";
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileLoadException(this.file.toString());
        }
        return data;
    }

    /**
     * Saves the updated listed into a local file
     *
     * @param taskList current task list to be saved for next use
     * @throws IOException
     */
    public void store(TaskList taskList) throws IOException {

        PrintStream stream;
        try {
            stream = new PrintStream(file.toString());
        } catch (FileNotFoundException e) {
            Files.createDirectories(path);
            stream = new PrintStream(file.toString());
        }
        for (int i = 0; i < taskList.size(); i++) {
            stream.print(taskList.get(i).stringifyTaskToSave() + "\n");
        }

        stream.close();
    }
}
