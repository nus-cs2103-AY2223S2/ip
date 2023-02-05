package aqua.manager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

import aqua.aquatask.AquaTask;
import aqua.storage.Reloadable;
import aqua.util.DateUtils;
import aqua.util.FileUtils;

/** Manager of tasks. */
public class TaskManager implements Reloadable {
    /** Path of save directory. */
    private static final String SAVE_DIRECTORY = "SAVE_DATA";
    /** Path of save file relative to save directory. */
    private static final String SAVE_FILE = "Goshujin-sama you promised to never touch this.txt";

    /** Array list containing tasks. */
    private final ArrayList<AquaTask> tasks = new ArrayList<>();


    /**
     * Adds the specified task.
     *
     * @param task - the task to add.
     */
    public void add(AquaTask task) {
        tasks.add(task);
    }


    /**
     * Marks task in the at the given index as specified.
     *
     * @param taskNum - the index of the task to mark.
     * @param isComplete - {@code true} to mark the task as complete and
     *      {@code false} as incomplete.
     * @return the modified task.
     * @throws IndexOutOfBoundsException if taskNum is out of range.
     */
    public AquaTask mark(int taskNum, boolean isComplete) throws IndexOutOfBoundsException {
        tasks.set(taskNum, tasks.get(taskNum).mark(isComplete));
        return tasks.get(taskNum);
    }


    /**
     * Deletes the task at the given index.
     *
     * @param taskNum - the index of the task to delete.
     * @return the deleted task.
     * @throws IndexOutOfBoundsException if taskNum is out of range.
     */
    public AquaTask delete(int taskNum) throws IndexOutOfBoundsException {
        return tasks.remove(taskNum);
    }


    public TaskFilterReport filterWithin(LocalDateTime start, LocalDateTime end) {
        ArrayList<AquaTask> filtered = new ArrayList<>();
        ArrayList<AquaTask> remaining = new ArrayList<>();
        for (AquaTask task : tasks) {
            if (!task.getEnd().isPresent()) {
                remaining.add(task);
                continue;
            }
            LocalDateTime taskEnd = task.getEnd().get();
            LocalDateTime taskStart = taskEnd;
            if (task.getStart().isPresent()) {
                taskStart = task.getStart().get();
            }
            if (!DateUtils.isIntersecting(taskStart, taskEnd, start, end)) {
                remaining.add(task);
                continue;
            }
            filtered.add(task);
        }
        return new TaskFilterReport(filtered, remaining);
    }


    /**
     * Returns a filtered LinkedHashMap of task number - task pair of the tasks
     * with names matching the specified patteren. Task number in the returned
     * hash map is 1 based and the iteration order is sorted according to the
     * task's task number within this task manager.
     *
     * @param pattern - the String character sequence of the task's name to
     *      include.
     * @return a LinkedHashMap of task number - task pair containing the
     *      filtered tasks.
     */
    public LinkedHashMap<Integer, AquaTask> filter(String pattern) {
        LinkedHashMap<Integer, AquaTask> taskMap = new LinkedHashMap<>();
        Stream.iterate(0, i -> i + 1)
                .limit(size())
                .filter(i -> tasks.get(i).getName().contains(pattern))
                .forEach(i -> taskMap.put(i + 1, tasks.get(i)));
        return taskMap;
    }


    /**
     * Returns the number of tasks stored.
     *
     * @return the number of tasks stored.
     */
    public int size() {
        return tasks.size();
    }


    /**
     * Saves the state of this task manager to hard disk.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void saveToFile() throws IOException {
        Path dirPath = Paths.get(SAVE_DIRECTORY);
        if (!FileUtils.mkdirs(dirPath)) {
            throw new IOException("Failed to create save directory");
        }
        FileUtils.writeFile(dirPath.resolve(SAVE_FILE), getReloadString());
    }


    /**
     * Returns the path of the file where the state is saved.
     *
     * @return the path of the file where the state is saved.
     */
    public Path getSavePath() {
        return Paths.get(SAVE_DIRECTORY, SAVE_FILE);
    }


    @Override
    public String getReloadString() {
        StringBuilder builder = new StringBuilder();
        for (AquaTask task : tasks) {
            builder.append(task.getReloadString());
            builder.append("\n");
        }
        return builder.toString().strip();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            builder.append(String.format("%d. %s\n",
                    i + 1,
                    tasks.get(i).toString()));
        }
        return builder.toString().strip();
    }
}
