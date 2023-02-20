package duke;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Storage class
 */
public class Storage {
    String filePath;
    ArrayList<Task> loadedTasks = new ArrayList<>();

    /**
     * Constructor for instantiating a Storage object
     * @param filePath path of file to save the storage in
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private Task createTask(String data) throws DukeException{
        String[] dataArr = data.split(Task.DIVIDER);
        String taskType = dataArr[0];
        String completionStatus = dataArr[1];
        String priority = dataArr[2];
        String description = dataArr[3];
        Task task;

        switch (taskType) {
            case "[T]":
                task = new Todo(description);
                break;
            case "[D]":
                String dueDateString = dataArr[4];
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate dueDate = LocalDate.parse(dueDateString, dateFormatter);
                task = new Deadline(description, dueDate);
                break;
            case "[E]":
                String from = dataArr[4];
                String to = dataArr[5];
                task = new Event(description, from, to);
                break;
            default:
                throw new DukeException("Unknown task type found in data.txt file");
        }
        if (completionStatus.equals("X")) {
            task.mark();
        }
        task.assignPriority(priority);
        return task;
    }

    private void createFile() throws DukeException {
        try {
            File data = new File("./data");
            File f_new = new File(filePath);
            data.mkdir();
            f_new.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error creating file");
        }
    }

    /**
     * Loads the file at the specified file path into the storage object
     * @return ArrayList an arraylist of tasks loaded from the file
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String data = sc.nextLine();
                    System.out.println(data);
                    Task taskToAdd = createTask(data);
                    loadedTasks.add(taskToAdd);
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found");
            }
        } else {
            createFile();
        }
        return this.loadedTasks;
    }

    /**
     * Takes the input task list and updates the task list in the storage
     * @param tasks updated task list
     * @throws DukeException
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            File f = new File(filePath);
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(tasks.toStorageData());
            fw.close();
        } catch (IOException e) {
            System.out.println("error writing into file");
        }
    }
}