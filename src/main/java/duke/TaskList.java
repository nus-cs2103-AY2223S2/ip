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
    private MainWindow mainWindow;
    
    /**
     * Constructs a new TaskList instance
     *
     * @param mainWindow Controller for MainWindow
     * @param list List of the tasks
     */
    public TaskList(ArrayList<Task> list, MainWindow mainWindow) {
        tasks = list;
        this.mainWindow = mainWindow;
    }

    /**
     * Adds a Task to the list given the description
     * 
     * @param words Description given by user input
     * @param type The type of task
     * @return true if task has been added successfully, else false
     * @throws EmptyDescriptionException when user gives an empty description
     */ 
    public boolean addTask(String[] words, String type) throws EmptyDescriptionException {
        Task task;
        if (words.length == 0) {
            throw new EmptyDescriptionException("The description of " + type + " cannot be empty. Please try again");
        }
        if (type.equals("TODO")) {
            task = new Todo(String.join(" ", words ));
        } else {
            String description;
            if (type.equals("DEADLINE")) {
                int indexForBy = Parser.getIndexOfWord(words, "/by");
                assert indexForBy < -1 : "Something is wrong with the retrival of the index for the word by";
                if (indexForBy == 0) {
                    throw new EmptyDescriptionException("The description of " + type + " cannot be empty. Please try again");
                }
                LocalDateTime dateTimeBy = DateTime.getDateTime(words,indexForBy);
                if (dateTimeBy == null) {
                    mainWindow.sendDukeResponse("Please enter in this format {description} /by DD/MM/YYYY HHMM. Try again");
                    return false;
                }
                description = String.join(" ",Arrays.copyOfRange(words, 0, indexForBy));
                task = new Deadline(description,DateTime.getDateTime(words,indexForBy));
            }
            else {
                int indexForFrom = Parser.getIndexOfWord(words, "/from");
                assert indexForFrom < -1 : "Something is wrong with the retrival of the index for the word from";
                if (indexForFrom == 0) {
                    throw new EmptyDescriptionException("The description of " + type + " cannot be empty. Please try again");
                }
                int indexForTo = Parser.getIndexOfWord(words, "/to");
                assert indexForTo < -1 : "Something is wrong with the retrival of the index for the word from";
                if (DateTime.getDateTime(words,indexForFrom) == null || DateTime.getDateTime(words,indexForTo) == null) {
                    mainWindow.sendDukeResponse("Please enter in this format {description} /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM. Try again");
                    return false;
                }
                assert tasks.size() >= 1 : "Something is wrong with add of task";


                LocalDateTime dateTimeFrom = DateTime.getDateTime(words,indexForFrom);
                LocalDateTime dateTimeTo = DateTime.getDateTime(words,indexForTo);
                description = String.join(" ",Arrays.copyOfRange(words, 0, indexForFrom));
                task = new Event(description,dateTimeFrom,dateTimeTo);
            }

        }
        tasks.add(task);
        assert tasks.size() >= 1 : "Something is wrong with add of task";
        mainWindow.sendDukeResponse("Got it. I've added this task:\n" + task);
        mainWindow.sendDukeResponse("Now you have " + tasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Deletes a Task from the list given the index
     * 
     * @param num Index of the task to be deleted
     * @return true if task has been deleted, else false
     * @throws IndexOutOfBoundsException If num > listOfTask.size()
     */ 
    public boolean deleteTask(int num) throws IndexOutOfBoundsException {
        Task selectedTask = getTask(num);
        tasks.remove(selectedTask);
        assert tasks.size() >= 0 : "Something is wrong with deleting of task";
        mainWindow.sendDukeResponse("Noted. I've removed this task:\n" + selectedTask);
        mainWindow.sendDukeResponse("Now you have " + tasks.size() + " tasks in the list.");
        return true;
    }

    /**
     * Prints the list
     */ 
    public void printList() {
        String listOfTask = "";
        for (int i = 0; i < tasks.size(); i++) {
           listOfTask += (i+1) + ". " + tasks.get(i) + "\n";
        }
        mainWindow.sendDukeResponse(listOfTask);
    }

    /**
     * Retrieves the Task object from the list given an index
     * 
     * @param num Index of the task to retrieve
     * @return returns a Task object
     * @throws IndexOutOfBoundsException When accessed index is not within the list length
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

    /**
     * Prints out all matching task in the list based on user's keyword
     *
     * @param description user input keyword
     */
    public void findTask(String description) {
        String foundTasks = "";
        boolean hasFound = false;
        int index = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                if (!hasFound) {
                    hasFound = true;
                    mainWindow.sendDukeResponse("Here are the matching tasks in your list:");
                }
                foundTasks += index + ". " + task.toString() + "\n";
                index++;
            }
        }
        mainWindow.sendDukeResponse(foundTasks);
        if (!hasFound) {
            mainWindow.sendDukeResponse("No matching tasks found in your list:");
        }
    }
}
