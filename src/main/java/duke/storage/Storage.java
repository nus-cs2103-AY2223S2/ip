package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;



/**
 * Class of Storage that saves and loads the given tasks by the user.
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the data of the stored tasks from a textfile.
     *
     * @return ArrayList<> - Returns the Task arraylist that is loaded from the textfile.
     * @throws DukeException - Error of the filed not being found.
     */
    public TaskList load() throws DukeException {
        //@@author pzaiming-reused
        //Reused from https://github.com/RyanQiu1
        // with minor modifications
        TaskList tasklst = new TaskList();
        try {
            File file = new File(this.filepath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split("\\] ");
                String[] lineType = lineArr[0].split("\\]");
                Task task = new Task(lineType[0].substring(4),
                        lineType[1].substring(1), lineArr[1]);
                tasklst.addTask(task);
            }
            scanner.close();
            return tasklst;
        } catch (FileNotFoundException e) {
            throw new DukeException("The loading of file \"duke.txt\" is not found!");
        }
    }

    /**
     * Updates the textfile based on the current commands inputed by the user.
     */
    public void updateStorage(TaskList taskList) {
        // create the directory if it is not found
        String directoryPath = "./data";
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            String res = taskList.getTasksString();
            FileWriter myWriter = new FileWriter(this.filepath);
            myWriter.write(res);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred updating the hard disk.");
            e.printStackTrace();
        }
    }
}
