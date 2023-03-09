package duke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;



/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * A Constructor for Storage.
     *
     * @param filePath The filepath where data is saved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the filepath.
     *
     * @return filepath where data is saved.
     */
    public String getFilepath() {
        return filePath;
    }

    /**
     * Saves the data currently stored in Duke's List to a file located in the filepath.
     *
     * @param dataArray ArrayList which contains the data to be written into the save file.
     */
    public void saveTasksToFile(ArrayList<Task> dataArray) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task data : dataArray) {
                String text = data.saveFormat();
                fw.write(text + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads the data found in file into Dukes list of tasks.
     *
     * @return Arraylist which contains the data from the saved file.
     * @throws FileNotFoundException if file is not found in the filepath.
     */
    public ArrayList<String> loadTaskListFromFile() throws FileNotFoundException {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(filePath);
        Scanner loader = new Scanner(file);
        while (loader.hasNextLine()) {
            data.add(loader.nextLine());
        }
        return data;
    }
}



