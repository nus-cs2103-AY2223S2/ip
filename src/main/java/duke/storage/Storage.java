package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Storage class implements a repository that deals with the
 * loading of tasks from the file and saving tasks in the file.
 *
 * @author Chia Jeremy
 */
public class Storage {

    private final File file;

    /**
     * Class constructor for storage.
     *
     * @param filePath the location of the storage file
     */
    public Storage(String filePath) {
        assert pathNotEmpty(filePath);
        this.file = new File(filePath);
    }

    /**
     * Returns the saved tasks as a list.
     *
     * @return the saved tasks as a list
     */
    public List<String> load() {
        return getSavedTasksAsList();
    }

    /**
     * Adds the task into storage.
     *
     * @param tasks the tasks to be added into storage
     */
    public void add(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (Task t : tasks) {
                String data = processTaskToData(t);
                fw.write(data + System.lineSeparator());
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to add to storage.");
        }
    }

    /**
     * Mark the task.
     *
     * @param index the index of the task
     */
    public void mark(int index) {
        int markIndex = 4;
        char newValue = 'X';
        String updateLine = getUpdatedLine(index, markIndex, newValue);
        replaceLineInFile(index, updateLine);
    }

    /**
     * Unmark the task.
     *
     * @param index the index of the task
     */
    public void unmark(int index) {
        int markIndex = 4;
        char newValue = ' ';
        String updateLine = getUpdatedLine(index, markIndex, newValue);
        replaceLineInFile(index, updateLine);
    }

    /**
     * Delete the task.
     *
     * @param index the index of the task
     */
    public void delete(int index) {
        replaceLineInFile(index, "");
    }

    private Boolean pathNotEmpty(String path) {
        return !path.isEmpty();
    }

    private List<String> getSavedTasksAsList() {
        List<String> savedTaskList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String s = br.readLine();
            while (s != null && !s.isEmpty()) {
                savedTaskList.add(s);
                s = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedTaskList;
    }

    private void replaceLineInFile(int index, String text) {
        try {
            List<String> tasks = getSavedTasksAsList();
            FileWriter fw = new FileWriter(this.file, false);
            for (int i = 0; i < tasks.size(); i++) {
                if (i != index) { // not the line we want to replace
                    fw.write(tasks.get(i) + System.lineSeparator());
                } else if (!text.isEmpty()) { // only replace line if is not empty, else delete line
                    fw.write(text + System.lineSeparator());
                }
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUpdatedLine(int lineToUpdate, int updateIndex, char newValue) {
        List<String> tasks = getSavedTasksAsList();
        StringBuilder sb = new StringBuilder(tasks.get(lineToUpdate));
        sb.setCharAt(updateIndex, newValue);
        return sb.toString();
    }

    private String processTaskToData(Task task) {
        String data;
        if (task instanceof Todo) {
            Todo td = (Todo) task;
            data = "T |   | " + td.getDescription();
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            data = "D |   | " + dl.getDescription() + " | " + dl.getDateTime();
        } else {
            Event evt = (Event) task;
            data = "E |   | " + evt.getDescription() + " | " + evt.getStartDt() + " | " + evt.getEndDt();
        }
        return data;
    }
}
