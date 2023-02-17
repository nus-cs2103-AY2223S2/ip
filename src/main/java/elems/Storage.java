package elems;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the object that stores information in a data file
 * @author clydelhui
 */
public class Storage {
    private final File dataFile;

    /**
     * Generates a <code>Storage</code> given the path to a data file.
     * If the file does not exist, a new file will be created.
     * @param filePathName The path to the data file
     * @param ui A <code>Ui</code> object to display the messages depending on the status
     *           of the data file after construction
     */
    public Storage(String filePathName, Ui ui) {
        this.dataFile = new File(filePathName);
        try {
            boolean createdFile = dataFile.createNewFile();
            if (createdFile) {
                ui.dukeDisplay("Data file successfully created!");
            } else {
                ui.dukeDisplay("Data file already exists!");
            }
        } catch (IOException e) {
            ui.errorDisplay(e);
        }
    }

    /**
     * Stores the <code>TaskList</code> into the <code>Storage</code> file
     * @param taskList The <code>TaskList</code> that contains the tasks to store
     * @throws IOException when there is a problem with reading or writing to the file
     */
    public void refreshStorage(TaskList taskList) throws IOException {
        FileWriter clearFile = new FileWriter(this.dataFile, false);
        clearFile.close();
        FileWriter writer = new FileWriter(this.dataFile);
        writer.write(taskList.getListStorageText());
        writer.close();

    }

    /**
     * Generates an <code>ArrayList</code> of <code>String</code> with the elements being the text storage format
     * of the tasks in the <code>TaskList</code>
     * @return An <code>ArrayList</code> of <code>String</code> with the elements being the text storage format
     *      * of the tasks in the <code>TaskList</code>
     * @throws FileNotFoundException when the file to load the text from is not found
     */
    public ArrayList<String> load() throws FileNotFoundException {
        Scanner scanner = null;
        ArrayList<String> taskText = new ArrayList<>();
        scanner = new Scanner(dataFile);
        while (scanner.hasNextLine()) {
            taskText.add(scanner.nextLine());
        }
        scanner.close();
        return taskText;
    }

}
