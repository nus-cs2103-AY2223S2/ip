import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    protected Path directory;
    protected Path filePath;

    /**
     * Initialises the location where save data will be stored.
     *
     * @param filePath Filepath where the save data is stored.
     */
    public Storage(String filePath) {
        String root = System.getProperty("user.dir");
        this.directory = Paths.get(root);
        this.filePath = Paths.get(root, filePath);

        int index = filePath.lastIndexOf("/") - 1;
        if (index > 0) {
            this.directory = Paths.get(root,
                    filePath.substring(0, index));
        }
    }

    /**
     * Checks the directories and filepath of the storage location.
     * If directory does not exist, create the necessary directories.
     * If file does not exist, create the file.
     */
    private void checkStorage() throws DukeException {
        try {
            // Checks if directory exist, if not create directory
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // Checks if filepath exist, if not create directory
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("Failed to check storage due to I/O interrupt");
        }
    }

    /**
     * Saves the changes made to the list of task in the hard disk
     *
     * @param tasks List of tasks
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            // Convert list of tasks to save data
            StringBuilder output = new StringBuilder();
            for (Task task: tasks.getTasks()) {
                output.append(task.save())
                        .append("\n");
            }

            // Saves list of tasks in hard disk
            checkStorage();
            Files.write(filePath,
                    output.toString()
                            .getBytes());
        } catch (IOException e) {
            throw new DukeException("Failed to save data due to I/O interrupt");
        }
    }

    /**
     * Returns the data stored in the hard disk
     *
     * @return List of tasks as string
     */
    public TaskList load() throws DukeException {
        try {
            // Gets save data from hard disk
            checkStorage();
            String[] saveData = Files.readAllLines(filePath)
                    .toArray(new String[0]);

            // Load list of tasks from save data
            TaskList tasks = new TaskList();
            for (String line: saveData) {
                String[] data = line.split(" \\| ", 3);
                String type = data[0];
                boolean isDone = data[1].equals("X");
                switch (data[0]) {
                case "T":
                    tasks.add(Todo.load(data[2], isDone));
                    break;
                case "D":
                    tasks.add(Deadline.load(data[2], isDone));
                    break;
                case "E":
                    tasks.add(Event.load(data[2], isDone));
                    break;
                }
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Loading failed due to I/O interrupt");
        }
    }
}
