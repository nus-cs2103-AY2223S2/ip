package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates new Storage object.
     *
     * @param filePath Path to file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the previous saved file.
     *
     * @return Array of previous tasks.
     */
    public TaskList loadFile() {
        TaskList taskList = null;
        try {
            taskList = new TaskList();
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task prevTask;
                String data = sc.nextLine();
                String[] command = data.split("\\|");
                boolean isDoneInFile = command[1].charAt(0) == 'X';
                switch (command[0]) {
                case "T":
                    prevTask = new Todo(data.substring(6));
                    prevTask.isDone = isDoneInFile;
                    taskList.addTask(prevTask);
                    break;
                case "D":
                    prevTask = new Deadline(command[2].substring(1),
                            command[3].substring(1));
                    prevTask.isDone = isDoneInFile;
                    taskList.addTask(prevTask);
                    break;
                case "E":
                    String[] splitString = command[3].substring(1).
                            split("-");
                    prevTask = new Event(command[2].substring(1),
                            splitString[0],
                            splitString[1].substring(1));
                    prevTask.isDone = isDoneInFile;
                    taskList.addTask(prevTask);
                    break;
                }
            }
        } catch (FileNotFoundException e1) {
            System.out.println("File not found, created new file for you");
            File newFile = new File("data/duke.txt");
            File folder = new File("data/duke.txt".split("/")[0]);
            try {
                folder.mkdir();
                newFile.createNewFile();
            } catch (IOException e) {
                System.out.println("I/O error occurred");
            }
        }
        return taskList;
    }

    /**
     * Appends new tasks to the file.
     *
     * @param task Task to be appended.
     */
    public void appendToFile(Task task) {
        try {
            FileWriter fw = new FileWriter(this.filePath, true);
            String statusOfTask = task.getStatusIcon();
            String descriptionOfTask = task.description;
            String taskType = task.toString().
                    substring(1, 2);
            if (task instanceof Todo) {
                fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "\n");
            } else if (task instanceof Deadline) {
                fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " +
                        ((Deadline) task).by + "\n");
            } else if (task instanceof Event) {
                fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " +
                        ((Event) task).from + "- " + ((Event) task).to + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Updates the file after mark, unmark or delete.
     *
     * @param taskList Array of all tasks.
     */
    public void updateFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (int j = 0; j < taskList.getNumberOfTasks(); j++) {
                Task currentTask = taskList.getTask(j);
                String statusOfTask = currentTask.getStatusIcon();
                String descriptionOfTask = currentTask.description;
                String taskType = currentTask.toString().
                        substring(1, 2);
                if (currentTask instanceof Todo) {
                    fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "\n");
                } else if (currentTask instanceof Deadline) {
                    fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " +
                            ((Deadline) currentTask).by + "\n");
                } else if (currentTask instanceof Event) {
                    fw.write(taskType + "|" + statusOfTask + " | " + descriptionOfTask + "| " +
                            ((Event) currentTask).from + "- " + ((Event) currentTask).to + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
