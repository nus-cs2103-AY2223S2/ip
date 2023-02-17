package duke;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeDataException;
import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;




/**
 * Deals with loading tasks from the file
 * and saving tasks in the file
 */
public class Storage {
    private String filePath;

    /**
     * Initializes a Storage object
     *
     * @param filePath the file on hard disk
     */
    public Storage(String filePath) {
        this.filePath = filePath;

    }

    /**
     * Loads the data from the filepath specified
     *
     * @return the list storing the stored tasks
     */
    public ArrayList<Task> loadData() {
        ArrayList<Task> list = new ArrayList<>();
        File data = new File(this.filePath);
        try {
            Scanner fileSc = new Scanner(data);
            while (fileSc.hasNextLine()) {
                String fileData = fileSc.nextLine();
                String[] taskData = fileData.split("\\|");
                list.add(readData(taskData));
            }
            fileSc.close();
        } catch (FileNotFoundException e) {
            this.createFile();
        } catch (DukeException e) {
            System.out.println(e);
        }
        return list;
    }

    /**
     * Creates an empty file in a seperate directory
     */
    public void createFile() {
        File dir = new File("./data");
        File newFile = new File(this.filePath);
        assert dir != null;
        dir.mkdir();
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Reads the data from the file
     *
     * @param taskData the string representation of Task
     * @return the Task object
     * @throws DukeDataException
     */
    public Task readData(String[] taskData) throws DukeDataException {
        String taskType = taskData[0];
        String taskStatus = taskData[1];
        String taskInfo = taskData[2].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Task loadTask = null;

        if (taskType.equals("T")) {
            loadTask = new Todo(taskInfo);
        } else if (taskType.equals("D")) {
            LocalDateTime taskTime = LocalDateTime.parse(taskData[3].trim(), formatter);
            loadTask = new Deadline(taskInfo, taskTime);
        } else if (taskType.equals("E")) {
            LocalDateTime taskFrom = LocalDateTime.parse(taskData[3].trim(), formatter);
            LocalDateTime taskTo = LocalDateTime.parse(taskData[4].trim(), formatter);
            loadTask = new Event(taskInfo, taskFrom, taskTo);
        } else {
            throw new DukeDataException();
        }

        assert loadTask != null;
        if (taskStatus.equals("1")) {
            loadTask.changeStatus();
        }
        return loadTask;
    }

    /**
     * Stores the data in the file on the hard disk
     *
     * @param list the list generated in current session
     * @throws IOException
     */
    public void storeData(TaskList list) throws IOException {
        FileWriter writer = new FileWriter(this.filePath, false);
        BufferedWriter buffer = new BufferedWriter(writer);
        for (int i = 1; i <= list.getListLength(); i++) {
            buffer.write(list.getTask(i).writeFile());
            buffer.newLine();
        }
        buffer.close();
        writer.close();
    }
}
