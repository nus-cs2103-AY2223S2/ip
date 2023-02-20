package tasks;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import exception.DukeException;

/**
 * Represents storage for all current tasks
 */
public class TaskList extends ArrayList<Task>{
    private static String tasksFile = new File("data/duke.txt").getAbsolutePath();
    private static Path tfPath = Paths.get(tasksFile);

    /**
     * Constructor for Tasklist object
     * @throws DukeException If tasks cannot be loaded from harddrive
     */
    public TaskList() throws DukeException {
        super();
        File f = new File(tasksFile);
        if (f.exists()) {
            //load tasks into arrayList
            try{
                //load existing values into TaskList
                ArrayList<String> fileContent = new ArrayList<>(Files.readAllLines(tfPath, StandardCharsets.UTF_8));
                for (int i = 0; i < fileContent.size(); i++) {
                    String currLine = fileContent.get(i);
                    //parse String into two
                    String splitLine[] = currLine.split(" \\| ");
                    //check first index
                    String type = splitLine[0];
                    //switch statement to cover for all 3 types of tasks
                    switch (type){
                    case "T":
                        Todo todo = new Todo(splitLine[2]);
                        this.addTask(todo);
                        //1 means task is marked
                        if (splitLine[1].equals("1")){
                            this.markTask(this.size() - 1);
                        }
                        break;
                    case "D":
                        Deadline deadline = new Deadline(splitLine[2], splitLine[3]);
                        this.addTask(deadline);
                        if (splitLine[1].equals("1")){
                            this.markTask(this.size() - 1);
                        }
                        break;
                    case "E":
                        Event event = new Event(splitLine[2], splitLine[3], splitLine[4]);
                        this.addTask(event);
                        if (splitLine[1].equals("1")){
                            this.markTask(this.size() - 1);
                        }
                        break;
                    }
                }
            } catch(IOException e){
                throw new DukeException("Failed to load tasks from storage");
            }
        }
    }

    /**
     * Returns delete task message.
     * Removes task that is at given index
     * @param index Index of task
     * @return Delete task message
     */
    public void deleteTask(int index){
        Task task = this.remove(index);
    }

    /**
     * Returns add task message.
     * Generates new Todo object and adds it to itself.
     * @param task Task to add
     * @return Add task message
     */
    public void addTask(Task task){
        this.add(task);
    }

    /**
     * Returns mark task message.
     * Marks targeted task as completed.
     * @param index Task index
     * @return Mark task message
     */
    public void markTask(int index){
        Task task = this.get(index);
        task.mark();
    }
    /**
     * Returns unmark task message.
     * Marks targeted task as not complete.
     * @param index Task index
     * @return Unmark task message
     */
    public void unmarkTask(int index){
        Task task = this.get(index);
        task.unmark();
    }
}
