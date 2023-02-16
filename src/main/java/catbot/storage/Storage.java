package catbot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import catbot.CatBotException;
import catbot.parser.Parser;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;

/**
 * Handles all operations relating to saving and loading from disk.
 */
public class Storage {
    private final File saveFile;

    /**
     * Initialises a new Storage instance.
     * @param saveFilePath is the path for the save file to use.
     * @throws CatBotException if there is an error while trying to create possibly required directories.
     */
    public Storage(String saveFilePath) throws CatBotException {
        Path path = Paths.get(saveFilePath);
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path.getParent());
                if (!path.toFile().createNewFile()) {
                    throw new CatBotException("Error while creating new save file.");
                }
            } catch (IOException e) {
                throw new CatBotException("Error while loading storage.");
            }
        }
        saveFile = path.toFile();
    }

    /**
     * Loads from a save file.
     * @return an {@code ArrayList} containing all the tasks loaded in from the file.
     * @throws CatBotException if there was an error while parsing the save file.
     */
    public ArrayList<Task> load() throws CatBotException {
        assert saveFile.exists() : saveFile.getPath();
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(saveFile);

            while (scanner.hasNextLine()) {
                Parser.parse(scanner.nextLine()).loadCommand(tasks);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    /**
     * Appends a given message to the save file
     * @param message is the string to append to the save file
     */
    public void writeToSaveFile(String message) throws CatBotException {
        assert saveFile.exists() : saveFile.getPath();
        try (FileWriter writer = new FileWriter(saveFile, true)) {
            writer.write(message);
        } catch (IOException e) {
            throw new CatBotException("Error while accessing save file.");
        }
    }

    /**
     * Writes the contents of the task list to the save file.
     * @param tasks is the TaskList to write to disk
     */
    public void saveToFile(TaskList tasks) throws CatBotException {
        assert saveFile.exists() : saveFile.getPath();
        try (FileWriter writer = new FileWriter(saveFile, false)) {
            for (Task task: tasks) {
                writer.write(task.toCommand() + "\n");
            }
        } catch (IOException e) {
            throw new CatBotException("Error while accessing save file.");
        }
    }
}
