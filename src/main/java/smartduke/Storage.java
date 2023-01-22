package smartduke;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the saved tasks from local file into the list.
     * @return The list of tasks stored in the local file.
     * @throws DukeException If the directory that stores the tasks data does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();

            File tasksData = this.filePath.toFile();
            if (!tasksData.exists()) {
                tasksData.createNewFile();
            }

            Scanner fileReader = new Scanner(tasksData);
            while (fileReader.hasNextLine()) {
                try {
                    String taskData = fileReader.nextLine();
                    String[] parsedTaskData = taskData.split(" \\| ");
                    String taskSymbol = parsedTaskData[0];
                    boolean isTaskDone = parsedTaskData[1].equals("1");
                    String taskDescription = parsedTaskData[2];

                    /* Add the task to taskList */
                    Task task = null;
                    switch (taskSymbol) {
                    case "T":
                        task = new ToDo(taskDescription);
                        break;
                    case "D":
                        String by = parsedTaskData[3];
                        task = new Deadline(taskDescription, Parser.parseDateTime(by));
                        break;
                    case "E":
                        String from = parsedTaskData[3];
                        String to = parsedTaskData[4];
                        task = new Event(taskDescription, Parser.parseDateTime(from), Parser.parseDateTime(to));
                        break;
                        default:
                            throw new DukeException("Task is not recorded in a valid format...");
                    }
                    if (isTaskDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
            }
            fileReader.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("The directory that stores the tasks data does not exist...");
        }
    }

    /**
     * Saves the current tasks from the task list into local file.
     * @throws DukeException If the directory that stores the tasks data does not exist.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            ArrayList<Task> tasks = taskList.list();

            File tasksData = this.filePath.toFile();
            tasksData.createNewFile();

            FileWriter fileWriter = new FileWriter(tasksData);

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                fileWriter.write(task.getSavedFormat());
                fileWriter.write('\n');
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("The directory that stores the tasks data does not exist...");
        }
    }
}
