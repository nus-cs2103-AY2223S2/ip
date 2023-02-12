package command;

import task.Task;
import task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Storage {
    // For loading from and storing into the file
    Path loadPath;
    Path storePath;

    /**
     * Constructor for a storage object
     */
    public Storage() {
        String home = System.getProperty("user.home");
        //loadPath = java.nio.file.Paths.get(home, filePath);;
        storePath = java.nio.file.Paths.get(System.getProperty("user.dir"), "dukeData.txt");
    }

    /**
     * Stores the provided list of tasks into the default storage path of the project.
     * @param list the list of tasks that will be stored into the filepath.
     */
    public void store(TaskList list) {
        StringBuilder outputConstruct = new StringBuilder();
        for (Task curr : list) {
            outputConstruct.append(curr.toStorageString()).append(System.lineSeparator());
        }
        String finalOut = outputConstruct.toString();
        try {
            if (!java.nio.file.Files.exists(storePath)) {
                java.nio.file.Files.createFile(storePath);
            }
            java.nio.file.Files.write(storePath, finalOut.getBytes());
        } catch (IOException e) {
            //Todo: Could have a better way of dealing with this
            System.out.println(e);
        }
    }

    /**
     * Attempts to load preexisting stored tasks to a TaskList firstly from a user provided file path, then from the
     * program's default storage path. If neither exist, an IOException is thrown.
     * @return a TaskList containing the data read from the file
     * @throws IOException when neither the user's path nor the default storage path have files
     */
    public List<String> load() throws IOException {
        try {
            //Todo: Handle the case where the user's provided file is contained in the incorrect format
            return Files.readAllLines(loadPath);
        } catch (IOException e) {
            return Files.readAllLines(storePath);
        }
    }
}
