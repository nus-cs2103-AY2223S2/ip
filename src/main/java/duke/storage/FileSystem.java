package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.helper.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * FileSystem class that handles the saving and loading of tasks
 */
public class FileSystem {
    private static final int POS_TYPE = 1;
    private static final int POS_IS_MARK = 4;
    private static final int POS_DESC = 7;
    private File file;

    /**
     * Constructor for FileSystem class
     *
     * @param relFilePath files's location
     * @throws IOException If file cannot be created
     */
    public FileSystem(String relFilePath) {
        String dirPath = relFilePath.split("/")[0];
        File dir = new File(dirPath);
        file = new File(relFilePath);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
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
    public ArrayList<Task> loadFromFile() {
        Scanner scanner = null;
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            char taskType = task.charAt(POS_TYPE);
            boolean isMark = task.charAt(POS_IS_MARK) == 'X';
            String taskDesc = task.substring(POS_DESC);

            if (taskType == 'T') {
                tasks.add(new ToDo(taskDesc, isMark));
            } else if (taskType == 'D') {
                tasks.add(createDeadlineFromData(taskDesc, isMark));
            } else if (taskType == 'E') {
                tasks.add(createEventFromData(taskDesc, isMark));
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
        assert !textToAppend.equals("") : "Text to append should not be empty";
        BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
        fw.write(textToAppend);
        fw.newLine();
        fw.close();
    }

    /**
     * Creates a deadline based on the data stored
     *
     * @param deadlineDesc Description of the deadline
     * @param isMark Whether the deadline has been marked done
     * @return A deadline stored in the file
     */
    public Deadline createDeadlineFromData(String deadlineDesc, boolean isMark) {
        String[] deadlineArr = deadlineDesc.split(" \\(by: ");
        int dateTimeLen = deadlineArr[1].length() - 1;
        String by = deadlineArr[1].substring(0, dateTimeLen);
        return new Deadline(deadlineArr[0], by, isMark);
    }

    /**
     * Creates an event based on the data stored
     *
     * @param eventDesc Description of the event
     * @param isMark Whether the task has been marked done
     * @return An event stored in the file
     */
    public Event createEventFromData(String eventDesc, boolean isMark) {
        String[] eventArr = eventDesc.split(" \\(from: ");
        String[] dateTimeArr = eventArr[1].split(" to: ");
        int endingDateTimeLen = dateTimeArr[1].length() - 1;
        String to = dateTimeArr[1].substring(0, endingDateTimeLen);
        return new Event(eventArr[0], dateTimeArr[0], to, isMark);
    }
}

