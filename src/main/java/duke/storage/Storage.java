package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.exceptions.LoadException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.Todos;

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
     * @return A list of tasks.
     * @throws FileNotFoundException If file is not found.
     */
    public TaskList loadFile() throws LoadException {

        ArrayList<Task> taskList;
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            taskList = new ArrayList<>();
            while (s.hasNext()) {
                String[] inputs = s.nextLine().split(" \\| ");

                boolean isDone = inputs[1].equals("1") ? true : false;
                switch(inputs[0]) {
                case "T":
                    taskList.add(new Todos(isDone, inputs[2]));
                    break;
                case "D":
                    taskList.add(new Deadlines(isDone, inputs[2], inputs[3]));
                    break;
                case "E":
                    taskList.add(new Events(isDone, inputs[2], inputs[3], inputs[4]));
                    break;
                default:
                    throw new LoadException();

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
            taskList = new ArrayList<Task>();

        } catch (Exception err) {
            throw new LoadException();
        }

        return new TaskList(taskList);
    }

    /**
     * Dumps the ArrayList dumpFile into data.txt.
     * Each task in a task list is converted into a readable format.
     * The task list is then dumped into the file.
     * @param tasks A list of tasks to be dumped.
     * @throws IOException If an error is encountered while accessing information.
     */
    public void dumpFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String text = tasks.getStorer()
                .stream()
                .map(t -> t.formatText() + "\n")
                .reduce("", (x, y) -> x + y);

        fw.write(text);
        fw.close();
    }

}
