package duke;

import task.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * Handles file operations.
 */
public class Storage {

    /** Stores filePath */
    private Path filePath;

    /**
     * Creates an instance of Storage.
     *
     * @param filePath Path of file from the home directory in Unix format.
     */
    public Storage(String filePath) {
        String home = System.getProperty("user.home"); // Get home directory
        String[] s = filePath.split("/");

        // Create directories
        this.filePath = Paths.get(home);
        for (int i = 0; i < s.length - 1; i++) {
            this.filePath = Paths.get(String.valueOf(this.filePath), s[i]);

            try {
                Files.createDirectories(this.filePath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }

        }

        // Create file
        this.filePath = Paths.get(String.valueOf(this.filePath), s[s.length-1]);
        try {
            Files.createFile(this.filePath); // Create empty file if it does not exist
        }
        catch (FileAlreadyExistsException ignored) {
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

    /**
     * Returns a list of all task stored in the file.
     *
     * @return List of all task stored in the file
     */
    public LinkedList<Task> loadFile() {

        LinkedList<Task> savedInputs = new LinkedList<>();

        try {
            Boolean isTaskDone;
            String taskDetails, taskDate;
            String[] savedTasks = Files.readString(filePath).split("\n");

            if (savedTasks[0].isBlank()) {
                return savedInputs; // blank file
            }

            for (String task : savedTasks) {
                switch (task.charAt(0)) {
                    case 'T':
                        isTaskDone = this.getIsTaskDone(task);
                        taskDetails = this.getTaskDetails(task);
                        savedInputs.add(new ToDo(isTaskDone, taskDetails));
                        break;
                    case 'D':
                        isTaskDone = this.getIsTaskDone(task);
                        taskDetails = this.getTaskDetails(task);
                        taskDate = this.getTaskDate(task);
                        savedInputs.add(new Deadline(isTaskDone, taskDetails, taskDate));
                        break;
                    case 'E':
                        isTaskDone = this.getIsTaskDone(task);
                        taskDetails = this.getTaskDetails(task);
                        taskDate = this.getTaskDate(task);
                        savedInputs.add(new Event(isTaskDone, taskDetails, taskDate));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // file creation unsuccessful
        }
        return savedInputs;
    }

    private Boolean getIsTaskDone(String s) {
        String line = s.substring(s.indexOf("|") + 1);
        return line.substring(0, line.indexOf("|")).equals("X");
    }

    private String getTaskDetails(String s) {
        String line = s.substring(s.indexOf("|") + 1);
        line = line.substring(line.indexOf("|") + 1);

        if (!line.contains("|")) {
            return line;
        }

        return line.substring(0, line.indexOf("|"));
    }

    private String getTaskDate(String s) {
        String line = s.substring(s.indexOf("|") + 1);
        line = line.substring(line.indexOf("|") + 1);
        line = line.substring(line.indexOf("|") + 1);
        return line;
    }

    /**
     * Overwrites the entire file with the current list of task.
     *
     * @param taskList Current list of task.
     */
    public void overwriteFile(TaskList taskList) {
        StringBuilder s = new StringBuilder();
        for (Task storedInput : taskList.getList()) {
            s.append(storedInput.writeToFile());
            s.append("\n");
        }

        try {
            Files.writeString(filePath, s);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
