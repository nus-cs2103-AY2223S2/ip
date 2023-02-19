package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Writes and loads task lists to and from storage.
 */
public class Storage {
    private final String dataFilePath;

    /**
     * Constructs a storage, creating the parent directories for the data file if they do not exist.
     *
     * @param dataFilePath Path to store the task list text file.
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        // Create the directories if they do not exist. Adapted from
        // https://stackoverflow.com/questions/4040624/how-to-create-a-file-including-folders-for-a-given-path.
        try {
            File targetFile = new File(dataFilePath);
            File parent = targetFile.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IllegalStateException("Couldn't create dir: " + parent);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the task list to memory.
     *
     * @param tasks Task list containing tasks to be saved.
     * @throws IOException if the path exists but is a directory instead of a file, or cannot be opened for some reason.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        if (!tasks.isEmpty()) {
            FileWriter writer = new FileWriter(dataFilePath);
            writer.write(tasks.toEncodedString());
            writer.close();
        }
    }

    /**
     * Parses and separates the data file into an ArrayList of each line.
     *
     * @return ArrayList of parsed commands.
     */
    public ArrayList<String[]> load() {
        File targetFile = new File(dataFilePath);
        ArrayList<String[]> fileCommands = new ArrayList<>();

        try {
            if (!targetFile.createNewFile()) {
                Scanner scanner = new Scanner(targetFile);
                while (scanner.hasNext()) {
                    fileCommands.add(Parser.parseFileCommand(scanner.nextLine()));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return fileCommands;
    }
}
