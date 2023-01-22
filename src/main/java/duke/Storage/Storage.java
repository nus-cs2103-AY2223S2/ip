package duke.Storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Exceptions.DukeException;
import duke.Task.Deadline;
import duke.Task.Event;
import duke.Task.Task;
import duke.Task.Todo;
/**
 * Storage class that handles the loading and saving of tasks.
 */
public class Storage {
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    
    /** 
     * Loads the tasks from the data file into the task list.
     * 
     * @return ArrayList<Task> Returns an ArrayList containing the tasks from the data file.
     * @throws DukeException If the data file could not be found.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        File file = this.filePath.toFile();
        if (!file.exists()) {
            throw new DukeException("Data file could not be found.");
        }
        ArrayList<Task> loadedList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] args = scanner.nextLine().split(" \\| ");
                if ("T".equals(args[0])) {
                    Todo newTodo = new Todo(args[2]);
                    if (args[1].equals("1"))
                        newTodo.toggleMark();
                    loadedList.add(newTodo);
                } else if ("D".equals(args[0])) {
                    Deadline newDeadline = new Deadline(args[2], LocalDateTime.parse(args[3], formatter));
                    if (args[1].equals("1"))
                        newDeadline.toggleMark();
                    loadedList.add(newDeadline);
                } else if ("E".equals(args[0])) {
                    Event newEvent = new Event(args[2], LocalDateTime.parse(args[3], formatter),
                            LocalDateTime.parse(args[4], formatter));
                    if (args[1].equals("1"))
                        newEvent.toggleMark();
                    loadedList.add(newEvent);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file could not be found.");
        }
        return loadedList;
    }

    
    /** 
     * Saves the tasks in the tasklist into the data file.
     * 
     * @param tasks The task list converted to an ArrayList to be saved.
     * @throws DukeException If the data file could not be found or there was an error creating the data file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        File file = filePath.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(String.format("File could not be created at %s", filePath));
            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Task task : tasks) {
                int mark = task.getMark().equals('X') ? 1 : 0;
                String newString = String.format("T | %d | %s%n", mark, task.getDescription());
                if (task instanceof Deadline)
                    newString = String.format("D | %d | %s | %s%n", mark, task.getDescription(),
                            ((Deadline) task).getBy());
                else if (task instanceof Event)
                    newString = String.format("E | %d | %s | %s | %s%n", mark, task.getDescription(),
                            ((Event) task).getFrom(),
                            ((Event) task).getTo());
                fileWriter.write(newString);
            }
        } catch (IOException e) {
            throw new DukeException("Data file could not be found.");
        }
    }
}
