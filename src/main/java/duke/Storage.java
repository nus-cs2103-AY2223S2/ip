package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
/**
 * Storage deals with loading tasks from the file and saving tasks to the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for Storage.
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks in file to an arraylist of tasks.
     * @return arraylist of tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadContents() throws FileNotFoundException, DukeException {
        File f = new File(this.filePath);
        if (!f.exists()) {
            throw new FileNotFoundException("File does not exist!");
        }
        Scanner sc = new Scanner(f);
        ArrayList<Task> storeTasks = new ArrayList<Task>();
        int numElem = 0;
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            String[] arrOfDetails = currentLine.split("\\|");
            String type = arrOfDetails[0];
            char marker = arrOfDetails[2].charAt(0);
            boolean isMarked = (marker == 'X') ? true : false;
            String desc = arrOfDetails[1];
            assert desc.length() == 0 : "Description of task cannot be empty!";
            switch (type) {
            case "T":
                storeTasks.add(new Todo(desc));
                break;
            case "D":
                LocalDateTime byWhen = LocalDateTime.parse(arrOfDetails[3]);
                storeTasks.add(new Deadline(desc, byWhen));
                break;
            case "E":
                LocalDateTime from = LocalDateTime.parse(arrOfDetails[3]);
                LocalDateTime to = LocalDateTime.parse(arrOfDetails[4]);
                storeTasks.add(new Event(desc, from, to));
                break;
            default:
                assert false;
                throw new DukeException("Loading Error!!");
            }
            if (isMarked) {
                assert numElem >= storeTasks.size() :
                        "Index of current task should not be greater than the size of the task list!";
                storeTasks.get(numElem).markAsDone();
            }
            numElem++;
        }
        return storeTasks;
    }

    /**
     * Saves tasks to file.
     * @param storeTasks current arraylist of tasks
     */
    public void saveTasks(ArrayList<Task> storeTasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");
            fw.close();
            fw = new FileWriter(filePath, true);
            for (Task t: storeTasks) {
                String type = t.getType();
                String content = "";
                switch (type) {
                case "T": // T|desc|X
                    content = String.format("%s|%s|%s", t.getType(), t.getDesc(), t.getStatusIcon());
                    break;
                case "D": //D|desc|X|from
                    Deadline deadlineTask = (Deadline) t;
                    content = String.format("%s|%s|%s|%s", t.getType(),
                            t.getDesc(), t.getStatusIcon(), deadlineTask.getDeadline());
                    break;
                case "E": //D|desc|X|from|to
                    Event eventTask = (Event) t;
                    content = String.format("%s|%s|%s|%s|%s", t.getType(),
                            t.getDesc(), t.getStatusIcon(), eventTask.getFrom(), eventTask.getTo());
                    break;
                default:
                    assert false;
                    throw new DukeException("Saving Error");
                }
                assert content.length() == 0 : "Content to be saved should not be an empty string!";
                fw.write(content + "\n");
            }
            fw.close();
        } catch (IOException e) {
            new DukeException("Error with saving TODO task");
        }
    }
}
