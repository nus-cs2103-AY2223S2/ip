package iris;

import iris.exception.IrisException;
import iris.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskStore {
    private final File taskFile;
    public TaskStore() {
        File dir = new File("./data");
        this.taskFile = new File("./data/TaskList.txt");
        try {
            if (!dir.exists()) {
                dir.mkdir();
            }
            if (!this.taskFile.exists()) {
                this.taskFile.createNewFile();
            }
        } catch (IOException e) {
            Ui.output("Something went wrong while creating the task file: " + e.getMessage());
        }
    }

    public void addTask(Task task) {
        try {
            FileWriter fw = new FileWriter(taskFile, true);
            fw.write(task.storageFormat());
            fw.close();
        } catch (IOException e) {
            Ui.output("Something went wrong while adding the task: " + e.getMessage());
        }
    }

    public void updateTasks(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.taskFile);
            fw.write(tasks.storageFormat());
            fw.close();
        } catch (IOException e) {
            Ui.output("Something went wrong while updating the task: " + e.getMessage());
        }
    }


    public void reset() {
        try {
            FileWriter fw = new FileWriter(this.taskFile);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            Ui.output("Something went wrong while resetting the file: " + e.getMessage());
        }
    }

    public TaskList parse() throws IrisException {
        TaskList tasks = new TaskList();
        try {
            try {
                Scanner fileScanner = new Scanner(this.taskFile);
                while (fileScanner.hasNextLine()) {
                    tasks.add(Task.parse(fileScanner.nextLine()));
                }
            } catch (FileNotFoundException e) {
                this.taskFile.createNewFile();
            }
        } catch (IOException e) {
            Ui.output("Something went wrong while reading the task file: " + e.getMessage());
        }

        return tasks;
    }
}
