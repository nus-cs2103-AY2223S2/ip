package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import duke.exceptions.DukeyException;


/**
 * Deals with the saving and loading of Tasks onto a hard drive.
 */
public class Storage {
    private File file;
    private String filePath;

    /**
     * Returns a new storage containing a File where data is to be stored.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        this.filePath = filePath;
    }

    /**
     * Appends text to the save File.
     * @param textToAdd the text to be added
     * @throws IOException on problems with the input text
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.file, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    /**
     * Clears the save File of any data.
     */
    public void clearFile() {
        this.file.delete();
        this.file = new File(this.filePath);
    }

    /**
     * Saves all the Tasks on a TaskList to the hard drive by writing the log strings of each Task
     * onto the save File.
     * @param taskList the TaskList to be saved
     * @throws DukeyException on invalid filepath provided
     */
    public void save(TaskList taskList) throws DukeyException {
        this.clearFile();
        Iterator<Task> it = taskList.getIterator();
        it.forEachRemaining(x -> {
            String logString = x.getLogString();
            try {
                this.writeToFile(logString);
            } catch (IOException e) {
                e.getMessage();
            }
        });

    }

    /**
     * Loads all the Tasks on the hard drive to a TaskList by creating new Tasks based on the details from
     * their log string.
     * @param taskList the TaskList on which the Tasks are to be laoded
     * @throws FileNotFoundException on missing file
     */
    public void load(TaskList taskList) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String taskLogString = fileScanner.nextLine();
            String[] logStringArray = taskLogString.split(",");
            if (logStringArray[0].equals("T")) {
                taskList.addTaskFromSave(ToDo.createToDoFromLog(logStringArray));
            } else if (logStringArray[0].equals("D")) {
                taskList.addTaskFromSave(Deadline.createDeadlineFromLog(logStringArray));
            } else if (logStringArray[0].equals("E")) {
                taskList.addTaskFromSave(Event.createEventFromLog(logStringArray));
            }
        }
    }
}
