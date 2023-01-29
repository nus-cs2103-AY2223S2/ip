package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code TaskList} class encapsulates a list of tasks
 * to keep track of
 */
public class TaskList {

    /**
     * a list that keeps track of current and new tasks
     */
    private ArrayList<Task> storage;


    /**
     * Constructor method for TaskList class
     * @param lines list of lines that convert into tasks, if not empty
     */
    public TaskList(List<String> lines){
        this.storage = new ArrayList<>(100);
        if (lines.size() != 0){
            fillStorage(lines);
        }
    }

    /**
     * Converts each line of {@code String} to {@code Task} object
     * before being added into taskList
     * @param lines list of lines read from file
     */
    private void fillStorage(List<String> lines){
        for (String line: lines){
            String[] arguments = line.split (" | ");
            switch(arguments[1]){
                case "T":
                    storeTodoTask(arguments);
                    break;
                case "D":
                    storeDeadlineTask(arguments);
                    break;
                case "E":
                    storeEventTask(arguments);
                    break;
            }
        }
    }

    /**
     * stores ToDo tasks into taskList NOSONAR
     * @param args array of arguments from a line
     */
    private void storeTodoTask(String[] args){
        Task addTask = new ToDo(args[3].trim());
        if (args[2].contains("X")){
            addTask.markAsDone();
        }

        storage.add(addTask);
    }

    /**
     * stores Deadline tasks into taskList
     * @param args array of arguments from a line
     */
    private void storeDeadlineTask(String[] args){
        String desc =  args[3].substring(0, args[3].indexOf("(by:")).trim();
        String deadline = args[3].substring(args[3].indexOf("(by:") +
                "(by:".length(), args[3].indexOf(")")).trim();

        Task addTask = new Deadline(desc, deadline);
        if (args[2].contains("X")){
            addTask.markAsDone();
        }

        storage.add(addTask);
    }

    /**
     * stores Event tasks into taskList
     * @param args array of arguments from a line
     */
    private void storeEventTask(String[] args){
        String desc = args[3].substring(0, args[3].indexOf("(from:")).trim();
        String from = args[3].substring(args[3].indexOf("(from:") +
                "(from:".length(), args[3].indexOf("to:")).trim();
        String to = args[3].substring(args[3].indexOf("to:") +
                "to:".length(), args[3].indexOf(")")).trim();

        Task addTask = new Events(desc, from, to);
        if (args[2].contains("X")){
            addTask.markAsDone();
        }

        storage.add(addTask);
    }

    /**
     * Prints out contents of taskList
     */
    public void generate(){
        System.out.println("_____________________________________\n");
        for (Task task : storage){
            System.out.println(storage.indexOf(task) + " | " + task.toString() + "\n");
        }
        System.out.println("_____________________________________\n");
    }


    /**
     * Adds task into taskList
     * @param task Task to be added
     */
    public void add(Task task){
        storage.add(task);
    }

    /**
     * Removes task at specified index from taskList
     * @param index index of taskList to remove
     * @throws DukeException when index is less than 0 or greater than size of taskList - 1
     */
    public void remove(int index) throws DukeException{
        try {
            storage.remove(index);
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    /**
     * Marks task at specified index from taskList
     * @param index index of taskList to remove
     * @throws DukeException when index is less than 0 or greater than size of taskList - 1
     */
    public void markTask(int index) throws DukeException{
        try {
            storage.get(index).markAsDone();
            System.out.println("_____________________________________\n" +
                    "Nice! I've marked this task as done:\n" +
                    storage.get(index).toString() + "\n" +
                    "_____________________________________\n");
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    /**
     * Unmarks task at specified index from taskList
     * @param index index of taskList to remove
     * @throws DukeException when index is less than 0 or greater than size of taskList - 1
     */
    public void unMarkTask(int index) throws DukeException{
        try {
            storage.get(index).unMark();
            System.out.println("_____________________________________\n" +
                    "Ok. I've marked this task as not done yet:\n" +
                    storage.get(index).toString() + "\n" +
                    "_____________________________________\n");
        } catch (IndexOutOfBoundsException err){
            throw new DukeException("Invalid Index given!");
        }
    }

    /**
     * Provides list of tasks
     * @return list of tasks
     */
    public ArrayList<Task> loadTaskList(){
        return this.storage;
    }

    /**
     * Gives string representation of number of tasks in taskList
     * @return string representation of size of taskList
     */
    public String taskCount(){
        if (this.storage.size() == 1){
            return "1 task ";
        } else {
            return this.storage.size() + " tasks ";
        }
    }
}
