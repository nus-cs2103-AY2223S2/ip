package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * This class loads the path of the stored commands and updates it when the bot is closed.
 */

public class StorageList {
    private ArrayList<Task> list;
    private String path;

    /**
     * Constructor for the storagelist class.
     *
     * @param path url for the storage list text file.
     */
    public StorageList(String path) {
        this.list = new ArrayList<>();
        this.path = path;
    }


    /**
     * This method loads the data of the previous commands from a textfile.
     *
     * @return ArrayList - Returns the arraylist that is loaded from the stored textfile.
     * @throws DukeException - File not found error will cause this exception to be thrown.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(this.path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split("\\] ");
                String[] lineType = lineArr[0].split("\\]");
                this.list.add(new Task(lineArr[1], lineType[0].substring(1), lineType[1].substring(1)));
            }
            scanner.close();
            return this.list;
        } catch (FileNotFoundException e) {
            throw new DukeException("The file is not found in the directory");
        }

    }


    /**
     * This method will allow the text file to be updated according to the current arraylist and ensure that textfile
     * will contain the most updated data.
     */
    public void updateStorage() {
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            java.nio.file.Path path = java.nio.file.Paths.get("data", "duke.txt");
            FileWriter writer = new FileWriter(String.valueOf(path));
            for (Task str : list) {
                writer.write(str.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
