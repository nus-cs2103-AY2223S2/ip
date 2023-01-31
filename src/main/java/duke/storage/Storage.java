package duke.storage;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.exceptions.LoadException;
import duke.tasks.Task;
import duke.tasks.Todos;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class which handles reading and writing data onto a file.
 * This is needed for saving the task list.
 */
public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and loads the data from data.txt.
     * Each line is read and a corresponding task is created.
     * From there, a new task list is constructed.
     * @return a list of tasks.
     * @throws FileNotFoundException
     */
    public TaskList loadFile() throws LoadException {

        ArrayList<Task> loadData;
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            loadData = new ArrayList<>();
            while (s.hasNext()) {
                String[] inputs = s.nextLine().split(" \\| ");

                boolean isDone = inputs[1].equals("1") ? true : false;
                switch(inputs[0]) {
                    case "T":
                        loadData.add(new Todos(isDone, inputs[2]));
                        break;
                    case "D":
                        loadData.add(new Deadlines(isDone, inputs[2], inputs[3]));
                        break;
                    case "E":
                        loadData.add(new Events(isDone, inputs[2], inputs[3], inputs[4]));
                        break;
                }
            }

        } catch (FileNotFoundException err) {
            String[] folders = this.filePath.split("/");

            String dirs = "/" + String.join(
                    "/", Arrays.copyOf(folders, folders.length - 1));
            File folderDir = new File(dirs);

            if (!folderDir.exists()) {
                folderDir.mkdirs();
            }
            File file = new File(this.filePath);
            loadData = new ArrayList<Task>();

        } catch (Exception err) {
            throw new LoadException();
        }

        return new TaskList(loadData);
    }

    /**
     * Dumps the ArrayList dumpFile into data.txt.
     * Each task in a task list is converted into a readable format.
     * The task list is then dumped into the file.
     * @param tasks a list of tasks to be dumped.
     * @throws IOException
     */
    public void dumpFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String txt = "";
        for (Task t: tasks.getStorer()) {
            txt = txt + t.formatText() + "\n";
        }
        fw.write(txt);
        fw.close();
    }

}