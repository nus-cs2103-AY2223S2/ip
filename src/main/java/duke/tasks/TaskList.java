package duke.tasks;

import java.util.ArrayList;


import duke.exceptions.InvalidTaskDescriptionException;



/**
 * Encapsulates the list of tasks that the user has to complete.
 *
 * @author Tan Matthew Simon Castaneda
 * @version CS2103 AY22/23 Semester 2
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int size;


    /**
     * A constuctor to create an object representing the lists of tasks to be done by the user.
     *
     * @param size Maximum number of tasks that the list will hold.
     */
    public TaskList(int size) {
        this.size = size;
        this.tasks = new ArrayList<>(size);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getCurrentSize() {
        return this.tasks.size();
    }

    /**
     * Adds an already constructed task to the list.
     *
     * @param task Task to be added into the list.
     */
    public void append(Task task) {
        this.tasks.add(task);
    }


    //add task

    /**
     * Creates a task object and adds it into the tasklist. (For todo tasks)
     *
     * @param input User input which describes the task.
     * @throws InvalidTaskDescriptionException If escription of the task does not match specified format.
     */
    public String addTask(String input) throws InvalidTaskDescriptionException {
        String[] newInput = input.split("todo ");
        if (newInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        tasks.add(new Todo(newInput[1]));
        String msg = "";
        msg += "Gotchu fam\n";
        msg += String.format("I've added\n" + tasks.get(this.getCurrentSize() - 1).toString()
                + "\nto all the shit u need to do\n");
        msg += String.format("shag bro now u got %d tasks\n", this.getCurrentSize());
        return msg;
    }


    /**
     * Creates an event object and adds it into the tasklist.
     *
     * @param input User input which describes the event, including its start and end time.
     * @throws InvalidTaskDescriptionException If description of the event does not match specified format.
     */
    public String addEvent(String input) throws InvalidTaskDescriptionException {
        String[] nInput = input.split("/");
        if (nInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        String[] newInput = input.split("/");
        tasks.add(this.getCurrentSize(), new Event(newInput[0], newInput[1], newInput[2]));
        String msg = "";
        msg += "Gotchu fam\n";
        msg += String.format("I've added\n" + tasks.get(this.getCurrentSize() - 1).toString()
                + "\nto all the shit u need to do\n");
        msg += String.format("shag bro now u got %d tasks\n", this.getCurrentSize());
        return msg;
    }


    /**
     * Creates a deadline object and adds it into the tasklist.
     *
     * @param input User input which describes the deadline including its time to be completed.
     * @throws InvalidTaskDescriptionException If description of the deadline does not match specified format.
     */
    public String addDeadline(String input) throws InvalidTaskDescriptionException {
        String[] nInput = input.split("/");
        if (nInput.length < 2) {
            throw new InvalidTaskDescriptionException("brother wake up and put a legit description can");
        }
        String[] newInput = input.split("/");
        tasks.add(this.getCurrentSize(), new Deadline(newInput[0], newInput[1]));
        String msg = "";
        msg += "Gotchu fam\n";
        msg += String.format("I've added\n" + tasks.get(this.getCurrentSize() - 1).toString()
                + "\nto all the shit u need to do\n");
        msg += String.format("shag bro now u got %d tasks\n", this.getCurrentSize());
        return msg;
    }
    

    /**
     * Deletes a task from the tasklist.
     *
     * @param command User input which specifies the index to delete the object (1-based).
     */
    public String deleteTask(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        //duke.tasks.Task temp = duke.tasks.get(taskPointer);
        try {
            Task temp = tasks.get(taskPointer);
            tasks.remove(taskPointer);
            String msg = "";
            msg += "Ok bro I've removed this : \n";
            msg += String.format(temp.toString()
                    + "\nfrom all the shit u need to do\n");
            msg += String.format("Hope you have a better life now\n");
            return msg;
        } catch (IndexOutOfBoundsException e) {
            return "brother look at how long ur list is first then delete lah\n";
        }
    }


    //mark

    /**
     * Method which marks the task specified by the index as done.
     *
     * @param command User input which specifies the index of the task to mark as done (1-based).
     */
    public String mark(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            tasks.get(taskPointer).markAsDone();
            return "marked that for u brother\n";
        } catch (IndexOutOfBoundsException e) {
            return("Wake up and choose a better task to mark\n");
        }
    }
    

    /**
     *  Method which marks the task specified by the index as undone.
     *
     * @param command User input which specifies the index of the task to mark as undone (1-based).
     */
    public String unmark(String[] command) {
        int taskPointer = Integer.parseInt(command[1]) - 1;
        try {
            tasks.get(taskPointer).undoTask();
            return "unmarked that for u u useless prick\n";
        } catch (IndexOutOfBoundsException e) {
            return "Wake up and choose a better task to unmark brother\n";
        }
    }

    /**
     * Prints out a list of tasks with the given words.
     *
     * @param word The word to be searched.
     */

    public String findTask(String word) {
        TaskList taskList = new TaskList(this.size);
        for (Task item : this.getTasks()) {
            if (item.hasString(word)) {
                taskList.append(item);
            }
        }
        return taskList.listTasks();
    }

    //list

    /**
     * Method to list out all the tasks for the users view.
     *
     */
    public String listTasks() {
        String str = "";
        if (this.getCurrentSize() == 0) {
           str += "You got nothing to do brother its time to get a life\n";
        }
        for (int i = 0; i < this.getCurrentSize(); i++) {
            if (this.tasks.get(i) == null) {
                break;
            }
            String str2 = String.format("%d." + tasks.get(i).toString() + "\n", i + 1);
            str += str2;
        }
        return str;

    }



}
