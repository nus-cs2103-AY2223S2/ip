package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Task;

/**
 * Used to store data from past lists and save new data inputted by user.
 */
public class Storage {
    private String filepath;
    private String path;
    private File file;

    /**
     * Constructor for Storage class.
     * @param filepath Relative filepath to load past data and save new data.
     * @throws DukeException The custom exception when user commands don't make sense.
     */
    public Storage(String filepath) throws DukeException {
        this.filepath = filepath;
        int ptr = this.filepath.indexOf("/");
        int index = -1;
        while (ptr != -1) {
            index = ptr;
            ptr = this.filepath.indexOf("/", index + 1);
        }
        if (index != -1) {
            this.path = this.filepath.substring(0, index);
            File path = new File(this.path);
            path.mkdirs();
        }
        try {
            this.file = new File(this.filepath);
            this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException(null);
        }
    }

    /**
     * Loads past data inputted by user.
     * @return An array of tasks containing the list of tasks still in the list after user previously exited.
     * @throws DukeException When filepath of file that is supposed to store past data cannot be created or found.
     */
    public Task[] load() throws DukeException {
        Task[] tasks = new Task[100];
        try {
            Scanner scanner = new Scanner(this.file);
            String data;
            for (int i = 0; scanner.hasNextLine(); i++) {
                data = scanner.nextLine();
                int len = data.length();
                String command = data.substring(0, len - 1);
                String marked = data.substring(len - 1);
                Task task = Task.makeTask(command);
                if (marked.equals("1")) {
                    task.mark();
                }
                tasks[i] = task;
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(null);
        }
        return tasks;
    }

    /**
     * Saves new data inputted by user.
     * @param taskList The TaskList object storing the list of Task objects.
     * @throws DukeException When filepath of file that is supposed to store new data cannot be created or found.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filepath);
            writer.write(taskList.taskListToSavedForm());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("There was a problem saving this task and changes made to it. Please try again.");
        }
    }
}
