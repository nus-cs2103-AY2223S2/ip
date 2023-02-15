package duke.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import duke.exceptions.MemoryFailedException;
import duke.tasks.Task;
import duke.tasks.TaskType;

/**
 * Handles the loading of data from and storage of data to memory.
 */
public class Storage {

    private Path memoryPath;
    private File memory;

    public Storage(String[] memoryPathArray) {
        assert memoryPathArray.length > 0 : "Memory Path Array should not be empty";

        this.memoryPath = Paths.get(memoryPathArray[0],
                Arrays.copyOfRange(memoryPathArray, 1, memoryPathArray.length));
        this.memory = new File(String.valueOf(memoryPath));
    }

    /**
     * Loads tasks from memory into the TaskList in the application.
     *
     * @param allTasks the TaskList containing all the tasks logged in by the user.
     * @throws MemoryFailedException if there is an issue retrieving data from memory.
     */
    public void loadTasks(TaskList allTasks) throws MemoryFailedException {
        try {
            boolean isSuccessful = this.memory.createNewFile();
            Scanner memoryScanner = new Scanner(memory);
            while (memoryScanner.hasNext()) {
                String taskLine = memoryScanner.nextLine();
                loadTaskLine(taskLine, allTasks);
            }
        } catch (IOException e) {
            throw new MemoryFailedException();
        }
    }

    /**
     * Parses each line within the memory file, converts it into a Task object,
     * and adds it to the TaskList in the application.
     *
     * @param taskLine a String from memory, representing a Task object.
     * @param allTasks the TaskList containing all the tasks logged in by the user.
     */
    private void loadTaskLine(String taskLine, TaskList allTasks) {
        // TODO: Handle corruption in file, leading to incorrect syntax
        // TODO: Edit such that it doesn't print anything
        String[] attributes = taskLine.split(", ");
        String type = attributes[0];
        String doneNumber = attributes[1];
        boolean isDone = Objects.equals(doneNumber, "1");
        String title = attributes[2];
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm a");
        if (Objects.equals(type, "T")) {
            allTasks.addToList(title, TaskType.TODO, null, null, isDone, false);
        } else if (Objects.equals(type, "D")) {
            LocalDateTime dateBy = LocalDateTime.parse(attributes[3], dateFormat);
            allTasks.addToList(title, TaskType.DEADLINE, null, dateBy, isDone, false);
        } else if (Objects.equals(type, "E")) {
            LocalDateTime start = LocalDateTime.parse(attributes[3], dateFormat);
            LocalDateTime end = LocalDateTime.parse(attributes[4], dateFormat);
            allTasks.addToList(title, TaskType.EVENT, start, end, isDone, false);
        } else {
            // TODO: Think if I can alert user of this
            System.out.println("Some task in memory does not fall into the three task categories!"
                    + "\nThese are not loaded.");
        }
    }

    /**
     * Saves tasks from the TaskList in the application into memory.
     *
     * @param allTasks the TaskList containing all the tasks logged in by the user.
     */
    public void saveTasks(TaskList allTasks) {
        // TODO: Handle case where file is destroyed while script is running
        try {
            BufferedWriter fw = Files.newBufferedWriter(this.memoryPath, StandardOpenOption.TRUNCATE_EXISTING);
            for (Task task: allTasks.getArray()) {
                fw.write(task.convertToMemoryString());
                fw.newLine();
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
