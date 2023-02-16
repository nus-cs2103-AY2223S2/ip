package duke.storage;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.TaskList;
import duke.exceptions.DukeException;
import duke.tasks.Task;


public class Storage {


    protected String path;

    public Storage(String path) {
        this.path = path;
    }


    /**
     * Loads the tasks from the text file and store them in the task list
     *
     * @return A file object to be given to the task list as input
     * @throws DukeException Throws DukeException of a specific massage
     */
    public File load() throws DukeException {
        Path directory = Paths.get("data");
        Path file = Paths.get(path);
        boolean fileExists = java.nio.file.Files.exists(file);
        boolean directoryExists = java.nio.file.Files.exists(directory);
        if (!directoryExists) {
            createFolder();
        }
        if (!fileExists) {
            createFile();
        }
        File f = new File(path); // create a File for the given file path
        return f;

    }


    /**
     * Writes the tasks to the text file when there is a change
     *
     * @param tasks The task list
     * @throws DukeException Throws DukeException of a specific massage
     */
    public void write(TaskList tasks) throws DukeException {
        try {
            Path directory = Paths.get("data");
            Path file = Paths.get(path);
            boolean fileExists = java.nio.file.Files.exists(file);
            boolean directoryExists = java.nio.file.Files.exists(directory);
            if (!directoryExists) {
                createFolder();
            }
            if (!fileExists) {
                createFile();
            }
            FileWriter writer = new FileWriter("./data/duke.txt");
            String output = "";
            for (int j = 0; j < tasks.getLength(); j++) {
                Task cur = tasks.getTask(j);
                output += extractTask(cur);
            }
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }

    /**
     * Converts each task in the task list to the format to be used in the text file
     *
     * @param task
     * @return A string description of the task to be written into the text file
     */
    public String extractTask(Task task) {

        String status = "";
        if (task.getStatusIcon().equals("X")) {
            status = "1";
        } else {
            status = "0";
        }
        if (task.getType().equals("T")) {
            return (task.getType() + " | " + status + " | "
                + task.getDetail() + "\n");
        } else {
            return (task.getType() + " | " + status + " | "
                + task.getDetail() + "| " + task.getTime() + "\n");
        }

    }


    /**
     * Creates a file named duke.txt when the file does not exist
     *
     * @throws DukeException DukeException saying file creation failed
     */
    public void createFile() throws DukeException {
        File folder = new File("data");
        File newFile = new File(folder, "duke.txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File creation failed");
        }
    }

    /**
     * Creates a folder named data and a file duke.txt inside when the folder does not exist
     *
     * @throws DukeException DukeException saying file creation failed
     */
    public void createFolder() throws DukeException {
        File folder = new File("data");
        folder.mkdir();
        File newFile = new File(folder, "duke.txt");
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File creation failed");
        }
    }


}
