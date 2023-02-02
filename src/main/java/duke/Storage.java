package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    protected File logFile;

    /**
     * Creates a storage - an abstraction to maintain the logfile
     *
     * @param parent The location of the direction of the child file
     * @param child The name of the child file
     */
    public Storage (String parent, String child) throws IOException {
        logFile = new File(parent, child);
        if (!logFile.exists()) {
            File parentFolder = new File(parent);
            parentFolder.mkdirs();
            logFile.createNewFile();
        }
    }

    /**
     * Retrieves the contents of the log file
     *
     * @return A Linked list containing the contents of the log file
     */
    public LinkedList<String> retrieveContents() throws FileNotFoundException {
        LinkedList<String> contents = new LinkedList<>();
        Scanner sc = new Scanner(logFile);
        while (sc.hasNext()) {
            contents.add(sc.nextLine());
        }
        return contents;
    }

    /**
     * Edit the file to ensure it is up-to-date
     *
     * @param tasks TaskList used to update the log file
     */
    public void update(LinkedList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(logFile);
        for (Task item : tasks) {
            fw.write(item.toString() + "\n");
        }
        fw.close();
    }
}
