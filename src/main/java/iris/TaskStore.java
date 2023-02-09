package iris;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import iris.exception.IrisException;
import iris.exception.StorageException;
import iris.task.Task;

/**
 * stores list of tasks in hard drive to keep it saved even when chatbot is closed
 */
public class TaskStore {
    private final File taskFile;

    /**
     * initiates the file and created one if it's not present
     */
    public TaskStore() throws IrisException {
        File dir = new File("./data");
        this.taskFile = new File("./data/TaskList.txt");
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!this.taskFile.exists()) {
                this.taskFile.createNewFile();
            }
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * adds a tasks to the stored file
     * @param task the task to be added
     */
    public void addTask(Task task) throws IrisException {
        try {
            FileWriter fw = new FileWriter(taskFile, true);
            fw.write(task.storageFormat());
            fw.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * updates the file to contain the given task list
     * @param tasks the task list to be updated
     */
    public void updateTasks(TaskList tasks) throws IrisException {
        try {
            FileWriter fw = new FileWriter(this.taskFile);
            fw.write(tasks.storageFormat());
            fw.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * clears all tasks from the stored task list
     */
    public void reset() throws IrisException {
        try {
            FileWriter fw = new FileWriter(this.taskFile);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
    }

    /**
     * parses the stored file to return task list
     * @return the task list stored in the file
     * @throws IrisException if there is a problem in the stored file
     */
    public TaskList parse() throws IrisException {
        TaskList tasks = new TaskList();
        try {
            try {
                Scanner fileScanner = new Scanner(this.taskFile);
                while (fileScanner.hasNextLine()) {
                    tasks.add(Task.parse(fileScanner.nextLine()));
                }
            } catch (FileNotFoundException e) {
                this.taskFile.createNewFile();
            }
        } catch (IOException e) {
            throw new StorageException(e.getMessage());
        }
        return tasks;
    }
}
