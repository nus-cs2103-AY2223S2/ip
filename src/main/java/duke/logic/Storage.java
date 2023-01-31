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

    protected String filePath;

    /**
     * Constructor for Storage object.
     *
     * @param filePath String containing the file path of data text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
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
                String[] entry = line.split(" ");
                String c = entry[0];
                boolean isDone;
                if (entry[1].equals("true")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                StringBuilder desc = new StringBuilder();
                desc.append(" ");
                for (int i = 2; i < entry.length - 1; i++) {
                    desc.append(entry[i]);
                    desc.append(" ");
                }

                desc.append(entry[entry.length - 1]);
                if (c.equals("todo")) {
                    taskList.addTask(Todo.create(desc.toString(), isDone));
                } else if (c.equals("deadline")) {
                    taskList.addTask(Deadline.create(desc.toString(), isDone));
                } else if (c.equals("event")) {
                    taskList.addTask(Event.create(desc.toString(), isDone));
                }

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
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
            File file = new File("./data/duke.txt");
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < taskList.getSize(); i++) {
                Task t = taskList.getTask(i);
                String entry = t.getType() + " " + t.getIsDone() + " " + t.getDescription();
                if (t instanceof Deadline) {
                    entry += " /by " + ((Deadline) t).getDueDate();
                } else if (t instanceof Event) {
                    entry += " /from " + ((Event) t).getStartTime() + " /to " + ((Event) t).getEndTime();
                }
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (Exception e) {
            throw new DukeException();
        }
    }
}
