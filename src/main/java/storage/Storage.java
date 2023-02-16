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

public class Storage {

    private String home;
    private Path path;
    private File file;

    public Storage() {
        this.home = System.getProperty("user.dir");
        this.path = Paths.get(this.home, "DataDuke");
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
     * to load previous task list from local storage into the application
     * 
     * @return previous task list in string
     */
    public String load() throws FileLoadException {
        String data = "";
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                data += sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new FileLoadException(this.file.toString());
        }
        return data;
    }

    /**
     * saves the most updates listed into a txt file
     * 
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
            stream.println(taskList.get(i).stringifyTaskToSave());
        }

        stream.close();
    }
}
