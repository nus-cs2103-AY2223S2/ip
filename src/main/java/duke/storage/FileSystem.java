package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.InvalidDateTimeException;
import duke.helper.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * FileSystem class that handles the saving and loading of tasks
 */
public class FileSystem {
    private File file;
    private final int DATETIME_LENGTH = 16;
    private final int TYPE_POS = 1;
    private final int IS_MARK_POS = 4;
    private final int DESC_POS = 7;

    /**
     * Constructor for FileSystem class
     *
     * @param relFilePath files's location
     * @throws IOException If file cannot be created
     */
    public FileSystem(String relFilePath) throws IOException {
        String dirPath = relFilePath.split("/")[0];
        File dir = new File(dirPath);
        file = new File(relFilePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Updates the file according to the tasks stored on the list
     *
     * @param taskList the TaskList that contains all the tasks that will be stored in the file
     */
    public void updateFile(TaskList taskList) {
        try {
            file.delete();
            file.createNewFile();
            ArrayList<Task> tasks = taskList.getTasks();

            for (Task t : tasks) {
                appendToFile(t.toString());
            }
        } catch (IOException e) {
            e.toString();
        }
    }

    /**
     * Loads the tasks from the file into the TaskList
     *
     * @return an ArrayList that contains all the tasks from the file
     * @throws FileNotFoundException If the file cannot be found
     */
    public ArrayList<Task> loadFromFile() throws FileNotFoundException, InvalidDateTimeException {
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            char taskType = task.charAt(TYPE_POS);
            boolean isMark = task.charAt(IS_MARK_POS) == 'X';
            String taskDesc = task.substring(DESC_POS);

            if (taskType == 'T') {
                tasks.add(new ToDo(taskDesc, isMark));
            } else if (taskType == 'D') {
                String[] deadlineDesc = taskDesc.split(" \\(by: ");
                String dateTime = deadlineDesc[1].substring(0, DATETIME_LENGTH);
                tasks.add(new Deadline(deadlineDesc[0], dateTime, isMark));
            } else if (taskType == 'E') {
                String[] eventDesc = taskDesc.split(" \\(from: ");
                String[] dateTimeArr = eventDesc[1].split(" to: ");
                String to = dateTimeArr[1].substring(0, DATETIME_LENGTH);
                tasks.add(new Event(eventDesc[0], dateTimeArr[0], to, isMark));
            }
        }
        return tasks;
    }

    /**
     * Adds task to the file storage
     *
     * @param textToAppend task to be added to file storage
     * @throws IOException if file cannot be created or accessed
     */
    public void appendToFile(String textToAppend) throws IOException {
        BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
        fw.write(textToAppend);
        fw.newLine();
        fw.close();
    }
}
