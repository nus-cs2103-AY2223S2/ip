package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import exception.TaskParseException;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;

/**
 * A storage handler for reading and writing to a filesystem.
 */
public class Storage {
    /**
     * Thrown when a file could not be parsed as a valid Miki save file.
     */
    public static class MikiLoadException extends Exception {
        /**
         * Constructs a <code>MikiLoadException</code> with the specified detail message.
         *
         * @param message the detail message.
         */
        protected MikiLoadException(String message) {
            super(message);
        }
    }

    private final String dataPath;

    /**
     * Creates a new storage handler with a specified base directory for file paths.
     *
     * @param dataPath base directory of file paths.
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * Creates the base directory (and any required ancestors) if it does not exist yet.
     */
    private void createSaveDir() {
        File dir = new File(dataPath);
        if (!dir.mkdirs()) {
            // Do nothing - Could not create save directory, ignore here and allow other methods to alert user
        }
    }

    private String[] makeSaveData(TaskList tasks) {
        String saveData = "";
        saveData += tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            String[] repres = tasks.get(i).save();
            saveData += "\n" + repres.length;
            for (int j = 0; j < repres.length; j++) {
                saveData += "\n" + repres[j];
            }
        }
        return saveData.split("\n");
    }

    /**
     * Searches the base directory for loadable files and
     * returns their filenames as a <code>String</code> array.
     *
     * @return the filenames of files in the base directory.
     * @throws MikiLoadException if the base directory does not exist and
     *                           cannot be created.
     */
    public String[] listSaves() throws MikiLoadException {
        createSaveDir();
        File dir = new File(dataPath);
        if (dir == null) {
            throw new MikiLoadException("(default save location is missing?!)");
        }
        File[] saves = dir.listFiles();
        String[] filenames = new String[saves.length];
        for (int i = 0; i < saves.length; i++) {
            filenames[i] = saves[i].getName();
        }
        return filenames;
    }

    /**
     * Saves a <code>TaskList</code> to a file, in a loadable format.
     *
     * @param pathString path of the file to save to.
     * @param tasks      tasklist to save.
     * @throws IOException if the file could not be written to.
     */
    public void save(String pathString, TaskList tasks) throws IOException {
        createSaveDir();
        Path path = FileSystems.getDefault().getPath(dataPath).resolve(pathString);
        BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        try {
            String[] saveData = makeSaveData(tasks);
            for (int i = 0; i < saveData.length; i++) {
                bw.write(saveData[i]);
                bw.newLine();
            }
        } finally {
            bw.close();
        }
    }

    /**
     * Loads a file as a <code>TaskList</code>.
     *
     * @param pathString path of the file to load from.
     * @param tasks      tasklist to load to.
     * @throws IOException       if the file could not be read from.
     * @throws MikiLoadException if the file did not represent a correctly-encoded TaskList.
     */
    public void load(String pathString, TaskList tasks) throws IOException, MikiLoadException {
        createSaveDir();
        tasks.clear();
        Path path = FileSystems.getDefault().getPath(dataPath).resolve(pathString);
        BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8);
        try {
            int numTasks = Integer.parseInt(br.readLine());
            for (int i = 0; i < numTasks; i++) {
                int taskLines = Integer.parseInt(br.readLine());
                String[] repres = new String[taskLines];
                for (int j = 0; j < taskLines; j++) {
                    repres[j] = br.readLine();
                }
                switch (repres[0].charAt(0)) {
                case 'T':
                    tasks.add(Todo.parseLoad(repres));
                    break;
                case 'D':
                    tasks.add(Deadline.parseLoad(repres));
                    break;
                case 'E':
                    tasks.add(Event.parseLoad(repres));
                    break;
                default:
                    // Invalid task type, possibly from a different version - skip and continue if no other problems
                }
            }
        } catch (NumberFormatException | TaskParseException ex) {
            throw new MikiLoadException("this file is corrupted...");
        } finally {
            br.close();
        }
    }
}
