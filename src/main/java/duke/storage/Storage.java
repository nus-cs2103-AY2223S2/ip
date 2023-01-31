package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.dukeexception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;


public class Storage {
    private File allTasks;
    private File taskFolder;

    public Storage(String filePath, String folderPath) {
        this.allTasks = new File(filePath);
        this.taskFolder = new File(folderPath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> defaultTasks = new ArrayList<>();
        if (!taskFolder.exists()) {
            System.out.println("---The default duke.task.Task Folder is not found, creating data folder...");
            taskFolder.mkdir();
            System.out.println("---Task Folder created successfully");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else if (!allTasks.exists()) {
            System.out.println("The default tasks do not exist, creating default task file...");
            File f = new File(taskFolder, "task.txt");
            try {
                f.createNewFile();
                System.out.printf("---Task File created successfully\n---ready to create tasks\n");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        } else {
            try {
                defaultTasks = loadDefaultTasks(new ArrayList<Task>(), allTasks);
            } catch (FileNotFoundException e) {
                System.out.println("Could not load the default tasks: " + e.getMessage());
            }
            System.out.println("\n\n---Default duke.task.Task List successfully loaded\n\n");
        }
        return defaultTasks;
    }

    private static ArrayList<Task> loadDefaultTasks(ArrayList<Task> tasks, File file) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String[] lineArr = s.nextLine().split("/");
            switch (lineArr[0]) {
            case "D":
                tasks.add(new Deadline(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3]));
                break;
            case "T":
                tasks.add(new Todo(lineArr[1], Integer.parseInt(lineArr[2])));
                break;
            case "E":
                tasks.add(new Event(lineArr[1], Integer.parseInt(lineArr[2]), lineArr[3], lineArr[4]));
                break;
            default:
                continue;
            }
        }
        s.close();
        return tasks;
    }

    public void update(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.allTasks);
        fw.write(tasks.getWriteString());
        fw.close();
    }
}
