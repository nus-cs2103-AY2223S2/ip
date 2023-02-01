package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;
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

            char taskType = task.charAt(1);
            boolean isMark = task.charAt(4) == 'X';

            switch (taskType) {
            case 'T':
                tasks.add(new ToDo(task.substring(7), isMark));
                break;
            case 'D':
                String[] deadlineDesc = task.substring(7).split(" /by ");
                tasks.add(new Deadline(deadlineDesc[0], deadlineDesc[1], isMark));
                break;
            case 'E':
                String[] eventDesc = Parser.parseEventDesc(task.substring(7));
                tasks.add(new Event(eventDesc[0], eventDesc[1], eventDesc[2], isMark));
                break;
            default:
                if (isMark) {
                    tasks.get(tasks.size() - 1).setIsDone(true);
                }
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
