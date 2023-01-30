package duke.storage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

/**
 * Represents storage of saved tasks
 */
public class Storage {

    private String filePath;
    private File f;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (s.hasNext()) {
                // convert text to duke.data.task.Task
                String[] input = s.nextLine().split(" \\| ");
                String type = input[0];
                String mark = input[1];
                Task x = null;
                if (type.equals("T")) {
                    x = new Todo(input[2]);
                } else if (type.equals("D")) {
                    x = new Deadline(input[2], input[3]);
                } else if (type.equals("E")) {
                    x = new Event(input[2], input[3], input[4]);
                }
                if (mark.equals("1")) {
                    x.mark();
                }
                tasks.add(x);
            }
            return tasks;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }

    }

    public void save(String taskListString) throws DukeException {
        try {
            FileWriter fw = new FileWriter("src/main/java/duke/data/duke.txt");
            fw.write(taskListString);
            fw.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }


}
