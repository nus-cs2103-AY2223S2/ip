package duke.storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final String DEFAULT_PATH = "data/duke.txt";

    private Path filePath;

    private String currRelativeFilePath;

    private static String DATE_FORMAT = "dd MMM yyyy";

    private static int MARK_INDEX = 10;

    private static int TYPE_INDEX = 7;

    private static int DESC_INDEX = 13;

    public Storage() {
        try {
            currRelativeFilePath = new File(".").getCanonicalPath();
            filePath = Paths.get(currRelativeFilePath + '/' + DEFAULT_PATH);
            Files.createDirectories(filePath.getParent());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads data stored in duke.txt file and returns a list of tasks.
     *
     * @return a list of task
     */
    public List<Task> loadData() {
        List<Task> list = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                char type = line.charAt(TYPE_INDEX);
                int hashtagIndex = line.indexOf('#');
                int numAsterisks = line.length() - line.replace("*", "").length();
                Task task = createTask(line, type, hashtagIndex);
                setPriority(task, numAsterisks);
                markTask(task, line);
                setTags(task, line, hashtagIndex);
                list.add(task);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            return list;
        }
    }

    /**
     * Sets the tags of the tasks loaded.
     *
     * @param task The task loaded
     * @param line The string saved in duke.txt
     * @param hashtagIndex The first index of the '#' character
     */
    public void setTags(Task task, String line, int hashtagIndex) {
        if (hashtagIndex != -1) {
            String[] tags = line.substring(hashtagIndex).split(" ");
            List<String> tagsArr = new ArrayList<>();
            for (int i = 0; i < tags.length; i++) {
                tagsArr.add(tags[i].substring(1));
            }
            task.setAllTags(tagsArr);
        }
    }

    /**
     * Marks the task.
     *
     * @param task The task to be marked
     * @param line The string saved in duke.txt
     */
    public void markTask(Task task, String line) {
        if (line.charAt(MARK_INDEX) == 'X') {
            task.mark();
        }
    }

    /**
     * Sets the priority of the task according to the number of asterisks in string.
     *
     * @param task The task to set priority
     * @param numAsterisks The number of asterisks
     */
    public void setPriority(Task task, int numAsterisks) {
        if (numAsterisks == 3) {
            task.setPriority("high");
        } else if (numAsterisks == 2) {
            task.setPriority("medium");
        } else if (numAsterisks == 1) {
            task.setPriority("low");
        } else {
            task.setPriority("");
        }
    }

    /**
     * Creates and returns a Task object.
     *
     * @param line The string saved in duke.txt
     * @param type The type of task
     * @param hashtagIndex The first index of the '#' character
     * @return
     */
    public Task createTask(String line, char type, int hashtagIndex) {
        if (type == 'T') {
            String desc;
            if (hashtagIndex == -1) {
                desc = line.substring(DESC_INDEX);
            } else {
                desc = line.substring(DESC_INDEX, hashtagIndex - 1);
            }
            return new Todo(desc);
        } else if (type == 'D') {
            return getDeadlineToLoad(line);
        } else {
            return getEventToLoad(line);
        }
    }

    /**
     * Creates and returns a deadline object to be loaded.
     *
     * @param line The string savd in duke.txt
     * @return The deadline object to be loaded
     */
    public Task getDeadlineToLoad(String line) {
        int hashtagIndex = line.indexOf('#');
        String sub;
        if (hashtagIndex == -1) {
            sub = line.substring(DESC_INDEX);
        } else {
            sub = line.substring(DESC_INDEX, hashtagIndex - 1);
        }
        int openBraceIndex = sub.indexOf('(');
        int closeBraceIndex = sub.indexOf(')');
        String date = sub.substring(openBraceIndex + 5, closeBraceIndex);
        LocalDate ld = LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        String desc = sub.substring(0, openBraceIndex - 1);
        return new Deadline(ld, desc);
    }

    /**
     * Creates and returns an event object to be loaded.
     *
     * @param line The string savd in duke.txt
     * @return The event object to be loaded
     */
    public Task getEventToLoad(String line) {
        int hashtagIndex = line.indexOf('#');
        String sub;
        if (hashtagIndex == -1) {
            sub = line.substring(DESC_INDEX);
        } else {
            sub = line.substring(DESC_INDEX, hashtagIndex - 1);
        }
        String[] segments = sub.split("from: ", 2);
        String desc = segments[0].substring(0, segments[0].length() - 2);
        String[] dateTime = segments[1].split(" to: ", 2);
        String start = dateTime[0];
        String end = dateTime[1].substring(0, dateTime[1].length() - 2);
        LocalDate sld = LocalDate.parse(start, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate eld = LocalDate.parse(end, DateTimeFormatter.ofPattern(DATE_FORMAT));
        return new Event(sld, eld, desc);
    }

    /**
     * Saves the current TaskList data to the duke.txt file.
     *
     * @param taskList The TaskList to be saved
     */
    public void save(TaskList taskList) {
        String txt = "";
        for (int i = 0; i < taskList.getNumTasks(); i++) {
            txt += taskList.getTask(i) + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter(DEFAULT_PATH);
            fileWriter.write(txt);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
