package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Class that is responsible for loading tasks from task storage file and writing to the file.
 */
public class Storage {

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    /**
     * Reads from txt file at path to extract Tasks to form a TaskList.
     * @return array list of tasks
     * @throws DukeException if failed to find the file at given path
     */
    public ArrayList<Task> load() throws DukeException {
        //if file is found successfully
        try {
            Files.createDirectories(Path.of(this.path).getParent()); //create parent directories if none exists
            File fileTxt = new File(this.path);
            Scanner scanner = new Scanner(fileTxt);
            ArrayList<Task> taskArrayList = new ArrayList<>();
            while (scanner.hasNext()) {
                String savedTaskLine = scanner.nextLine();
                Task savedTask = Parser.parseFromFile(savedTaskLine);
                taskArrayList.add(savedTask);
            }
            return taskArrayList;
        } catch (IOException e) {
            throw new DukeException("Failed to load task file");
        }
    }

    public void setPath(String newPath) {
        this.path = newPath;
    }

    /**
     * Overwrites the file with contents from taskList.
     * Used by mark/unmark/delete commands.
     * @param taskList instance of TaskList object
     * @throws IOException if specified file cannot be accessed/written to
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (Task t : taskList.getList()) {
            fw.write(t.toTxt() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Appends the file with the new task, leaving the prior contents of the file unchanged.
     * Used by Add commands.
     * @param task instance of Task object
     * @throws IOException if specified file cannot be accessed/written to
     */
    public void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(path, true);
        fw.write(task.toTxt() + System.lineSeparator());
        fw.close();
    }

}
