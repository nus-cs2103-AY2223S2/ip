package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.exceptions.DukeException;
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
    private static final String DELIMITER = ";;";
    private static final int DELIMITER_COUNT = 10;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and loads a task list data from data.txt, given its index.
     * Each line is read and a corresponding task is created.
     * From there, a new task list is constructed.
     * @param index The index of the file to load.
     * @return A list of tasks.
     * @throws FileNotFoundException If file is not found.
     */
    public TaskList loadFile(int index) throws LoadException {

        ArrayList<Task> taskList;
        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            taskList = new ArrayList<>();
            String nextLine = "";

            while (s.hasNext() && index > 0) {
                nextLine = s.nextLine();
                if (nextLine.equals(DELIMITER)) {
                    index--;
                }
            }
            nextLine = "";
            while (!nextLine.equals(DELIMITER)) {
                nextLine = s.nextLine();
                if (nextLine.equals(DELIMITER)) {
                    break;
                }
                System.out.println(nextLine);
                String[] inputs = nextLine.split(" \\| ");
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
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException error) {
                    throw new LoadException();
                }

            }

            taskList = new ArrayList<Task>();

        } catch (Exception err) {
            String[] folders = this.filePath.split("/");
            String dirs = "/" + String.join(
                    "/", Arrays.copyOf(folders, folders.length - 1));
            File folderDir = new File(dirs);

            if (!folderDir.exists()) {
                folderDir.mkdirs();
            }
            File file = new File(this.filePath);
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException error) {
                    throw new LoadException();
                }
            }

            throw new LoadException();
        }
        return new TaskList(taskList);
    }

    /**
     * Dumps the ArrayList dumpFile into data.txt.
     * Each task in a task list is converted into a readable format.
     * The task list is then dumped into the file.
     * @param tasks A list of tasks to be dumped.
     * @throws DukeException If an error is encountered while accessing information.
     */
    public void dumpFile(TaskList tasks) throws DukeException {

        try {
            Stream<String> log = readAll(0);

            FileWriter fw = new FileWriter(this.filePath);
            String text = tasks.getStorer()
                    .stream()
                    .map(t -> t.formatText() + "\n")
                    .reduce("", (x, y) -> x + y)
                    + String.format("%s\n", DELIMITER);

            fw.write(text);
            fw.close();

            writeAll(log);
        } catch (Exception err) {

            throw new LoadException();
        }

    }

    /**
     * Deletes all tasks before a task at a given index.
     * @param index Index of a task before which all tasks are deleted.
     * @throws DukeException If an error is encountered while accessing information.
     */
    public void deleteBefore(int index) throws DukeException {
        try {
            Stream<String> tasks = readAll(index);
            writeAll(tasks, false);

        } catch (Exception err) {
            throw new LoadException();
        }

    }

    /**
     * Reads all tasks in text form after the appropriate index.
     * @param index An index after which all tasks will be read.
     * @return A stream of strings.
     * @throws FileNotFoundException If the file is not found.
     */
    private Stream<String> readAll(int index) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(this.filePath));
        ArrayList<String> arr = new ArrayList<>();
        int delimiterCount = DELIMITER_COUNT;
        int count = 0;

        while (sc.hasNextLine() && delimiterCount > 0) {
            String line = sc.nextLine();
            if (count >= index) {
                arr.add(line);
            }
            if (line.equals(DELIMITER)) {
                count++;
                delimiterCount--;
            }

        }
        return arr.stream();
    }



    private void writeAll(Stream<String> log, boolean append) throws IOException {

        FileWriter fwriter = new FileWriter(this.filePath, append);
        log.forEach(x -> {
            try {
                fwriter.write(x + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        fwriter.close();
    }

    private void writeAll(Stream<String> log) throws IOException {

        writeAll(log, true);
    }

}
