package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that manages the CRUD of the tasks
 */
public class Storage {

    private File folder;
    private File file;

    /**
     * Constructor for a new Storage class
     * @param folderName location of the folder
     * @param fileName name of the file doing read and write
     */
    public Storage(String folderName, String fileName) {
        folder = new File(folderName);
        if (!folder.exists()) {
            folder.mkdir();
        }
        file = new File(folder, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads data from the database and inserts it into an ArarayList to be returned
     * @return ArrayList containing string of tasks
     * @throws IOException
     */
    public ArrayList<String> load() throws IOException {
        Scanner s = new Scanner(file);
        ArrayList<String> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Saves data in the ArrayList to the databasse
     * @param list
     * @throws IOException
     */
    public void save(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (Task t : list.getList()) {
            fw.write(t.savedAs() + System.lineSeparator());
        }
        fw.close();
    }

}
