package aqua.manager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

import aqua.aquatask.AquaTask;
import aqua.storage.Reloadable;
import aqua.util.FileUtils;

/** Manager of tasks. */
public class TaskManager implements Reloadable {
    private static final String SAVE_DIRECTORY = "SAVE_DATA";
    private static final String SAVE_FILE = "Goshujin-sama you promised to never touch this.txt";

    private final ArrayList<AquaTask> taskList = new ArrayList<>();


    public void add(AquaTask task) {
        taskList.add(task);
    }

    
    public AquaTask mark(int taskNum, boolean isComplete) throws IndexOutOfBoundsException {
        taskList.set(taskNum, taskList.get(taskNum).mark(isComplete));
        return taskList.get(taskNum);
    }


    public AquaTask delete(int taskNum) throws IndexOutOfBoundsException {
        return taskList.remove(taskNum);
    }


    public LinkedHashMap<Integer, AquaTask> filter(String pattern) {
        LinkedHashMap<Integer, AquaTask> taskMap = new LinkedHashMap<>();
        Stream.iterate(0, i -> i + 1)
                .limit(size())
                .filter(i -> taskList.get(i).getName().contains(pattern))
                .forEach(i -> taskMap.put(i + 1, taskList.get(i)));
        return taskMap;
    }


    public int size() {
        return taskList.size();
    }


    public void saveToFile() throws IOException {
        Path dirPath = Paths.get(SAVE_DIRECTORY);
        if (!FileUtils.mkdirs(dirPath)) {
            throw new IOException("Failed to create save directory");
        }
        FileUtils.writeFile(dirPath.resolve(SAVE_FILE), getReloadString());
    }


    public Path getSavePath() {
        return Paths.get(SAVE_DIRECTORY, SAVE_FILE);
    }


    @Override
    public String getReloadString() {
        StringBuilder builder = new StringBuilder();
        for (AquaTask task : taskList) {
            builder.append(task.getReloadString());
            builder.append("\n");
        }
        return builder.toString().strip();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            builder.append(String.format(
                "%d. %s\n",
                i+1,
                taskList.get(i).toString()
            ));
        }
        return builder.toString().strip();
    }
}
