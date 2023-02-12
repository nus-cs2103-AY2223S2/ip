package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Represents a class used to store and load user's tasks from Duke chatbot.
 */
public class Storage {
    private String filePath;

    /**
     * Constructor for a Storage object.
     * @param filePath Relative path of taskList.txt file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a TaskList from taskList.txt.
     * @return TaskList containing user's saved tasks.
     * @throws DukeException If file could not be read.
     */
    public TaskList loadData() throws DukeException {
        TaskList taskList = new TaskList();
        File file = new File(this.filePath);
        try {
            if (file.createNewFile()){
                return taskList;
            }
            assert file.exists() : "File was not successfully created.";
        } catch (IOException e) {
            throw new DukeException("Something went wrong when accessing file");
        }
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to read file");
        }
        
        while (sc.hasNextLine()) {
            try {
                Task task;
                String[] line = sc.nextLine().split("\\|");
                if (line[0].equals("T")) {
                    task = new Todo(line[2].strip());
                } else if (line[0].equals("D")) {
                    task = new Deadline(line[2], line[3]);
                } else {
                    task = new Event(line[2], line[3], line[4]);
                }
    
                if (line[1].equals("1")) {
                    task.mark();
                }
    
                taskList.addTask(task);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        return taskList;
    }

    /**
     * Store user's tasks in a taskList.txt file in given relative file path.
     * @param t User's tasks to store.
     * @throws DukeException If unable to save file.
     */
    public void storeData(ArrayList<Task> t) throws DukeException {
        File file = new File(this.filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("Created new file taskList.txt");
            }
            assert file.exists() : "File was not successfully created.";
            FileWriter fw = new FileWriter(file);
            String taskString = "";
            for (int i = 0; i < t.size(); i++){
                taskString += t.get(i).getFileDesc() + "\n";
            }
            fw.write(taskString);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to save to file");
        }
    }
}
