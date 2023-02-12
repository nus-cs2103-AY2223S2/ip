package sebastian.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import sebastian.exceptions.CannotLoadDataException;
import sebastian.exceptions.CannotWriteDataException;
import sebastian.exceptions.DeadlineFormatMismatchException;
import sebastian.exceptions.EventFormatMismatchException;


/**
 * A class to store task list to hard disk and to retrieve data from the hard disk
 */
public class Storage {
    private final File file;
    public Storage() {
        File dir = new File("data");
        if(!dir.exists()) {
            dir.mkdir();
        }
        this.file = new File("data/SebastianData.txt");
    }
    /**
     * Load data from hard disk and convert the String representation into actual tasks.
     * @return a main.TaskList containing all the tasks.
     */
    public TaskList formTaskListFromData() throws CannotLoadDataException {
        TaskList taskList = new TaskList();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String curLine;
            while ((curLine = br.readLine()) != null) {
                String[] curTask = curLine.split("<>");
                String taskType = curTask[0];
                boolean isCompleted = curTask[1].equals("DONE");
                String taskDescription = curTask[2];
                switch (taskType) {
                case "T":
                    taskList.addTodo(isCompleted, taskDescription);
                    break;
                case "D":
                    taskList.addDeadline(isCompleted, taskDescription, curTask[3]);
                    break;
                case "E":
                    taskList.addEvent(isCompleted, taskDescription, curTask[3], curTask[4]);
                    break;
                default:
                    break;
                }
            }
            return taskList;
        } catch (IOException | DeadlineFormatMismatchException | EventFormatMismatchException e) {
            throw new CannotLoadDataException();
        }
    }

    /**
     * Save tasks in the taskList into the hard disk.
     * @param taskList a TaskList object
     */
    public void writeToDisk(TaskList taskList) throws CannotWriteDataException {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(taskList.formatTaskListForSave());
            fw.flush();
        } catch (IOException e) {
            throw new CannotWriteDataException();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
