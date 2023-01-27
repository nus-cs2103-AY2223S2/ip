import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Storage class represents a repository that implements CRUD (Create, Read, Update, and Delete)
 * operations to deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Chia Jeremy
 */

public class Storage {

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public ArrayList<String[]> load() {
        ArrayList<String[]> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String[] data = s.nextLine().split(" \\| ");
                tasks.add(data);
            }
        } catch (FileNotFoundException e) {
            Feedback.warning("File not found.");
        }
        return tasks;
    }

    public void add(Task task) {
        try {
            FileWriter fw = new FileWriter(this.file, true);
            String data;
            if (task instanceof Todo) {
                Todo td = (Todo) task;
                data = "T |   | " + td.getDescription();
            } else if (task instanceof Deadline) {
                Deadline dl = (Deadline) task;
                data = "D |   | " + dl.getDescription() + " | " + dl.getDateTime();
            } else {
                Event ev = (Event) task;
                data = "E |   | " + ev.getDescription() + " | " + ev.getStartDT() + " | " + ev.getEndDT();
            }
            fw.write(data + System.lineSeparator());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            Feedback.warning("Fail to add to repository: " + e.getMessage());
        }
    }

    public void mark(int index) {
        List<String> tasks = getSavedTasksAsList();
        StringBuilder sb = new StringBuilder(tasks.get(index));
        sb.setCharAt(4, 'X');
        replaceTextInFile(index, sb.toString());
    }

    public void unmark(int index) {
        List<String> tasks = getSavedTasksAsList();
        StringBuilder sb = new StringBuilder(tasks.get(index));
        sb.setCharAt(4, ' ');
        replaceTextInFile(index, sb.toString());
    }

    public void delete(int index) {
        replaceTextInFile(index, "");
    }

    private void replaceTextInFile(int index, String text) {
        try {
            List<String> tasks = getSavedTasksAsList();
            FileWriter fw = new FileWriter(this.file, false);
            for (int i = 0; i < tasks.size(); i++) {
                if (i != index) {
                    fw.write(tasks.get(i) + System.lineSeparator());
                } else if (i == index && !text.isEmpty()) {
                    fw.write(text + System.lineSeparator());
                }
                fw.flush();
            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getSavedTasksAsList() {
        List<String> savedTasks = new ArrayList<>(100);
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String s = br.readLine();
            while (s != null && !s.isEmpty()) {
                savedTasks.add(s);
                s = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            Feedback.warning("File not found.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return savedTasks;
    }
}