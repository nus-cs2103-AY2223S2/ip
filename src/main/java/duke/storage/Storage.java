package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Storage class that manage loading of tasks from file and saving of tasks in file
 */
public class Storage {
    private String filePath;
    private File taskSaved;

    /**
     * Constructor of Storage
     */
    public Storage() {
        this.filePath = "C:/Users/linwe/Documents/TaskSaved.txt";
        try {
            this.taskSaved = new File(this.filePath);
            FileWriter myWriter = new FileWriter(this.filePath);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    
    /**
     * Load tasks from file
     * @return List of tasks
     * @throws IOException If unable to read file
     */
    public List<Task> load() {
        List<Task> taskList = new ArrayList<>();
        try {
            BufferedReader myReader = new BufferedReader(new FileReader(this.taskSaved));
            String line = myReader.readLine();

            while (line != null) {
                String[] str = line.split(" | ");
                String taskType = str[0];
                String isCompleted = str[1];
                String taskDes = str[2];
                Task task = null;

                switch (taskType) {
                case "T":
                    task = new ToDo(taskDes);
                    break;

                case "D":
                    task = new Deadline(taskDes, LocalDate.parse(str[3]));
                    break;

                case "E":
                    task = new Event(taskDes, LocalDate.parse(str[3]), LocalDate.parse(str[4]));
                    break;

                default:
                    assert false: "Unable to load task!";
                    break;
                }
                if (isCompleted.equals("[X]")) {
                    task.markTask();
                }

                taskList.add(task);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.print(e);
        }

        return taskList;

    }

    /**
     * Save the list of tasks in the file
     * @param taskList List of tasks to be saved
     * @throws IOException When tasks cannot be saved
     */
    public void save(TaskList taskList) {
        try {
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(this.taskSaved));
            for (Task task: taskList.getTaskList()) {
                myWriter.write(task.toString() + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("There is an error in saving tasks.");
        }
    }


}
