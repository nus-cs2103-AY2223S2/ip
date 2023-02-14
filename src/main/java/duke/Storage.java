package duke;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.parsing.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Functions related to saving/loading data to/from files.
 */
public class Storage {

    private static String strDir = "./data";
    private static String fileName = "./data/duke.txt";

    /**
     * Saves a task list to a file.
     * 
     * @param tasks Task list to save.
     * @throws IOException Specified file not found.
     */
    public static void saveToFile(TaskList tasks) throws IOException {

        // create directory
        Path path = Paths.get(strDir);
        Files.createDirectories(path);

        // output string to file
        PrintWriter out = new PrintWriter(fileName);
        out.println(tasks.toString());
        out.close();

    }

    /**
     * Loads a task list from a file.
     * 
     * @param tasks Task list to load into.
     * @throws IOException Specified file not found.
     */
    public static void loadFromFile(TaskList tasks) throws IOException {
        try {
            Path fileNamePath = Path.of(fileName);
            String strData = Files.readString(fileNamePath);

            String[] strTasks = strData.split("\n");
            for (String strTask : strTasks) {
                if (strTask.length() == 1) {
                    return; // for handling empty file, it still contains "\n"
                }
                Task loadedTask = Parser.parseLoadedTask(strTask);
                tasks.add(loadedTask);
            }
        } catch (NoSuchFileException e) {
            // do nothing if no file exists (nothing to execute)
        }
    }

}
