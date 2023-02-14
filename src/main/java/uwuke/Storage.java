package uwuke;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Collection;

import uwuke.model.Deadline;
import uwuke.model.Event;
import uwuke.model.Task;
import uwuke.model.TaskList;
import uwuke.model.Todo;

import java.util.Arrays;

/**
 * Handler for task storage and retrieval
 */
public class Storage {

    private static final Path DATA_DIRECTORY = Path.of("data");
    private static final Path DATA_FILE = Path.of("data", "tasks.dat");

    public static void saveTasks(Collection<? super Task> tasks) throws Exception {
        if (!Files.exists(DATA_DIRECTORY)) {
            Files.createDirectory(DATA_DIRECTORY);
        }
        
        // Creates .dat file if it doesn't exist, removes all content originally there
        Files.write(DATA_FILE, Arrays.toString(tasks.toArray()).getBytes());
    }

    public static TaskList readSavedTasks() throws Exception {
        String rawString = Files.readString(DATA_FILE);
        String[] taskStrings = rawString.split(", ");
        taskStrings = cleanTaskString(taskStrings);
        TaskList tasks = new TaskList();
        for (String s : taskStrings) {
            tasks.addTask(getTaskFromString(s));
        }
        return tasks;
    }

    /**
     * Return array of tasks without beginning and ending brackets
     * 
     * @param taskStrings Array of tasks split by commas
     * @return Cleaned up array
     */
    private static String[] cleanTaskString(String[] taskStrings) {
        if (taskStrings.length == 1) {
            String s = taskStrings[0];
            taskStrings[0] = s.substring(1, s.length()-1);
            return taskStrings;
        } else {
            String f = taskStrings[0];
            String e = taskStrings[taskStrings.length-1];
            taskStrings[0] = f.substring(1);
            taskStrings[taskStrings.length-1] = e.substring(0, e.length()-1);
            return taskStrings;
        }
    }

    private static Task getTaskFromString(String taskString) throws DukeException {
        boolean isMarked = taskString.charAt(4) == 'X';
        char type = taskString.charAt(1);
        if (type == 'T') {
            String s = StorageParser.parseTodo(taskString);
            Todo td = new Todo(s);
            if (isMarked) {
                td.markDone();
            }
            return td;
        } else if (type == 'D') {
            String[] s = StorageParser.parseDeadline(taskString);
            Deadline dl = new Deadline(s[0], s[1]);
            if (isMarked) {
                dl.markDone();
            }
            return dl;
        } else if (type == 'E') {
            String[] s = StorageParser.parseEvent(taskString);
            Event e = new Event(s[0], s[1], s[2]);
            if (isMarked) {
                e.markDone();
            }
            return e;
        } else {
            throw new DukeException("Cannot parse save file");
        }
    }
}
