package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Loads and stores the TaskList into a hard drive file.
 */
public class Storage {

    private File file;
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new File in the filePath given previously if the file does not already exist.
     * @throws DukeException if file could not be created
     */
    public void checkFileExists() throws DukeException {
        File f = new File(this.filePath);
        this.file = f;
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Unable to open and create file.");
        }
    }

    /**
     * Loads the file with a lists of tasks, from the given filePath
     * and creates a TaskList for each task within the file.
     * @return TaskList which contains tasks from the loaded file.
     * @throws DukeException if file could not be found.
     */
    public TaskList loadFile() throws DukeException {
        try {
            TaskList tasklist = new TaskList();
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {
                String[] task = s.nextLine().split(" \\| ");
                switch (task[0]) {
                    case "T":
                        tasklist.addToDo(task[2]);
                        break;
                    case "D":
                        tasklist.addDeadline(task[2], task[3]);
                        break;
                    case "E":
                        tasklist.addEvent(task[2], task[3], task[4]);
                        break;
                }
                if (task[1].equals("1")) {
                    tasklist.setTaskStatus(tasklist.listSize(), true);
                }
            }
            s.close();
            return tasklist;
        } catch (FileNotFoundException e) {
            throw new DukeException("Could not find file.");
        }
    }

    /**
     * Writes back to the hard disk file path after operations are made on the TaskList.
     * @param tasklist TaskList to update the hard disk tasks.
     * @throws DukeException if file cannot be written to.
     */
    public void writeFile(TaskList tasklist) throws DukeException{
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder str = new StringBuilder();
            for (Task task : tasklist.getTasks()) {
                str.append(task.toFileString());
            }
            fw.write(String.valueOf(str));
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not write to file path");
        }
    }
}
