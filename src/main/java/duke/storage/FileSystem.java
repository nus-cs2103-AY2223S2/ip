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
    private File file;

    /**
     * Constructor for FileSystem class
     *
     * @param relFilePath files's location
     */
    public FileSystem(String relFilePath) {
        String dirPath = relFilePath.split("/")[0];
        File dir = new File(dirPath);
        file = new File(relFilePath);

        try {
            dir.mkdirs();
            file.createNewFile();
        } catch (IOException | SecurityException e) {
            System.out.println(e);
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
            System.out.println(e);
        }
    }

    /**
     * Loads the tasks from the file into the TaskList
     *
     * @return an ArrayList that contains all the tasks from the file
     * @throws FileNotFoundException If the file cannot be found
     */
    public ArrayList<Task> loadFromFile() {
        int typePos = 1;
        int markPos = 4;
        int descPos = 7;
        Scanner scanner = null;
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        while (scanner.hasNextLine()) {
            String task = scanner.nextLine();
            char taskType = task.charAt(typePos);
            boolean isMark = task.charAt(markPos) == 'X';
            String taskDesc = task.substring(descPos);

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
     * @param input Description of the deadline
     * @param isMark Whether the deadline has been marked done
     * @return A deadline stored in the file
     */
    public Deadline createDeadlineFromData(String input, boolean isMark) {
        String bySeparator = " \\(by: ";
        String[] deadlineArr = input.split(bySeparator);
        String deadlineDesc = deadlineArr[0];
        String dateTime = deadlineArr[1];

        int dateTimeLen = dateTime.length() - 1;
        String by = dateTime.substring(0, dateTimeLen);
        return new Deadline(deadlineDesc, by, isMark);
    }

    /**
     * Creates an event based on the data stored
     *
     * @param input Description of the event
     * @param isMark Whether the task has been marked done
     * @return An event stored in the file
     */
    public Event createEventFromData(String input, boolean isMark) {
        String fromSeparator = " \\(from: ";
        String toSeparator = " to: ";

        String[] eventArr = input.split(fromSeparator);
        String eventDesc = eventArr[0];

        String[] dateTimeArr = eventArr[1].split(toSeparator);
        String from = dateTimeArr[0];
        int endingDateTimeLen = dateTimeArr[1].length() - 1;
        String to = dateTimeArr[1].substring(0, endingDateTimeLen);
        return new Event(eventDesc, from, to, isMark);
    }
}

