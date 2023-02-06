package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A storage object represents tasks in Duke.
 */
public class Storage {
    private TaskList myTaskList;

    /**
     * Constructor for a Storage object
     * @param myTaskList
     */
    public Storage(TaskList myTaskList) {
        this.myTaskList = myTaskList;
        try {
            loadFromFile();
        } catch (IOException ignored) {}
    }

    /**
     * Loads tasks from a file to RAM.
     * @throws IOException
     */
    public void loadFromFile() throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFolderPath = Paths.get(home, "data");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");
        if (!Files.exists(dukeFolderPath)) {
            Files.createDirectories(dukeFolderPath);
            Files.createFile(dukeFilePath);
        } else if (!Files.exists(dukeFilePath)) {
            Files.createFile(dukeFilePath);
        }
        BufferedReader dukeReader = Files.newBufferedReader(dukeFilePath);
        String task;

        while ((task = dukeReader.readLine()) != null) {
            // process it
            // format: E|0|project meeting|Aug 6th|2pm|4pm
            String[] params = task.split("\\|");
            String type = params[0];
            boolean isCompleted = params[1].equals("1");
            String description = params[2];
            assert description != null;

            if (type.equals("T")) {
                this.myTaskList.addTask(new ToDo(description, isCompleted));
            } else if (type.equals("D")) {
                LocalDate by = LocalDate.parse(params[3]);
                this.myTaskList.addTask(new Deadline(description, isCompleted, by));
            } else if (type.equals("E")) {
                LocalDate start = LocalDate.parse(params[3]);
                LocalDate end = LocalDate.parse(params[4]);
                this.myTaskList.addTask(new Event(description, isCompleted, start, end));
            }
        }
        dukeReader.close();
    }

    /**
     * Adds a task that the user inputs to the file and to RAM.
     * @param currTask
     * @throws IOException
     */
    public void addTask(Task currTask) throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");

        String newEntry = "\n";
        String completedBit = currTask.getCompletion() ? "1" : "0";
        if (currTask instanceof Event) {
            Event e = (Event) currTask;
            String taskType = "E";
            newEntry = taskType + "|" + completedBit + "|" + currTask.getDescription() + "|"
                    + e.getStartTime() + "|" + e.getEndTime() + newEntry;
        } else if (currTask instanceof ToDo) {
            String taskType = "T";
            newEntry = taskType + "|" + completedBit + "|" + currTask.getDescription() + newEntry;
        } else if (currTask instanceof Deadline) {
            Deadline d = (Deadline) currTask;
            String taskType = "D";
            newEntry = taskType + "|" + completedBit + "|" + currTask.getDescription() + "|"
                    + d.getDeadline() + newEntry;
        }
        Files.write(dukeFilePath, newEntry.getBytes(), StandardOpenOption.APPEND); // don't need to close
        this.myTaskList.addTask(currTask);
    }

    /**
     * Deletes a task from RAM and the file
     * @param taskNo
     * @throws IOException
     */
    public void deleteTask(int taskNo) throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");
        Path tempPath = Paths.get(home, "data", "temp.txt");
        BufferedReader dukeReader = Files.newBufferedReader(dukeFilePath);
        BufferedWriter dukeWriter = Files.newBufferedWriter(tempPath);

        String currentLine;
        int currentLineIndex = 1;
        while ((currentLine = dukeReader.readLine()) != null) {
            if (currentLineIndex != taskNo) {
                dukeWriter.write(currentLine + System.getProperty("line.separator"));
            }
            currentLineIndex++; // don't copy over to temp file (which is equivalent to deleting)
        }
        dukeReader.close();
        dukeWriter.close();
        Files.delete(dukeFilePath);
        Files.move(tempPath, dukeFilePath); // move from src to dest (replace)

        this.myTaskList.deleteTask(taskNo);
    }

    /**
     * Changes completion of tasks in RAM and in the file.
     * @param taskNo
     * @throws IOException
     */
    public void changeTaskCompletion(int taskNo) throws IOException {
        String home = System.getProperty("user.dir");
        Path dukeFilePath = Paths.get(home, "data", "duke.txt");
        Path tempPath = Paths.get(home, "data", "temp.txt");
        BufferedReader dukeReader = Files.newBufferedReader(dukeFilePath);
        BufferedWriter dukeWriter = Files.newBufferedWriter(tempPath);

        String currentLine;
        int currentLineIndex = 1;
        while ((currentLine = dukeReader.readLine()) != null) {
            if (currentLineIndex == taskNo) { // if this is the one we want to change, flip the completed bit
                StringBuilder temp = new StringBuilder(currentLine);
                char flipped = temp.charAt(2) == '1' ? '0' : '1';
                temp.setCharAt(2, flipped);
                dukeWriter.write(temp + System.getProperty("line.separator"));
            } else {
                dukeWriter.write(currentLine + System.getProperty("line.separator"));
                currentLineIndex++; // don't copy over to temp file (which is equivalent to deleting)
            }
        }
        dukeReader.close();
        dukeWriter.close();
        Files.delete(dukeFilePath);
        Files.move(tempPath, dukeFilePath); // move from src to dest (replace)
    }
    public ArrayList<Task> getMatchingTasks(String targetDescription) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int i = 0; i < myTaskList.countTasks(); i++) {
            String descriptionForThisTask = myTaskList.getTaskAtIndex(i).getDescription();
            if (descriptionForThisTask.contains(targetDescription)) {
                temp.add(myTaskList.getTaskAtIndex(i));
            }
        }
        return temp;
    }
}
