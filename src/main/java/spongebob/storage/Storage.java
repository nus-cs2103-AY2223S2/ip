package spongebob.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import spongebob.exception.SpongebobEventOverlapException;
import spongebob.exception.SpongebobFileNotFoundException;
import spongebob.exception.SpongebobInvalidArgumentException;
import spongebob.exception.SpongebobIoException;

import spongebob.task.Deadlines;
import spongebob.task.Events;
import spongebob.task.Task;
import spongebob.task.ToDos;

/**
 * Represents the file to store the task list.
 */
public class Storage {
    private final String filePath;
    private final Path path;

    /**
     * Constructor to create a storage.
     *
     * @param filepath string representation of the path.
     * @throws SpongebobFileNotFoundException indicate attempt to open the file denoted by a
     *     specified pathname has failed.
     */
    public Storage(String filepath) throws SpongebobFileNotFoundException {
        try {
            this.filePath = filepath;
            this.path = Path.of(filepath);
        } catch (InvalidPathException e) {
            throw new SpongebobFileNotFoundException("File is not found!");
        }
    }

    /**
     * Sets a default storage if the file or folder is not found.
     */
    public static void setDefaultStorage() {
        File folder = new File("data/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        File file = new File("data/spongebob.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("IO Exception occur");
            }
        }
    }

    /**
     * Inserts new task into the file.
     *
     * @param t task to be inserted.
     * @throws SpongebobIoException indicate failed or interrupted I/O operations occurred.
     */
    public void updateData(Task t) throws SpongebobIoException {
        try {
            List<String> allLines = Files.readAllLines(path);
            createTaskDescriptionForTasklist(t, allLines);
            Files.write(path, allLines);
        } catch (IOException e) {
            throw new SpongebobIoException("Cannot read from " + filePath + " data file");
        }
    }

    /**
     * Updates the task in the file with its new state.
     *
     * @param lineNumber line number in the file to be toggled.
     * @param status status of file that indicate whether the task is done or not.
     * @throws SpongebobIoException indicate failed or interrupted I/O operations occurred.
     */
    public void updateData(int lineNumber, int status) throws SpongebobIoException {
        try {
            List<String> allLines = Files.readAllLines(path);
            // overwrite the spongebob.txt file by updating the task's new status
            updateTaskStatusForTasklist(lineNumber, status, allLines);
            Files.write(path, allLines);
        } catch (IOException e) {
            throw new SpongebobIoException("Cannot read from " + filePath + " data file");
        }
    }

    private static void createTaskDescriptionForTasklist(Task t, List<String> allLines) {
        String s = String.format("%s | %s | %s", t.toString().charAt(1), t.getStatusIcon(), t.getDescription());
        if (t instanceof Deadlines) {
            // create string description for deadline task to store in data file
            Deadlines d = (Deadlines) t;
            s += String.format(" | %s", d.getDeadline());
        } else if (t instanceof Events) {
            // create string description for event task to store in data file
            Events e = (Events) t;
            s += String.format(" | %s | %s", e.getStartTime(), e.getEndTime());
        }
        allLines.add(s);
    }

    private static void updateTaskStatusForTasklist(int lineNumber, int status, List<String> allLines) {
        String line = allLines.get(lineNumber);
        String s = line.substring(0, 4) + status + line.substring(5);
        allLines.set(lineNumber, s);
    }

    /**
     * Removes specific task from the file.
     *
     * @param lineNumber line number in the file to be deleted.
     * @throws SpongebobIoException indicate failed or interrupted I/O operations occurred.
     */
    public void removeData(int lineNumber, Task task) throws SpongebobIoException {
        try {
            List<String> allLines = Files.readAllLines(path);
            removeDataFromEventListIfIsEventTask(task);
            // remove the line from spongebob.txt
            allLines.remove(lineNumber);
            Files.write(path, allLines);
        } catch (IOException e) {
            throw new SpongebobIoException("Cannot read from " + filePath + " data file");
        }
    }

    private void removeDataFromEventListIfIsEventTask(Task t) {
        if (t instanceof Events) {
            Events.deleteEventTimeList((Events) t);
        }
    }

    /**
     * Loads the task from the file at the beginning of the program execution.
     *
     * @return list of task that stored in the file before.
     * @throws SpongebobIoException indicate failed or interrupted I/O operations occurred.
     * @throws SpongebobInvalidArgumentException indicate that a command has been passed an illegal argument.
     * @throws SpongebobEventOverlapException indicate there are overlapping tasks exist.
     */
    public ArrayList<Task> setUpStorageAndLoadData() throws SpongebobIoException, SpongebobInvalidArgumentException,
            SpongebobEventOverlapException {
        ArrayList<Task> task = load();
        setEventTaskList(task);
        return task;
    }

    private ArrayList<Task> load() throws SpongebobIoException,
            SpongebobInvalidArgumentException, SpongebobEventOverlapException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            List<String> allLines = Files.readAllLines(path);

            if (allLines.isEmpty()) {
                return taskList;
            }

            for (String taskDescription : allLines) {
                Task t = createTaskFromStorage(taskDescription);
                taskList.add(t);
            }

            return taskList;
        } catch (IOException e) {
            throw new SpongebobIoException("Cannot read from " + filePath + " data file");
        }
    }

    private void setEventTaskList(ArrayList<Task> tasks) {
        ArrayList<Events> eventTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task instanceof Events) {
                eventTasks.add((Events) task);
            }
        }
        Events.setEventList(eventTasks);
    }

    private Task createTaskFromStorage(String description) throws SpongebobInvalidArgumentException,
            SpongebobEventOverlapException {
        String[] s = description.split(" \\| ");
        Task t = null;
        boolean isDone = s[1].equals("1");
        switch (s[0]) {
        case "T":
            t = new ToDos(s[2]);
            break;
        case "D":
            t = new Deadlines(s[2], s[3]);
            break;
        case "E":
            t = new Events(s[2], s[3], s[4]);
            break;
        }
        assert t != null : "Attempt to create empty task when load data from storage.";
        t.setDone(isDone);
        return t;
    }
}
