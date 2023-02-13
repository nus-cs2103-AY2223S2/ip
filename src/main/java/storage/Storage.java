package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that can do data saving and data loading.
 */
public class Storage {
    protected String directoryPath;
    protected String filePath;

    /**
     * Constructor.
     *
     * @param directoryPath The path of the directory for storage.
     * @param filePath The path of the file for storage.
     */
    public Storage(String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Saves the command message to the disk.
     *
     * @param commandMsg The command message to save to the disk.
     */
    public void saveToDisk(String commandMsg) {
        try {
            if (new File(filePath).exists() == false) {
                Files.createDirectories(Path.of(directoryPath));
            }
            File file = new File(filePath);

            assert new File(filePath).exists() : "The file for storage should exists.";
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(commandMsg);
            fw.close();
        } catch (IOException exception) {
            System.out.println("Error: unable to save tasks to the disk :(");
        }
    }

    /**
     * Loads the data from the disk.
     *
     * @param commandList The ArrayList we use to store the output data.
     */
    public void loadData(ArrayList<String> commandList) {
        try {
            File file = new File(filePath);
            if (file.exists() == false) {
                return;
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                commandList.add(command);
            }
            sc.close();
        } catch (FileNotFoundException exception) {
            System.out.println("No data storage file exists.");
        }
    }
}
