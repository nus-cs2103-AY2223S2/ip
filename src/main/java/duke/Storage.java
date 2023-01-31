package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Storage {
    public File log;
    public Parser p;

    /**
     * Creates a storage - an abstraction to maintain the logfile
     *
     * @param parent The location of the direction of the child file
     * @param child The name of the child file
     */
    public Storage (String parent, String child) {
        log = new File(parent, child);
        if (!log.exists()) {
            try {
                File parentFolder = new File(parent);
                parentFolder.mkdirs();
                log.createNewFile();
            } catch (IOException e) {
                System.out.println(String.format("Error: Unable to create %s file in %s directory", child, parent));
            }
        }
    }

    /**
     * Edit the file to ensure it is up-to-date
     *
     * @param tasks TaskList used to update the log file
     */
    public void update(LinkedList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(log);
            for (Task item : tasks) {
                fw.write(item.toLog(p) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error: No permissions to read/write " + log.getAbsoluteFile());
        }
    }
}
