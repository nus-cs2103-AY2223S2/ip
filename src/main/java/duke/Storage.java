package duke;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import duke.exceptions.DukeCannotFindFileException;

/**
 * Represents the storage used to save the tasklist.
 * Handles the reading and writing of text files so that state can be saved.
 * @author lukkesreysandeur
 */
public class Storage {
    /** The filepath of the saved file. */
    private final String saveLocation = "data/savestate.txt";
    /** The folder that the saved file is stored in. */
    private final String saveFolder = "data";
    /** Boolean stating whether the folder has been created. */
    private boolean isFolderCreated = false;
    /** Boolean stating whether the file has been created. */
    private boolean isFileCreated = false;

    /**
     * Initialises the storage object.
     * Sets the booleans to true if the file and folder can be found.
     */
    public Storage() {
        File folder = new File(saveFolder);
        if (folder.exists()) {
            isFileCreated = true;
        }
        File savedFile = new File(saveLocation);
        if (savedFile.exists()) {
            isFolderCreated = true;
        }
    }

    /**
     * Saves the current state of the tasklist by writing to a text file.
     * Creates new folder and file if they cannot be found, then sets the booleans to true.
     * @param lst The current tasklist object.
     */
    public void saveState(TaskList lst) {
        if (!isFolderCreated) {
            File folder = new File(saveFolder);
            if (!folder.mkdir()) {
                System.out.println("Something went wrong, folder not created");
            } else {
                isFolderCreated = true;
            }
        }
        if (!isFileCreated) {
            File savedFile = new File(saveLocation);
            try {
                savedFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
            isFileCreated = true;
        }

        assert isFileCreated;
        String data = lst.listToText();
        try {
            FileWriter writer = new FileWriter(saveLocation);
            assert writer != null;
            writer.write(data);
            writer.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads the saved text file into the tasklist, throws error if the file cannot be found.
     * @param lst The tasklist to be saved into.
     * @throws DukeCannotFindFileException
     */
    public void loadState(TaskList lst) throws DukeCannotFindFileException {
        assert isFileCreated;
        if (!isFileCreated) {
            throw new DukeCannotFindFileException();
        }
        File savedFile = new File(saveLocation);
        try {
            Scanner scanner = new Scanner(savedFile);
            while (scanner.hasNextLine()) {
                String taskText = scanner.nextLine();
                if (taskText.isEmpty()) {
                    break;
                }
                lst.loadTask(taskText);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
