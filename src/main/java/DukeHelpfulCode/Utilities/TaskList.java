package DukeHelpfulCode.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import DukeHelpfulCode.Exceptions.*;
import DukeHelpfulCode.Tasks.*;

public class TaskList {
    /**
     * List used by userCmds 'add' and 'list'.
     * Methods:
     *      addToList()     -> appends Task to the list
     *      markAsDone(int i)    -> mark Task in i-th index as done.
     */
    private static String LINEBREAK = "_________________________________________________________________\n";

    private static List<Task> tasks = new ArrayList<>();

    public static List<Task> getTaskList() {
        /**
         * Returns the current state of the list.
         *
         * @param   none
         * @return  taskList    The current taskList
         */
        return tasks;
    }

    public TaskList(List<Task> taskList){
        /**
         * taskList can be empty, but thats fine
         */
        this.tasks = taskList;
    }

    public TaskList(){}

    public String toString() {
        /**
         * Returns the string representation of the current TaskList.
         *
         * @param   none
         * @return  result  String representation of the tasklist
         *                  Tasks are listed vertically with 1-index
         */
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++){
            result += (i+1) +". " + this.tasks.get(i).toString() + "\n";
        }
        return result;
    }

    public String add(Task task){
        /**
         * Adds Task to the TaskList.
         *
         * @param   task    The task to be added to the list
         * @return  none
         */
        /*if (taskList.contains(task)){
           return "Oops, it seems that you have already added this Task to your list!\n";
        }
         */
        this.tasks.add(task);
        return LINEBREAK + "Got it. I've added this task: \n" + task.toString() + "\nNow you have " + this.len() + " tasks in your list.\n" + LINEBREAK;

    }

    public String mark(boolean isMark, int i) throws NoSuchTaskException, TaskAlrMarkException {
        /**
         * Marks the i-1 th index Task as done if not done and vice versa.
         * Note: list is 0-index but displayed as 1-index, hence i would be 1-index.
         * If i > userList.size(), throw TaskNotInListException.
         * If Task done but "mark" and vice versa, throw TaskAlrMarkException.
         *
         * @param   isMark      User's command to "mar" or "unmark" a Task as done or not done.
         * @param   i           The index of the Task to be marked. Note that user input i is 1-index, so -1 is needed.
         */
        if (i > this.tasks.size()) {
            throw new NoSuchTaskException();
        } else if (isMark && this.tasks.get(i-1).isDone()) {
            throw new TaskAlrMarkException();
        } else if (!isMark && !this.tasks.get(i-1).isDone()){
            throw new TaskAlrMarkException();
        } else {
            return this.tasks.get(i - 1).mark();
        }
    }

    public String delete(int taskNum) throws NoSuchTaskException {
        /**
         * Deletes the task selected by the user with the indexing
         *
         * @param   taskNum     taskNum is the 1-indexed indexing of the task chosen by the user to be deleted.
         *                      It is 1-indexed because the dsiplay list is 1-indexed.
         * @return  none
         */
        // deletes taskNum-1 indexed task from the list
        if (taskNum > this.len() || taskNum <= 0){
            throw new NoSuchTaskException();
        }
        else if (this.tasks.get(taskNum-1).isDone()) {
            this.tasks.remove(taskNum - 1);
            return "The " + taskNum +"th Task on the list has been removed!\n" + LINEBREAK;
        } else {
            return "The " + taskNum +"th Task on the list is not yet complete!";
            /*
            System.out.println("The " + taskNum +"th Task on the list is not yet complete!\nAre you sure you want to remove it? y/n");
            Scanner sc = new Scanner(System.in);
            String yn = sc.next();
            if (yn.equals("y")){
                this.taskList.remove(taskNum - 1);
                System.out.println("The " + taskNum +"th Task on the list has been removed!\n" + LINEBREAK);
            } else if (yn.equals("n")){
                System.out.println("OK, I will leave it!\n" + LINEBREAK);
            } else {
                System.out.println("Sorry, I don't understand.\n");
            }
            */
        }

    }

    public int len() {
        /**
         * Returns the number of Tasks in the TaskList currently.
         *
         * @param none
         * @return none
         */
        return this.tasks.size();
    }

}