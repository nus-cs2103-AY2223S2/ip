package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Deals with loading and saving task record
 */
public class Storage {
    protected final String filePath;
    protected final String directory;
    protected final String fileName;

    /**
     * Constructor
     *
     * @param directory directory of the record file
     * @param fileName  the name of the record file
     */
    public Storage(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
        this.filePath = getRecordPath();
    }

    /**
     * Returns the path to the record file
     *
     * @return the path to the record
     */
    private String getRecordPath() {
        return directory + "/" + fileName;
    }

    /**
     * Saves the list of tasks to a txt file as a string
     *
     * @param commandListString the string to write to
     */
    public void saveToFile(String commandListString) {
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            // create the directory and the record file
            // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
            if (!(new File(filePath).exists())) {
                Files.createDirectories(Paths.get(directory));
            }
            assert new File(filePath).exists() : String.format("The file path %s does not exist", filePath);

            File file = new File(filePath);
            file.createNewFile();

            // write to the file
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(commandListString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving record.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the record file at start-up
     *
     * @param commandList the list to store commands into
     */
    public void loadRecordIfExists(ArrayList<String> commandList) {
        // check if file exists
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        // read the file
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                commandList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading up record. ");
            e.printStackTrace();
        }
    }
}
