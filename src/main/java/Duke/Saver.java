package Duke;
import Duke.Exception.ProgramException;
import Duke.Tasks.TaskList;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to save and load tasks to and from Duke.
 * @author Bryan Juniano
 */

public class Saver {
    protected final static String PATH = "./data";
    protected final static String FILE_NAME = "/tasks.txt";

    /**
     * Creates a file with the specified path, if it does not exist
     */
    public static void createFile(){
        File directory = new File(PATH);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(PATH+FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current taskList on duke locally on a text file.
     * @param taskList the taskList to be saved into the data file.
     */
    public void save(TaskList taskList){
        try {
            createFile();
            FileWriter writer = new FileWriter(PATH + FILE_NAME);
            System.out.println("Texas style.");
            writer.write(taskList.toSave());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the content from the stored text file into a taskList Object.
     * @param taskList the taskList to store the contents of the file into.
     */
    public void load(TaskList taskList) throws ProgramException {
        try {
            createFile();
            Path path = Paths.get(PATH+FILE_NAME);
            taskList.fromSave(Files.readString(path));
            System.out.println("tasks loaded successfully.");
        }
        catch (IOException e) {
            throw new ProgramException("Save file data corrupted.");
        }
    }

}
