package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/*
 * Contains the task list and it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;
    
    /**
     * Constructs a new TaskList instance
     * 
     * @param list List of the tasks
     */
    public TaskList(ArrayList<Task> list) {
        tasks = list;
    }

    /**
     * Adds a Task to the list given the description
     * 
     * @param words Description given by user input
     * @param type The type of task
     * @return true if task has been added successfully, else false
     */ 
    public boolean addTask(String[] words, String type) {
        Task task;
        if (words.length == 0) {
            System.out.println("The description of " + type + " cannot be empty. Please try again");
            return false;
        }
        if (type.equals("TODO")) {
            task = new Todo(String.join(" ", words ));
        } else {
            String description;
            if (type.equals("DEADLINE")) {
                int indexForBy = Parser.getIndexOfWord(words, "/by");
                if (indexForBy == 0) {
                    System.out.println("The description of " + type + " cannot be empty. Please try again");
                    return false;
                }
                LocalDateTime dateTimeBy = DateTime.getDateTime(words,indexForBy);
                if (dateTimeBy == null) {
                    System.out.println("Please enter in this format {description} /by DD/MM/YYYY HHMM. Try again");
                    return false;
                }
                description = String.join(" ",Arrays.copyOfRange(words, 0, indexForBy));
                task = new Deadline(description,DateTime.getDateTime(words,indexForBy));
            }
            else {
                int indexForFrom = Parser.getIndexOfWord(words, "/from");
                if (indexForFrom == 0) {
                    System.out.println("The description of " + type + " cannot be empty. Please try again");
                    return false;
                }
                int indexForTo = Parser.getIndexOfWord(words, "/to");

                if (DateTime.getDateTime(words,indexForFrom) == null || DateTime.getDateTime(words,indexForTo) == null) {
                    System.out.println("Please enter in this format {description} /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM. Try again");
                    return false;
                }

                LocalDateTime dateTimeFrom = DateTime.getDateTime(words,indexForFrom);
                LocalDateTime dateTimeTo = DateTime.getDateTime(words,indexForTo);
                description = String.join(" ",Arrays.copyOfRange(words, 0, indexForFrom));
                task = new Event(description,dateTimeFrom,dateTimeTo);
            }

        }
        tasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Deletes a Task from the list given the index
     * 
     * @param num Index of the task to be deleted
     * @return true if task has been deleted, else false
     * @throws IndexOutOfBoundsExceptio If num > listOfTask.size()
     */ 
    public boolean deleteTask(int num) throws IndexOutOfBoundsException {
        Task selectedTask = getTask(num);
        tasks.remove(selectedTask);
        System.out.println("Noted. I've removed this task:\n" + selectedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Prints the list
     */ 
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
    }

    /**
     * Retrieves the Task object from the list given an index
     * 
     * @param num Index of the task to retrieve
     * @return returns a Task object
     */ 
    public Task getTask(int num) throws IndexOutOfBoundsException {
        return tasks.get(num-1);
    }

    /**
     * Returns a list of task
     * 
     * @return return a list of task
     */
    public ArrayList<Task> getListOfTask() {
        return tasks;
    }
}
