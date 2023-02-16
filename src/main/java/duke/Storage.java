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

    private String fileDir;
    private String filePath;

    /**
     * Constructor for Storage.
     * @param filePath
     */
    public Storage(String fileDir, String filePath) {
        this.fileDir = fileDir;
        this.filePath = filePath;
    }

    /**
     * Loads tasks in file to an arraylist of tasks.
     * @return arraylist of tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadContents() throws IOException, DukeException {
        File folder = new File(this.fileDir);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File f = new File(this.filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (sc.hasNext()) {
            String currentLine = sc.nextLine();
            storeTask(currentLine, taskList);
        }
        return taskList;
    }

    /**
     * Stores current task into current task list
     * @param currentLine saved input
     * @param taskList current task list
     * @throws DukeException when there is an error
     */
    private static void storeTask(String currentLine, ArrayList<Task> taskList) throws DukeException {
        String[] arrOfDetails = currentLine.split("\\|");
        String type = arrOfDetails[0];
        char marker = arrOfDetails[2].charAt(0);
        boolean isMarked = (marker == 'X') ? true : false;
        String desc = arrOfDetails[1];
        assert desc.length() != 0 : "Description of task cannot be empty!";
        switch (type) {
        case "T": // T|desc|X
            taskList.add(new Todo(desc));
            break;
        case "D": //D|desc|X|byWhen
            LocalDateTime byWhen = LocalDateTime.parse(arrOfDetails[3]);
            taskList.add(new Deadline(desc, byWhen));
            break;
        case "E": //D|desc|X|from|to
            LocalDateTime from = LocalDateTime.parse(arrOfDetails[3]);
            LocalDateTime to = LocalDateTime.parse(arrOfDetails[4]);
            taskList.add(new Event(desc, from, to));
            break;
        default:
            assert false;
            throw new DukeException("Loading Error!!");
        }
        if (isMarked) {
            taskList.get(taskList.size() - 1).markAsDone();
        }
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
                String content = getContent(t);
                fw.write(content + "\n");
            }
            fw.close();
        } catch (IOException e) {
            new DukeException("Error with saving TODO task");
        }
    }

    /**
     * Gets content of specified task.
     * @param t specified task
     * @return String of content to be saved
     * @throws DukeException when there is an error.
     */
    private static String getContent(Task t) throws DukeException {
        String type = t.getType();
        String content = "";
        switch (type) {
        case "T": //T|desc|X
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
        assert content.length() != 0 : "Content to be saved should not be an empty string!";
        return content;
    }
}
