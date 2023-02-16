package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * A class that stores tasks by writing into file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    /**
     * Write at the end of file.
     * @param filePath the path of the file to be written.
     * @param textToAppend the text to be appended.
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Write marking to a specific line on the file.
     * @param filePath the path of the file to be written.
     * @param mark the marking to be written.
     * @param line the line to be written.
     */
    public void writeMarkingToFile(String filePath, String mark, int line) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath),
                                                                                StandardCharsets.UTF_8));
        String s = fileContent.get(line);
        ArrayList<String> taskDetails = new ArrayList<>(Arrays.asList(s.split(" \\| ")));
        taskDetails.set(1, mark);
        StringBuilder task = new StringBuilder();
        for (int i = 0; i < taskDetails.size() - 1; i++) {
            task.append(taskDetails.get(i)).append(" | ");
        }
        task.append(taskDetails.get(taskDetails.size() - 1));
        fileContent.set(line, task.toString());
        Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Delete a task from the file.
     * @param filePath the path of the file to be written.
     * @param line the line to be deleted.
     */
    public void deleteTaskInFile(String filePath, int line) throws IOException {
        ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath),
                                                                                StandardCharsets.UTF_8));
        fileContent.remove(line);
        Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Load all the tasks written in the file.
     * @return An array of tasks.
     */
    public ArrayList<Task>readTaskList() throws IOException {
        ArrayList<String> dirsArray = new ArrayList<>(Arrays.asList(filePath.split("/")));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < dirsArray.size() - 1; i++) {
            stringBuilder.append(dirsArray.get(i));
        }
        String dirPath = stringBuilder.toString();
        Files.createDirectories(Paths.get(dirPath));
        File f = new File(filePath); // create a File for the given file path
        f.createNewFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> userTasks = new ArrayList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            String[] taskDetails = task.split(" \\| ");
            Task loadTask;
            switch(taskDetails[0]) {
            case "T": {
                loadTask = new ToDo(taskDetails[2]);
                break;
            }
            case "D": {
                loadTask = new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]));
                break;
            }
            case "E": {
                loadTask = new Event(taskDetails[2], LocalDate.parse(taskDetails[3]), LocalDate.parse(taskDetails[4]));
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + taskDetails[0]);
            }
            if (taskDetails[1].equals("1")) {
                loadTask.markTask();
            }
            userTasks.add(loadTask);
        }
        return userTasks;
    }
}
