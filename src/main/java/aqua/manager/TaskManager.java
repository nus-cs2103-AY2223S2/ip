package aqua.manager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import aqua.storage.Reloadable;
import aqua.usertask.UserTask;
import aqua.util.DateUtils;
import aqua.util.FileUtils;

/** Manager of tasks. */
public class TaskManager implements Reloadable {
    private static final String SAVE_DIRECTORY = "SAVE_DATA";
    private static final String SAVE_FILE = "Goshujin-sama you promised to never touch this.txt";

    private final ArrayList<UserTask> tasks = new ArrayList<>();


    /**
     * Adds the specified task.
     *
     * @param task - the task to add.
     * @return the task change report.
     */
    public TaskChangeReport add(UserTask task) {
        tasks.add(task);
        return new TaskChangeReport(task, tasks.size());
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
    public UserTask mark(int taskNum, boolean isComplete) throws IndexOutOfBoundsException {
        tasks.set(taskNum, tasks.get(taskNum).mark(isComplete));
        return tasks.get(taskNum);
    }


    /**
     * Deletes the task at the given index.
     *
     * @param taskNum - the index of the task to delete.
     * @return the task change report.
     * @throws IndexOutOfBoundsException if taskNum is out of range.
     */
    public TaskChangeReport delete(int taskNum) throws IndexOutOfBoundsException {
        UserTask task = tasks.remove(taskNum);
        return new TaskChangeReport(task, tasks.size());
    }


    /**
     * Filters tasks that are within the time range specified. Tasks that do
     * not have a start date are classified under unknown.
     *
     * @param start - the starting time of tasks to include.
     * @param end - the ending time of the tasks to include.
     * @return a {@code TaskFilterReport} containing data of the tasks
     *      filtered.
     */
    public TaskFilterReport filterWithin(LocalDateTime start, LocalDateTime end) {
        ArrayList<UserTask> filtered = new ArrayList<>();
        ArrayList<UserTask> unknown = new ArrayList<>();
        for (UserTask task : tasks) {
            if (!task.getEnd().isPresent()) {
                unknown.add(task);
                continue;
            }
            LocalDateTime taskEnd = task.getEnd().get();
            LocalDateTime taskStart = taskEnd;
            if (task.getStart().isPresent()) {
                taskStart = task.getStart().get();
            }
            if (DateUtils.isIntersecting(taskStart, taskEnd, start, end)) {
                filtered.add(task);
            }
        }
        return new TaskFilterReport(filtered, unknown);
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
    public LinkedHashMap<Integer, UserTask> filter(String pattern) {
        LinkedHashMap<Integer, UserTask> taskMap = new LinkedHashMap<>();
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
        for (UserTask task : tasks) {
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
