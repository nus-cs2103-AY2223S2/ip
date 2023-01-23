package duke.utilities;

import duke.exceptions.DukeException;
import duke.exceptions.LoadFileException;
import duke.exceptions.SaveFileException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ITask;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 * Represents local storage. A <code>Storage</code> object corresponds to local
 * database
 */
public class Storage {
    private final String _path;

    /**
     * Constructor for Storage
     *
     * @param path the path to local database file
     */
    public Storage(String path) {
        _path = path;
    }

    /**
     * load the tasks from local database
     *
     * @throws DukeException IF error occur during loading of task.
     */
    public ArrayList<ITask> load() throws DukeException {
        File f = new File(_path);

        try {

            Scanner s = new Scanner(f);
            ArrayList<ITask> result = new ArrayList<>();
            while (s.hasNext()) {
                String line = s.nextLine();
                int markIndex = line.indexOf("[X]") + 3;
                int unmarkIndex = line.indexOf("[ ]") + 3;

                if (line.contains("[T]")) {
                    if (line.contains("[X]")) {
                        result.add(new Todo(line.substring(markIndex).trim(), true));
                    } else {
                        result.add(new Todo(line.substring(unmarkIndex).trim(), false));
                    }
                } else if (line.contains("[D]")) {

                    String by = line.substring(line.indexOf("/by:") + 4, line.indexOf("/content:")).trim();
                    if (line.contains("[X]")) {
                        result.add(new Deadline(line.substring(markIndex).trim(), new  Date(Long.parseLong(by)), true));
                    } else {
                        result.add(new Deadline(line.substring(unmarkIndex).trim(), new  Date(Long.parseLong(by)), false));
                    }

                } else if (line.contains("[E]")) {
                    String from = line.substring(line.indexOf("/from:") + 6, line.indexOf("/to:")).trim();
                    String to = line.substring(line.indexOf("/to:") + 4, line.indexOf("/content:")).trim();

                    if (line.contains("[X]")) {
                        result.add(new Event(line.substring(markIndex).trim(),
                                new  Date(Long.parseLong(from)), new  Date(Long.parseLong(to)), true));
                    } else {
                        result.add(new Event(line.substring(unmarkIndex).trim(),
                                new  Date(Long.parseLong(from)), new  Date(Long.parseLong(to)), false));
                    }
                }
            }
            return result;

        } catch (IOException e) {
            throw new LoadFileException(_path, e.getMessage());
        }

    }

    /**
     * save the tasks from local database
     *
     * @param tasks task to save
     * @throws DukeException IF error occur during save of task.
     */
    public void saveAll(ArrayList<ITask> tasks) throws DukeException {
        try {
            File f = new File(_path);
            File dir = new File(f.getParent());
            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    throw new LoadFileException(_path, "Make dir fail");
                }
            }
            if (!f.exists()) {
                if (!f.createNewFile()) {
                    throw new LoadFileException(_path, "Create file fail");
                }
            }
            FileWriter fw = new FileWriter(_path);
            StringBuilder content = new StringBuilder();
            for (ITask t : tasks) {
                content.append(t.toSaveFormat()).append("\n");
            }
            fw.write(content.toString());
            fw.close();
        } catch (IOException e) {

            throw new SaveFileException(_path, e.getMessage());
        }

    }
}
