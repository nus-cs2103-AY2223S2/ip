package duke.functions;

import duke.ToDoList;

import duke.exceptions.LoadDukeException;
import duke.exceptions.SaveDukeException;

import duke.tasks.DeadlineTask;
import duke.tasks.EventTask;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import java.util.Scanner;

/**
 * A class that can hold a specified directory path with respect
 * to the home directory of the user.
 * It can then save or load the state of the ToDoList to and from
 * the specified directory path respectively.
 */
public class Storage {
    private Path path;

    public Storage(String pathStr) {
        String home = System.getProperty("user.home");
        this.path = Paths.get(home, pathStr);
    }

    /**
     * Creates the given directory if it does not exist.
     *
     * @param dir The directory path to be created if it does not exist.
     * @throws IOException If the given directory path does not exist
     *                     even after creating it in this method.
     */
    public void createDirectory(Path dir) throws IOException {
        if (!Files.exists(dir)) {
            createDirectory(dir.getParent());
        }
        Files.createDirectories(dir);
    }

    /**
     * Creates the given file and its directories if it does not exist.
     *
     * @param filePath The file and directory path to be created if it
     *                 does not exist.
     * @throws IOException If the given path does not exist
     *                     even after creating it in this method.
     */
    public void createFile(Path filePath) throws IOException{
        if (!Files.exists(filePath)) {
            createDirectory(filePath.getParent());
            Files.createFile(filePath);
        }
    }

    /**
     * Returns an instance of a ToDoList object that contains the specific tasks
     * in their correct state based on the given path stored in the Storage object.
     *
     * @throws LoadDukeException If an error occurred while loading the contents of
     *                           the file from the path stored in this Storage object.
     */
    public ToDoList load() throws LoadDukeException {
        try {
            createFile(path);
            File f = new File(path.toString());
            Scanner sc = new Scanner(f);
            return Parser.handleLoadCommand(sc);
        } catch (Exception e) {
            throw new LoadDukeException();
        }
    }

    /**
     * Saves the specific states of the different Task objects in the give ToDoList
     * to the file pointed to by the path stored in the Storage object.
     *
     * @param list The ToDoList that is to have its state saved.
     * @throws SaveDukeException If an error occurred while saving the contents of
     *                           the ToDoList object to the file.
     */
    public void save(ToDoList list) throws SaveDukeException {
        try {
            FileWriter fw = new FileWriter(path.toString());
            int count = list.getToDoCount();
            for (int i = 1; i <= count; i++) {
                fw.write(list.getTask(i).save());
            }
            fw.close();
        } catch (Exception e) {
            throw new SaveDukeException();
        }
    }

}
