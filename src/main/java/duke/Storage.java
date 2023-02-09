package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String path;
    File file;
    Parser parser;

    public Storage() {
        this.path = null;
        this.file = null;
        this.parser = null;
    }

    /**
     * Checks if a given path exists. If it does not exist, create the file.
     * Creates a Storage object with the given path.
     * @param path The path which the Storage object will store and read the saved tasks from.
     */
    public Storage(String path) {
        this.path = path;
        this.file = new File(path);
        this.parser = new Parser();
        
        if (! file.exists()) {
            if (! file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Unable to create new database file.");
            }
        }
        assert file.exists() : "Storage file does not exist!";
    }

    /**
     * Loads a previously saved database file and returns the TaskList corresponding to the saved database file.
     * @return A TaskList corresponding to the saved database file.
     */
    public TaskList loadData() {
        Scanner s;
        try {
            s = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read database.");
            return new TaskList();
        }
        TaskList taskList = new TaskList();
        while (s.hasNext()) {
            taskList.add(this.parser.parseTask(s.nextLine()));
        }
        return taskList;
    }

    /**
     * Saves a TaskList into a database in the previously specified path.
     * @param taskList The TaskList to be saved to the database.
     * @throws IOException Exception thrown if the database file does not exist.
     */
    public void saveData(TaskList taskList) {
        if (path == null) {
            return;
        }
        assert file.exists() : "File does not exist!";
        try {
            FileWriter fw = new FileWriter(this.file);

            for (Task task : taskList.getTasks()) {
                fw.write(task.formatTask() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    

}
