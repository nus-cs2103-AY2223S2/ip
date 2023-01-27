import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStore {
    private File taskFile;
    public TaskStore() {
        this.taskFile = new File("./data/TaskList.txt");
        if (!this.taskFile.exists()) {
            try {
                this.taskFile.createNewFile();
            } catch (IOException e) {
                Ui.output("Something went wrong while creating the task file");
            }
        }
    }

    public void addTask(Task task) {
        try {
            FileWriter fw = new FileWriter(taskFile, true);
            fw.write(task.toString());
            fw.close();
        } catch (IOException e) {
            Ui.output("Something went wrong while adding the task." + e.getMessage());
        }
    }

    public void updateFile(String newText) {
        try {
            FileWriter fw = new FileWriter(this.taskFile);
            fw.write(newText);
            fw.close();
        } catch (IOException e) {
            Ui.output("Something went wrong while updating the task." + e.getMessage());
        }
    }

    public TaskList parse(){
        Ui.output("parsing the file");
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
            Ui.output("Something went wrong while reading the task file." + e.getMessage());
        }

        return tasks;
    }
}
