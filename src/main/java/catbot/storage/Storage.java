package catbot.storage;

import catbot.CatBotException;
import catbot.parser.Parser;
import catbot.tasklist.Task;
import catbot.tasklist.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File saveFile;

    public Storage(String saveFilePath) throws CatBotException {
        Path path = Paths.get(saveFilePath);
        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new CatBotException("Error while loading storage.");
            }
        }
        saveFile = path.toFile();
    }

    public ArrayList<Task> load() throws CatBotException {
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
        try (FileWriter writer = new FileWriter(saveFile, false)) {
            for (Task task: tasks) {
                writer.write(task.toCommand() + "\n");
            }
        } catch (IOException e) {
            throw new CatBotException("Error while accessing save file.");
        }
    }
}
