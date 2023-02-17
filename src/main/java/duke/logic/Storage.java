package duke.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import duke.DukeException;
import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;
import duke.logic.task.Todo;

/**
 * Handle loading and saving of data.
 */
public class Storage {

    protected File filePath;

    /**
     * Constructor for Storage object.
     *
     * @param filePath String containing the file path of data text file.
     */
    public Storage(String filePath) {
        this.filePath = new File(filePath);
    }

    /**
     * Creates the appropriate task given a line of String from data file.
     * @param line line of String from data file.
     * @return Task object created.
     * @throws DukeException If error creating task.
     */
    public Task initialiseTask(String line) throws DukeException {
        String[] entry = line.split(" ");
        String c = entry[0];
        boolean isDone = entry[1].equals("true");
        StringBuilder desc = new StringBuilder(" ");
        for (int i = 2; i < entry.length - 1; i++) {
            desc.append(entry[i]);
            desc.append(" ");
        }

        desc.append(entry[entry.length - 1]);

        Task task = null;
        if (c.equals("todo")) {
            task = Todo.create(desc.toString(), isDone);
        } else if (c.equals("deadline")) {
            task = Deadline.create(desc.toString(), isDone);
        } else if (c.equals("event")) {
            task = Event.create(desc.toString(), isDone);
        }

        return task;
    }

    /**
     * Converts a task into data String form to be saved in data file.
     * @param task Task to be converted and saved.
     * @return String data form of task.
     */
    public String extractTask(Task task) {
        String entry = task.getType() + " " + task.getIsDone() + " " + task.getDescription();
        if (task instanceof Deadline) {
            entry += " /by " + ((Deadline) task).getDueDate();
        } else if (task instanceof Event) {
            entry += " /from " + ((Event) task).getStartTime() + " /to " + ((Event) task).getEndTime();
        }

        return entry;
    }

    /**
     * Creates Tasks based on data text file at filePath and adds into TaskList.
     *
     * @param taskList TaskList which tasks should be loaded into.
     * @throws DukeException If error reading from file.
     */
    public void load(TaskList taskList) throws DukeException {
        try {
            FileReader fileReader = new FileReader(this.filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                taskList.addTask(this.initialiseTask(line));
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("loading error");
            throw new DukeException();
        }
    }

    /**
     * Write tasks from given TaskList onto a data text file at filePath.
     *
     * @param taskList TaskList to write from.
     * @throws DukeException If error writing to file.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < taskList.getSize(); i++) {
                String entry = this.extractTask(taskList.getTask(i));
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
