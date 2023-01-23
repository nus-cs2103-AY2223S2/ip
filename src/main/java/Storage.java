import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    protected final String filePath;
    protected final String directory;
    protected final String fileName;

    public Storage(String directory, String fileName) {
        this.directory = directory;
        this.fileName = fileName;
        this.filePath = getRecordPath();
    }

    /**
     * Obtain the path to the record path
     * @return the path to the record
     */
    private String getRecordPath() {
        return directory + "/" + fileName;
    }

    /**
     * Saves the list of tasks to a file
     * @param commandListString: the string to write to
     */
    public void saveToFile(String commandListString) {
        // https://www.w3schools.com/java/java_files_create.asp
        try {
            // create the directory and the record file
            // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
            if (!(new File(filePath).exists())) {
                Files.createDirectories(Paths.get(directory));
            }
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
     * Load up the record file at start-up
     * @param path: path to the record file
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
