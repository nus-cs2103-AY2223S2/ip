package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> init;
    private String filePath;

    private static final int INDEX_DEADLINE_BY = "by:".length() + 1;
    private static final int INDEX_EVENT_FROM = "from:".length() + 1;
    private static final int INDEX_EVENT_TO = "to:".length() + 1;

    /**
     * Parameterized constructor to create a Storage object
     * @param filePath the path of the file to be read from and written to
     */
    public Storage(String filePath) {
        this.init = new ArrayList<>();
        this.filePath = filePath;
    }

    /**
     * Stores the list of tasks into a specified file
     * @param taskList the TaskList to be stored into the file
     */
    public void store(TaskList taskList) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses file input
     * @param s the String containing a single line from the file
     */
    private void parse(String s) {
        String[] arr = s.split("/");
        char eventType = arr[0].charAt(0);
        boolean isDone = (arr[1].charAt(0) == '1');
        String description = arr[2].trim();
        if (eventType == 'T') {
            Todo t = new Todo(description, isDone);
            init.add(t);
        } else if (eventType == 'D') {
            String by = arr[3].substring(INDEX_DEADLINE_BY).trim();
            Deadline d = new Deadline(description, isDone, by);
            init.add(d);
        } else if (eventType == 'E') {
            String from = arr[3].substring(INDEX_EVENT_FROM).trim();
            String to = arr[4].substring(INDEX_EVENT_TO).trim();
            Event e = new Event(description, isDone, from, to);
            init.add(e);
        }
    }

    /**
     * Returns a list of previously saved tasks from the file
     * @return a list containing the saved tasks
     * @throws DukeException if the file contains no tasks
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                parse(s);
            }
            sc.close();

            if (init.isEmpty()) {
                throw new DukeException("No saved tasks to load");
            }

            return init;
        } catch (FileNotFoundException e) {
            try {
                File f = new File(filePath);
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return init;
    }
}
