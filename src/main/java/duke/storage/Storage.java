package duke.storage;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.exception.DukeException;


public class Storage {
    protected final String SAVE_DIR = "./data";
    protected final String SAVE_NAME = "/duke.txt";

    public void loadFromSave(TaskList taskList) throws DukeException {
        File file = new File(SAVE_DIR + SAVE_NAME);
        ArrayList<Task> taskArray = new ArrayList<>();
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String nextLine = scanner.nextLine();
                    taskArray.add(Task.decode(nextLine));
                }
                scanner.close();
                taskList.loadTasks(taskArray);
//                System.out.println("Retrieved saved tasks from storage");
            } catch (IOException e) {
                throw new DukeException("Error when loading from save");
            }
        }
    }

    public void saveTaskList(TaskList taskList) {
        try {
            boolean dataDir = new File(SAVE_DIR).mkdirs();
            File txtFile = new File(SAVE_DIR + SAVE_NAME);
            FileWriter myWriter = new FileWriter(SAVE_DIR + SAVE_NAME);
            myWriter.write(taskList.encode());
            myWriter.close();
//            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
//            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
