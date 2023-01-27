package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> listOfTasks;
    
    public TaskList(ArrayList<Task> list) {
        listOfTasks = list;
    }

    /**
     * Add a Task to the list given the description
     * 
     * @param words Array of strings to be added to the list
     * @param type The type of task
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
        listOfTasks.add(task);
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Delete a Task from the list given the index
     * 
     * @param num Index of the task to be deleted
     */ 
    public boolean deleteTask(int num) throws IndexOutOfBoundsException {
        Task selectedTask = getTask(num);
        listOfTasks.remove(selectedTask);
        System.out.println("Noted. I've removed this task:\n" + selectedTask);
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Print the list
     */ 
    public void printList() {
        for (int i = 0; i < listOfTasks.size(); i++) {
            System.out.println((i+1) + ". " + listOfTasks.get(i));
        }
    }

    /**
     * Retrieve the Task object from the list given an index
     * 
     * @param num Index of the task to retrieve
     * @return returns a Task object
     */ 
    public Task getTask(int num) throws IndexOutOfBoundsException {
        return listOfTasks.get(num-1);
    }

    /**
     * Return a list of task
     * 
     * @return return a list of task
     */
    public ArrayList<Task> getListOfTask() {
        return listOfTasks;
    }
}
