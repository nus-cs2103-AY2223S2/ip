package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Stores the user tasks.
 */
public class Storage {
    protected String filePath;
    protected File file;
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    /**
     * Constructor for the Storage class.
     * @param filePath the pathname string of the file to keep track of user things.
     * @throws DukeException If there was an error in finding or creating the file.
     */
    public Storage(String filePath) throws DukeException {
        try {
            this.filePath = filePath;
            this.file = new File(filePath);
            this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to create file \"" + filePath + "\"");
        }
    }

    /**
     * Loads the Tasks stored in the file to the TaskList class, to be used for the current Duke program.
     * @return ArrayList containing the Tasks stored in the file.
     * @throws DukeException If there was an error when attempting to read the file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasksArrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String currentLine = br.readLine();
            while (currentLine != null) {
                Task newTask = taskStringParser(currentLine);
                if (newTask == null) {
                    throw new DukeException("Error in reading duke.txt file");
                }
                tasksArrayList.add(newTask);
                currentLine = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasksArrayList;
    }

    /**
     * Converts a line of text in the file to a Task.
     * @param currentLine the current line of text being parsed.
     * @return the converted Task.
     */
    public Task taskStringParser(String currentLine) {
        String taskType = currentLine.substring(0, 3);
        String marked = currentLine.substring(3, 6);
        String details = currentLine.substring(7);

        switch (taskType) {
        case "[T]":
            return toDoParser(marked, details);
        case "[D]":
            return deadlineParser(marked, details);
        case "[E]":
            return eventParser(marked, details);
        default:
            return null;
        }
    }

    /**
     * Parses the line of text in the file to a ToDo.
     * @param marked the String representation of whether the task has been completed.
     * @param details the description and relevant dates (if any) of the task.
     * @return the created ToDo.
     */
    private ToDo toDoParser(String marked, String details) {
            ToDo toDo = new ToDo(details);
            if (marked.equals("[X]")) {
                toDo.markUnmark(true);
            }
            return toDo;
    }

    /**
     * Parses the line of text in the file to a Deadline.
     * @param marked the String representation of whether the task has been completed.
     * @param details the description and relevant dates (if any) of the task.
     * @return the created Deadline.
     */
    private Deadline deadlineParser(String marked, String details) {
        String description = details.substring(0, details.indexOf(" (by:"));
        String byString = details.substring(details.indexOf(" (by:") + " (by:".length() + 1,
                details.indexOf(")"));

        LocalDateTime by = LocalDateTime.parse(byString, FORMATTER);

        Deadline deadline = new Deadline(description, by);
        if (marked.equals("[X]")) {
            deadline.markUnmark(true);
        }
        return deadline;
    }

    /**
     * Parses the line of text in the file to an Event.
     * @param marked the String representation of whether the task has been completed.
     * @param details the description and relevant dates (if any) of the task.
     * @return the created Event.
     */
    private Event eventParser(String marked, String details) {
        String eventDescription = details.substring(0, details.indexOf(" (from:"));
        String fromString = details.substring(details.indexOf(" (from:") + " (from:".length() + 1,
                details.indexOf(" to: "));
        String toString = details.substring(details.indexOf(" to:") + " to:".length() + 1,
                details.indexOf(")"));

        LocalDateTime from = LocalDateTime.parse(fromString, FORMATTER);
        LocalDateTime to = LocalDateTime.parse(toString, FORMATTER);

        Event event = new Event(eventDescription, from, to);
        if (marked.equals("[X]")) {
            event.markUnmark(true);
        }
        return event;
    }

    /**
     * Updates the file whenever there is a change in the TaskList.
     * @param taskList containing the updated Tasks.
     * @throws DukeException If there is an error when updating the file.
     */
    public void update(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                fileWriter.write(t.toString());
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
